package com.namelessgod2008.feature.obsidian.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 坚固黑曜石：黑曜石免疫凋零的主动方块破坏。
 * <p>
 * 凋零"受击后自爆"的实质是 {@link WitherBoss#canDestroy}：每 20 tick 清空
 * 自身周围可破坏方块（条件仅 {@code !isAir && !WITHER_IMMUNE}，不查抗性/硬度），
 * 黑曜石不在 WITHER_IMMUNE tag 里所以被直接 destroyBlock 移除——这就是玩家
 * 眼中"凋零爆炸破坏黑曜石"。真正的爆炸（生成/受击强度 7）因黑曜石 1200 抗性
 * 本就破不了（ServerExplosion 的 h 公式一格即负）。
 */
@Mixin(WitherBoss.class)
public abstract class WitherBossMixin {

    @Inject(method = "canDestroy", at = @At("RETURN"), cancellable = true)
    private static void protectObsidianFromWither(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (CarpetTNGSetting.reinforcedObsidian && cir.getReturnValue() && state.is(Blocks.OBSIDIAN)) {
            cir.setReturnValue(false);
        }
    }
}
