package com.namelessgod2008.feature.copper.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.WeatheringCopperBulbBlock;
import net.minecraft.world.level.block.WeatheringCopperDoorBlock;
import net.minecraft.world.level.block.WeatheringCopperFullBlock;
import net.minecraft.world.level.block.WeatheringCopperGrateBlock;
import net.minecraft.world.level.block.WeatheringCopperSlabBlock;
import net.minecraft.world.level.block.WeatheringCopperStairBlock;
import net.minecraft.world.level.block.WeatheringCopperTrapDoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 铜接触水时的氧化速度倍率：
 * 原版氧化不受水影响（水/雨不加速），本功能新增——当铜方块接触水
 * （任一相邻面是水：水方块、流水、含水方块都算）时，把
 * {@code ChangeOverTimeBlock.changeOverTime} 的基础氧化概率 0.05688889F
 * 乘以规则倍率。倍率 1.0 时行为与原版完全一致。
 * <p>
 * Mixin 不能把接口 {@code ChangeOverTimeBlock} 作为 {@code @Mixin} 目标注入其
 * default 方法（interface mixin 只支持给接口添加方法）。因此改为注入全部 7 种
 * 铜方块实现类的 {@code randomTick}，HEAD 接管（cancel 时自行处理、保留铜门的
 * 下半格判定），倍率 1.0 或未接触水时直接放行原版逻辑。
 * <p>
 * 判定"接触水"：遍历 6 个相邻面，任一面的 {@code getFluidState().is(WATER)}
 * 成立即算接触。铜方块自身不可含水（waterloggable=false），无需检查自身位置。
 */
@Mixin({
        WeatheringCopperFullBlock.class,
        WeatheringCopperSlabBlock.class,
        WeatheringCopperStairBlock.class,
        WeatheringCopperDoorBlock.class,
        WeatheringCopperTrapDoorBlock.class,
        WeatheringCopperGrateBlock.class,
        WeatheringCopperBulbBlock.class
})
public class ChangeOverTimeMixin {

    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void applyUnderwaterOxidationMultiplier(BlockState state, ServerLevel level, BlockPos pos,
                                                    RandomSource random, CallbackInfo ci) {
        double multiplier = CarpetTNGSetting.copperUnderwaterOxidationMultiplier;
        if (multiplier == 1.0 || !isInContactWithWater(level, pos)) {
            return; // 保持原版行为
        }
        // 铜门只氧化下半格（与 DoorBlock.randomTick 原版判定一致）
        if (state.getBlock() instanceof WeatheringCopperDoorBlock
                && state.getValue(DoorBlock.HALF) != DoubleBlockHalf.LOWER) {
            return;
        }
        ci.cancel();
        float probability = 0.05688889F * (float) multiplier;
        if (random.nextFloat() < probability) {
            ChangeOverTimeBlock<?> self = (ChangeOverTimeBlock<?>) (Object) this;
            self.getNextState(state, level, pos, random)
                    .ifPresent(blockState -> level.setBlockAndUpdate(pos, blockState));
        }
    }

    private static boolean isInContactWithWater(ServerLevel level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (level.getFluidState(pos.relative(direction)).is(FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }
}
