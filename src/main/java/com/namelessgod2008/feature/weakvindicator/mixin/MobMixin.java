package com.namelessgod2008.feature.weakvindicator.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Vindicator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * 弱化卫道士：
 * 原版 {@link Mob#doHurtTarget}（Mob.java:1403）计算攻击伤害
 * {@code f = getAttributeValue(ATTACK_DAMAGE)}（卫道士基础 5.0）+ 附魔 + 武器攻击加成，
 * 然后 {@code source.hurtServer(level, damageSource, f)} 造成伤害。
 * <p>
 * 注入该 {@code hurtServer} 调用的伤害参数（index 2），规则开启且攻击者是卫道士时
 * 强制改为 1.0F——无论难度、附魔或武器，卫道士的攻击伤害恒为 1。
 * 卫道士用 {@code MeleeAttackGoal} 攻击，最终都走 {@code Mob.doHurtTarget}，单一注入点覆盖全部。
 */
@Mixin(Mob.class)
public class MobMixin {

    @ModifyArg(
            method = "doHurtTarget",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z"
            ),
            index = 2
    )
    private float weakVindicatorDamage(float amount) {
        if (CarpetTNGSetting.weakVindicator && (Object) this instanceof Vindicator) {
            return 1.0F;
        }
        return amount;
    }
}
