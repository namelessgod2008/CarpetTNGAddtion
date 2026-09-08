package com.namelessgod2008.feature.potion.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;

/**
 * GUI 药水槽放入检查：放行不祥之瓶。
 * 玩家在酿造台界面点击/Shift 放入药水槽走 {@code PotionSlot.mayPlaceItem}，
 * 原版仅接受药水/玻璃瓶，不祥之瓶需放行。
 */
@Mixin(targets = "net.minecraft.world.inventory.BrewingStandMenu$PotionSlot")
public class BrewingStandMenuMixin {

    @WrapMethod(method = "mayPlaceItem")
    private static boolean allowOminousBottle(ItemStack stack, Operation<Boolean> original) {
        if (CarpetTNGSetting.brewableOminousPotion && stack.is(Items.OMINOUS_BOTTLE)) {
            return true;
        }
        return original.call(stack);
    }
}
