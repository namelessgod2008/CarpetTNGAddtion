package com.namelessgod2008.feature.potion.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.component.OminousBottleAmplifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

/**
 * 灾厄药水酿造（直接产出原版灾厄之瓶 ominous_bottle）：
 * <ul>
 *   <li>粗制的药水 + 不死图腾 → 灾厄之瓶 I</li>
 *   <li>灾厄之瓶 I-IV + 萤石粉 → 等级 +1（最多 V 级）</li>
 *   <li>V 级 + 萤石粉无效</li>
 *   <li>效果与原版一致：不祥之兆，1 小时 40 分钟</li>
 * </ul>
 * 接管 {@code isBrewable}/{@code doBrew}，规则关闭时完全走原版。
 */
@Mixin(BrewingStandBlockEntity.class)
public class BrewingStandMixin {

    private static final int MAX_LEVEL = 5;

    @Inject(method = "canPlaceItem", at = @At("HEAD"), cancellable = true)
    private void allowOminousItems(int slot, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (!CarpetTNGSetting.brewableOminousPotion) return;
        // 材料槽：放行不死图腾
        if (slot == 3 && stack.is(Items.TOTEM_OF_UNDYING)) {
            cir.setReturnValue(true);
            return;
        }
        // 药水槽：放行不祥之瓶（需槽位为空，与原版逻辑一致）
        if (slot <= 2 && stack.is(Items.OMINOUS_BOTTLE)
                && ((net.minecraft.world.Container) (Object) this).getItem(slot).isEmpty()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "isBrewable", at = @At("RETURN"), cancellable = true)
    private static void allowOminousBrewing(PotionBrewing potionBrewing, NonNullList<ItemStack> items,
                                            CallbackInfoReturnable<Boolean> cir) {
        if (!CarpetTNGSetting.brewableOminousPotion) return;
        if (cir.getReturnValue()) return; // 原版配方已可行
        cir.setReturnValue(hasOminousRecipe(items));
    }

    @Inject(method = "doBrew", at = @At("HEAD"), cancellable = true)
    private static void doOminousBrew(Level level, BlockPos pos, NonNullList<ItemStack> items, CallbackInfo ci) {
        if (!CarpetTNGSetting.brewableOminousPotion) return;

        ItemStack material = items.get(3);
        boolean handled = false;

        if (material.is(Items.TOTEM_OF_UNDYING)) {
            // 粗制药水 → 灾厄之瓶 I
            for (int i = 0; i < 3; i++) {
                if (isAwkwardPotion(items.get(i))) {
                    items.set(i, newOminousBottle(0));
                    handled = true;
                }
            }
        } else if (material.is(Items.GLOWSTONE_DUST)) {
            // 灾厄之瓶升级（最多 V 级）
            for (int i = 0; i < 3; i++) {
                ItemStack stack = items.get(i);
                if (stack.is(Items.OMINOUS_BOTTLE)) {
                    int amplifier = stack.getOrDefault(DataComponents.OMINOUS_BOTTLE_AMPLIFIER,
                            new OminousBottleAmplifier(0)).value();
                    if (amplifier < MAX_LEVEL - 1) {
                        items.set(i, newOminousBottle(amplifier + 1));
                        handled = true;
                    }
                }
            }
        }

        if (handled) {
            material.shrink(1);
            items.set(3, material);
            level.levelEvent(1035, pos, 0); // 酿造完成音效/粒子
            ci.cancel(); // 跳过原版 doBrew
        }
    }

    private static boolean hasOminousRecipe(NonNullList<ItemStack> items) {
        ItemStack material = items.get(3);
        for (int i = 0; i < 3; i++) {
            ItemStack stack = items.get(i);
            if (stack.isEmpty()) continue;
            if (material.is(Items.TOTEM_OF_UNDYING) && isAwkwardPotion(stack)) return true;
            if (material.is(Items.GLOWSTONE_DUST) && stack.is(Items.OMINOUS_BOTTLE)) {
                int level = stack.getOrDefault(DataComponents.OMINOUS_BOTTLE_AMPLIFIER,
                        new OminousBottleAmplifier(0)).value();
                if (level < MAX_LEVEL - 1) return true;
            }
        }
        return false;
    }

    private static boolean isAwkwardPotion(ItemStack stack) {
        Optional<Holder<Potion>> potion = stack.getOrDefault(
                DataComponents.POTION_CONTENTS, PotionContents.EMPTY).potion();
        return potion.isPresent() && potion.get().is(Potions.AWKWARD);
    }

    private static ItemStack newOminousBottle(int amplifier) {
        ItemStack stack = new ItemStack(Items.OMINOUS_BOTTLE);
        stack.set(DataComponents.OMINOUS_BOTTLE_AMPLIFIER, new OminousBottleAmplifier(amplifier));
        return stack;
    }
}
