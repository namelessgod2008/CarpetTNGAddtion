package com.namelessgod2008.feature.slime.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * 抢夺附魔击杀大史莱姆/岩浆怪后分裂更多小史莱姆：
 * 原版 {@code Slime.remove} 分裂数 {@code k = 2 + random.nextInt(3)}。
 * 规则开启且击杀者手持的武器带抢夺附魔时，每 1 级抢夺多分裂 1 个
 * （k += lootingLevel）。岩浆怪继承 {@code Slime}，天然适用。
 */
@Mixin(Slime.class)
public class SlimeMixin {

    @Redirect(
            method = "remove",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/RandomSource;nextInt(I)I"
            )
    )
    private int moreSplitsWithLooting(RandomSource random, int bound) {
        if (CarpetTNGSetting.lootingSlimeSplit) {
            int looting = getLootingLevel((Slime) (Object) this);
            if (looting > 0) {
                return random.nextInt(bound) + looting;
            }
        }
        return random.nextInt(bound);
    }

    private static int getLootingLevel(Slime slime) {
        if (!(slime.level() instanceof ServerLevel serverLevel)) return 0;
        LivingEntity killer = slime.getKillCredit();
        if (killer == null) return 0;
        ItemStack weapon = killer.getMainHandItem();
        return serverLevel.registryAccess()
                .lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT)
                .get(Enchantments.LOOTING)
                .map(looting -> EnchantmentHelper.getItemEnchantmentLevel(looting, weapon))
                .orElse(0);
    }
}
