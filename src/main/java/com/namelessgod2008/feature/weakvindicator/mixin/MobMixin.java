package com.namelessgod2008.feature.weakvindicator.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Vindicator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * 弱化卫道士：
 * 原版 {@link Mob#doHurtTarget}（Mob.java:1403）计算攻击伤害
 * {@code f = getAttributeValue(ATTACK_DAMAGE)}（卫道士基础 5.0）+ 附魔 + 武器攻击加成，
 * 然后 {@code source.hurtServer(level, damageSource, f)} 造成伤害。
 * <p>
 * 包裹该 {@code hurtServer} 调用，规则开启且攻击者（this）是卫道士时把伤害参数
 * 强制改为 1.0F——无论难度、附魔或武器，卫道士的攻击伤害恒为 1。
 * 卫道士用 {@code MeleeAttackGoal} 攻击，最终都走 {@code Mob.doHurtTarget}，单一注入点覆盖全部。
 */
@Mixin(Mob.class)
public class MobMixin {

    @WrapOperation(
            method = "doHurtTarget",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z"
            )
    )
    private boolean weakVindicatorDamage(Entity source, ServerLevel level, DamageSource damageSource, float amount,
                                         Operation<Boolean> original) {
        if (CarpetTNGSetting.weakVindicator && (Object) this instanceof Vindicator) {
            return original.call(source, level, damageSource, 1.0F);
        }
        return original.call(source, level, damageSource, amount);
    }
}
