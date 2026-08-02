package com.namelessgod2008.feature.dispenser.mixin;

import com.namelessgod2008.feature.dispenser.DispenserPlantingHandler;
import com.namelessgod2008.feature.dispenser.PlantingSeedAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 让发射器喷出的种子沿直线轨道飞行，碰到目标方块（耕地/灵魂沙）时种植作物。
 * <p>
 * 关键：原版 {@code ItemEntity.tick} 服务端会检查实体与方块重叠并
 * {@code moveTowardsClosestSpace} 把实体推开（悬停在耕地层内的种子会被
 * 每 tick 顶高，导致轨迹偏移）。因此飞行期间：
 * <ul>
 *   <li>HEAD：清空速度，阻止原版 move 移动/推挤</li>
 *   <li>TAIL：手动 {@code setPos} 沿直线推进（客户端由位置包正常插值）</li>
 * </ul>
 * 飞行数据存放在实体自身上，物品无 NBT，可正常堆叠与拾取。
 */
@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin implements PlantingSeedAccessor {

    @Unique
    private CompoundTag plantingData;

    @Override
    public CompoundTag carpettng$getPlantingData() {
        return plantingData;
    }

    @Override
    public void carpettng$setPlantingData(CompoundTag tag) {
        this.plantingData = tag;
    }

    /** 飞行数据随实体存档持久化。 */
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void savePlantingData(CompoundTag tag, CallbackInfo ci) {
        if (plantingData != null) {
            tag.put("carpettng:planting", plantingData);
        }
    }

    /** 读取存档中的飞行数据。 */
    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readPlantingData(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains("carpettng:planting")) {
            plantingData = tag.getCompound("carpettng:planting");
        }
    }

    /** 飞行中清空速度，阻止原版 move 移动与方块推挤。 */
    @Inject(method = "tick", at = @At("HEAD"))
    private void onPlantingSeedTickHead(CallbackInfo ci) {
        if (plantingData != null) {
            ((ItemEntity) (Object) this).setDeltaMovement(Vec3.ZERO);
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void onPlantingSeedTick(CallbackInfo ci) {
        ItemEntity self = (ItemEntity) (Object) this;
        if (plantingData == null) return;
        CompoundTag data = plantingData;

        Level level = self.level();
        double dx = data.getDouble(DispenserPlantingHandler.KEY_DX);
        double dy = data.getDouble(DispenserPlantingHandler.KEY_DY);
        double dz = data.getDouble(DispenserPlantingHandler.KEY_DZ);
        int ticks = data.getInt(DispenserPlantingHandler.KEY_TICKS) + 1;
        data.putInt(DispenserPlantingHandler.KEY_TICKS, ticks);

        // 先检测当前位置的耕地（在推进之前，保证终点不会超出第 9 格）
        BlockPos pos = self.blockPosition();
        if (tryPlant(level, pos, self, data) || tryPlantBelow(level, pos, self, data)) {
            return;
        }

        // 飞行超时：恢复为普通掉落物（本 tick 不再推进，落点在第 9 格内）
        if (ticks >= DispenserPlantingHandler.MAX_TICKS) {
            self.setNoGravity(false);
            self.setDeltaMovement(0, 0.05, 0);
            plantingData = null;
            return;
        }

        // 手动直线推进（绕过原版 move 的推挤/碰撞/摩擦）
        self.setNoGravity(true);
        self.setDeltaMovement(dx, dy, dz);
        self.setPos(self.getX() + dx, self.getY() + dy, self.getZ() + dz);

        // 飞行尾迹：绿色尘埃粒子（每 2 tick）——不用 ITEM 粒子，
        // ITEM 粒子是方块破坏时的碎片粒子，会误看成"作物被破坏"
        if (ticks % 2 == 0 && level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(new net.minecraft.core.particles.DustParticleOptions(0x55FF55, 0.6f),
                    self.getX(), self.getY() + 0.2, self.getZ(), 1, 0.05, 0.05, 0.05, 0);
        }
    }

    /** 种子所在格为空气/可替换，其下方是目标方块（耕地/灵魂沙）→ 种在种子所在格。 */
    private static boolean tryPlant(Level level, BlockPos pos, ItemEntity seed, CompoundTag data) {
        BlockState state = level.getBlockState(pos);
        BlockState below = level.getBlockState(pos.below());
        if (below.is(DispenserPlantingHandler.getBlock(data.getString(DispenserPlantingHandler.KEY_TARGET)))
                && state.canBeReplaced()) {
            plant(level, pos, seed, data);
            return true;
        }
        return false;
    }

    /** 种子所在格本身是目标方块（耕地/灵魂沙）→ 种在其上方一格。 */
    private static boolean tryPlantBelow(Level level, BlockPos pos, ItemEntity seed, CompoundTag data) {
        BlockState state = level.getBlockState(pos);
        BlockState above = level.getBlockState(pos.above());
        if (state.is(DispenserPlantingHandler.getBlock(data.getString(DispenserPlantingHandler.KEY_TARGET)))
                && above.canBeReplaced()) {
            plant(level, pos.above(), seed, data);
            return true;
        }
        return false;
    }

    private static void plant(Level level, BlockPos pos, ItemEntity seed, CompoundTag data) {
        Block crop = DispenserPlantingHandler.getBlock(data.getString(DispenserPlantingHandler.KEY_CROP));
        if (crop != null) {
            level.setBlock(pos, crop.defaultBlockState(), 3);
            level.playSound(null, pos, SoundEvents.CROP_PLANTED, SoundSource.BLOCKS, 1.0f, 1.0f);
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(net.minecraft.core.particles.ParticleTypes.HAPPY_VILLAGER,
                        pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5, 6, 0.3, 0.2, 0.3, 0.05);
            }
        }

        // 清除飞行数据并销毁种子实体
        ((PlantingSeedAccessor) seed).carpettng$setPlantingData(null);
        seed.discard();
    }
}
