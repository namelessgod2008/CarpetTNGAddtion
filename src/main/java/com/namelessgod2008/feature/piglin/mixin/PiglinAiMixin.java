package com.namelessgod2008.feature.piglin.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

/**
 * 猪灵相关规则（feature/piglin）：
 * <ul>
 *   <li>piglinBarterDisabledTime：修改 {@code wasHurtBy} 的 ADMIRING_DISABLED 记忆时长
 *       （原版 400 tick，被玩家攻击后拒绝交易的时间，自由数值）</li>
 *   <li>neutralPiglins：禁用 {@code findNearestValidAttackTarget} 的"无金甲玩家"分支——
 *       猪灵不再主动攻击未穿金护甲的玩家；ANGRY_AT/UNIVERSAL_ANGER/猪灵蛮兵分支保留（群起而攻之照旧）</li>
 * </ul>
 */
@Mixin(PiglinAi.class)
public abstract class PiglinAiMixin {

    @ModifyArg(method = "wasHurtBy",
               at = @At(value = "INVOKE",
                        target = "Lnet/minecraft/world/entity/ai/Brain;setMemoryWithExpiry(Lnet/minecraft/world/entity/ai/memory/MemoryModuleType;Ljava/lang/Object;J)V",
                        ordinal = 0), // ordinal 0 = ADMIRING_DISABLED（400L），ordinal 1 = AVOID_TARGET
               index = 2)
    private static long customTradeDisabledTime(long expiry) {
        return CarpetTNGSetting.piglinBarterDisabledTime;
    }

    @Inject(method = "findNearestValidAttackTarget", at = @At("RETURN"), cancellable = true)
    private static void neutralPiglin(ServerLevel level, Piglin piglin, CallbackInfoReturnable<Optional<? extends LivingEntity>> cir) {
        if (!CarpetTNGSetting.neutralPiglins || cir.getReturnValue().isEmpty()) {
            return;
        }
        // 目标是"无金甲玩家"记忆（主动攻击来源）→ 取消攻击；其他分支（ANGRY_AT/蛮兵等）保留
        Optional<Player> notWearingGold = piglin.getBrain().getMemory(MemoryModuleType.NEAREST_TARGETABLE_PLAYER_NOT_WEARING_GOLD);
        if (notWearingGold.isPresent() && notWearingGold.get() == cir.getReturnValue().get()) {
            cir.setReturnValue(Optional.empty());
        }
    }
}
