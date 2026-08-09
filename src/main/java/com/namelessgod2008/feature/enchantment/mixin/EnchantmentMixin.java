package com.namelessgod2008.feature.enchantment.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.core.Holder;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 不同类型保护魔咒可以叠加：
 * 原版保护/爆炸保护/火焰保护/弹射物保护共享 {@code exclusive_set/armor} tag，
 * {@code Enchantment.areCompatible} 判定它们互斥，无法附在同一件装备上。
 * 规则开启时，若两附魔都属于该互斥组（都是保护类），视为兼容（允许叠加）。
 * <p>
 * 伤害减免本身原版 {@code EnchantmentHelper.getDamageProtection} 已累加
 * 不同来源的保护，无需额外处理。
 */
@Mixin(Enchantment.class)
public class EnchantmentMixin {

    @Inject(method = "areCompatible", at = @At("RETURN"), cancellable = true)
    private static void allowStackableProtection(Holder<Enchantment> first, Holder<Enchantment> second,
                                                 CallbackInfoReturnable<Boolean> cir) {
        if (!CarpetTNGSetting.stackableProtection) return;
        // Both are armor protection types (share the exclusive_set/armor tag): allow stacking.
        if (first.is(EnchantmentTags.ARMOR_EXCLUSIVE) && second.is(EnchantmentTags.ARMOR_EXCLUSIVE)) {
            cir.setReturnValue(true);
        }
    }
}
