package com.namelessgod2008.feature.endcrystal.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.item.EndCrystalItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * 末地水晶放置限制：
 * 规则默认开启 = 原版行为（末地水晶只能放在黑曜石/基岩上）。
 * 规则关闭时允许在任意方块上放置（解除原版限制）。
 * <p>
 * 原版 {@code EndCrystalItem.useOn} 检查
 * {@code !state.is(OBSIDIAN) && !state.is(BEDROCK)} 时 FAIL。
 * 规则关闭时让 {@code state.is(OBSIDIAN)} 恒返回 true，
 * 使条件不成立，跳过 FAIL 允许放置。
 */
@Mixin(EndCrystalItem.class)
public class EndCrystalItemMixin {

    @WrapOperation(
            method = "useOn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"
            )
    )
    private boolean allowPlacementWithoutRestriction(BlockState state, Block block, Operation<Boolean> original) {
        if (!CarpetTNGSetting.endCrystalPlacementRestriction && block == Blocks.OBSIDIAN) {
            return true;
        }
        return original.call(state, block);
    }
}
