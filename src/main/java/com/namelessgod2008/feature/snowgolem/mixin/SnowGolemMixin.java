package com.namelessgod2008.feature.snowgolem.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.animal.SnowGolem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * 雪傀儡不再融化受伤：
 * 原版 {@code SnowGolem.aiStep} 在炎热生物群系（SNOW_GOLEM_MELTS tag：
 * 沙漠/下界/恶地等）每 tick 受到 1 点火焰伤害。
 * 规则开启时 redirect 掉 {@code hurtServer} 调用，跳过该伤害。
 */
@Mixin(SnowGolem.class)
public class SnowGolemMixin {

    @Redirect(
            method = "aiStep",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/animal/SnowGolem;hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z"
            )
    )
    private boolean noMeltDamage(SnowGolem snowGolem, ServerLevel level, DamageSource damageSource, float amount) {
        if (CarpetTNGSetting.snowGolemNoMelt) {
            return false;
        }
        return snowGolem.hurtServer(level, damageSource, amount);
    }
}
