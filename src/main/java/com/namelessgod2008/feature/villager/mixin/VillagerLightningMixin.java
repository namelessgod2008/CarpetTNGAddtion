package com.namelessgod2008.feature.villager.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ConversionParams;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * 村民被闪电击中后不会变成女巫：
 * 原版 {@code Villager.thunderHit} 在非和平难度调用
 * {@code convertTo(EntityType.WITCH, ...)} 变女巫；转化失败时走
 * {@code super.thunderHit}（被击中但不变身）。
 * 规则开启时拦截 {@code convertTo} 调用返回 null，使村民保持原样
 * （仍受雷击但不会变身女巫）。
 */
@Mixin(Villager.class)
public class VillagerLightningMixin {

    @WrapOperation(
            method = "thunderHit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/npc/Villager;convertTo(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/entity/ConversionParams;Lnet/minecraft/world/entity/ConversionParams$AfterConversion;)Lnet/minecraft/world/entity/Mob;"
            )
    )
    private Mob noWitchConversion(Villager villager, EntityType<Mob> entityType, ConversionParams params,
                                  ConversionParams.AfterConversion<Mob> afterConversion, Operation<Mob> original) {
        if (CarpetTNGSetting.villagerLightningNoWitch) {
            return null; // conversion fails -> vanilla falls back to super.thunderHit
        }
        return original.call(villager, entityType, params, afterConversion);
    }
}
