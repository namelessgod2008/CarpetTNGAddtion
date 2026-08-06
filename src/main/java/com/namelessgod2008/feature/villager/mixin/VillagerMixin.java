package com.namelessgod2008.feature.villager.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 村民在下界/末地使用床会爆炸：
 * 村民尝试在无法设置出生点的维度（床不起作用的维度，如地狱/末地）睡觉时，
 * 复刻玩家右键床的爆炸逻辑（移除床 + 爆炸），并阻止村民入睡。
 * <p>
 * 注入 {@code Villager.startSleeping(BlockPos)}（村民入睡的专属入口，
 * 由 SleepInBed 行为调用）。规则开启且维度 {@code !dimensionType().bedWorks()} 时
 * 触发。爆炸伤害源、半径 5、引火、破坏方块均与原版床爆炸一致。
 */
@Mixin(Villager.class)
public class VillagerMixin {

    @Inject(method = "startSleeping", at = @At("HEAD"), cancellable = true)
    private void explodeOnBedInBadDimension(BlockPos pos, CallbackInfo ci) {
        if (!CarpetTNGSetting.villagerBedExplosion) return;
        Villager villager = (Villager) (Object) this;
        if (!(villager.level() instanceof ServerLevel level)) return;
        // Only explode where beds cannot set the spawn point (nether / the end).
        if (level.dimensionType().bedWorks()) return;

        // Mirror the vanilla bed explosion (BedBlock.useWithoutItem).
        BlockState state = level.getBlockState(pos);
        // If the head of the bed was targeted, also remove the foot half.
        if (state.getBlock() instanceof BedBlock && state.getValue(BedBlock.PART) == BedPart.HEAD) {
            BlockPos foot = pos.relative(state.getValue(BedBlock.FACING).getOpposite());
            if (level.getBlockState(foot).is(state.getBlock())) {
                level.removeBlock(foot, false);
            }
        }
        level.removeBlock(pos, false);

        Vec3 center = pos.getCenter();
        level.explode(null, level.damageSources().badRespawnPointExplosion(center), null,
                center, 5.0F, true, Level.ExplosionInteraction.BLOCK);
        ci.cancel();
    }
}
