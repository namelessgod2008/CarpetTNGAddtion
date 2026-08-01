package com.namelessgod2008.feature.recipe.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingMenu.class)
public class CraftingMenuMixin {

    @Inject(method = "slotChangedCraftingGrid", at = @At("TAIL"))
    private static void checkCraftingRules(AbstractContainerMenu menu, ServerLevel level, Player player,
                                           CraftingContainer container, ResultContainer resultContainer,
                                           RecipeHolder<?> recipe, CallbackInfo ci) {
        ItemStack result = resultContainer.getItem(0);
        if (result.is(Items.SADDLE) && !CarpetTNGSetting.craftableSaddle) {
            resultContainer.setItem(0, ItemStack.EMPTY);
        }
        if (result.is(Items.NAME_TAG) && !CarpetTNGSetting.craftableNameTag) {
            resultContainer.setItem(0, ItemStack.EMPTY);
        }
        if (result.is(Items.BELL) && !CarpetTNGSetting.craftableBell) {
            resultContainer.setItem(0, ItemStack.EMPTY);
        }
    }
}
