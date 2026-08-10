package com.namelessgod2008.feature.enchantment.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.core.Holder;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.inventory.GrindstoneMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * 诅咒附魔可移除（放在砂轮上）：
 * 原版 {@code GrindstoneMenu.removeNonCursesFrom} 用
 * {@code removeIf(holder -> !holder.is(EnchantmentTags.CURSE))} 只移除非诅咒
 * 附魔，保留绑定诅咒/消失诅咒。规则开启时让 {@code is(CURSE)} 返回 false，
 * 使诅咒也被移除。
 */
@Mixin(GrindstoneMenu.class)
public class GrindstoneMenuMixin {

    @Redirect(
            // removeIf predicate lambda of removeNonCursesFrom; method name is a stable
            // synthetic lambda name in both dev and prod (see anvil method_24922 precedent).
            method = "method_58073",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/core/Holder;is(Lnet/minecraft/tags/TagKey;)Z"
            )
    )
    private static boolean removeCursesOnGrindstone(Holder<Enchantment> holder, net.minecraft.tags.TagKey<Enchantment> tag) {
        if (CarpetTNGSetting.grindstoneRemovesCurses && tag == EnchantmentTags.CURSE) {
            return false; // curses are treated as removable -> removeIf keeps them out
        }
        return holder.is(tag);
    }
}
