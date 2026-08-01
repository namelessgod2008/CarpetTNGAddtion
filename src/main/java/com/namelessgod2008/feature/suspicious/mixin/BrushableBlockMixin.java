package com.namelessgod2008.feature.suspicious.mixin;

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
 * 精准采集可以挖可疑的沙子和砂砾：
 * 原版可疑方块掉落表为空（挖掉不掉落任何物品），
 * 手持精准采集工具破坏时掉落方块自身。
 * <p>
 * 注入声明 {@code spawnAfterBreak} 的 {@code BlockBehaviour}，
 * 通过方块过滤仅处理可疑沙子/砂砾。
 */
@Mixin(BlockBehaviour.class)
public class BrushableBlockMixin {

    @Inject(method = "spawnAfterBreak", at = @At("HEAD"))
    private void dropWithSilkTouch(BlockState state, ServerLevel level, BlockPos pos,
                                   ItemStack tool, boolean dropExperience, CallbackInfo ci) {
        if (!state.is(Blocks.SUSPICIOUS_SAND) && !state.is(Blocks.SUSPICIOUS_GRAVEL)) return;
        if (!CarpetTNGSetting.silkTouchSuspiciousBlocks) return;
        level.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT)
                .get(Enchantments.SILK_TOUCH)
                .ifPresent(silkTouch -> {
                    if (EnchantmentHelper.getItemEnchantmentLevel(silkTouch, tool) > 0) {
                        Block.popResource(level, pos, new ItemStack(state.getBlock()));
                    }
                });
    }
}
