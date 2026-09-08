package com.namelessgod2008.feature.trialspawner.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawner;
import org.spongepowered.asm.mixin.Mixin;

/**
 * 缩短试炼刷怪笼冷却：
 * 所有冷却/奖励逻辑共用 {@code getTargetCooldownLength()}（原版默认 36000 tick = 30 分钟），
 * 规则开启时固定为 5 分钟（6000 tick），战斗结束后可更快再次挑战。
 */
@Mixin(TrialSpawner.class)
public class TrialSpawnerMixin {

    private static final int COOLDOWN_5_MIN = 6000;

    @WrapMethod(method = "getTargetCooldownLength")
    private int shortenCooldown(Operation<Integer> original) {
        return CarpetTNGSetting.shortenedTrialSpawnerCooldown ? COOLDOWN_5_MIN : original.call();
    }
}
