package com.namelessgod2008.feature.silktouch.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 精准采集可以采集土径和耕地：
 * 原版土径/耕地的掉落表只有泥土（用任何工具挖掘都掉泥土），
 * 手持精准采集工具破坏时掉落方块自身（土径或耕地的物品形态）。
 * <p>
 * 这两个方块类（DirtPathBlock/FarmBlock）都直接继承 {@code Block}，
 * 未覆盖 {@code spawnAfterBreak}，注入声明它的 {@code BlockBehaviour} 即可。
 */
@Mixin(BlockBehaviour.class)
public class SoilSilkTouchMixin {

    @Inject(method = "spawnAfterBreak", at = @At("HEAD"))
    private void dropSoilWithSilkTouch(BlockState state, ServerLevel level, BlockPos pos,
                                       ItemStack tool, boolean dropExperience, CallbackInfo ci) {
        Block block = state.getBlock();
        if (!state.is(Blocks.DIRT_PATH) && !state.is(Blocks.FARMLAND)) return;
        boolean enabled = state.is(Blocks.DIRT_PATH)
                ? CarpetTNGSetting.silkTouchPathBlocks
                : CarpetTNGSetting.silkTouchFarmland;
        if (!enabled) return;
        level.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT)
                .get(Enchantments.SILK_TOUCH)
                .ifPresent(silkTouch -> {
                    if (EnchantmentHelper.getItemEnchantmentLevel(silkTouch, tool) > 0) {
                        Block.popResource(level, pos, new ItemStack(block));
                    }
                });
    }
}
