package com.namelessgod2008.feature.potion.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import org.spongepowered.asm.mixin.Mixin;

/**
 * 允许不死图腾作为酿造材料放入酿造台：
 * 材料槽的放入判定（GUI 的 {@code IngredientsSlot.mayPlace}、漏斗等）最终
 * 都走 {@code PotionBrewing.isIngredient}，此处放行即可覆盖所有放入路径。
 */
@Mixin(PotionBrewing.class)
public class PotionBrewingMixin {

    @WrapMethod(method = "isIngredient")
    private boolean allowTotemOfUndying(ItemStack stack, Operation<Boolean> original) {
        if (CarpetTNGSetting.brewableOminousPotion && stack.is(Items.TOTEM_OF_UNDYING)) {
            return true;
        }
        return original.call(stack);
    }
}
