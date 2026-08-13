package com.namelessgod2008.feature.enderman.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * 阻止末影人搬起黑名单中的方块：
 * 原版 {@code EnderMan.EndermanTakeBlockGoal.tick} 在
 * {@code blockState.is(BlockTags.ENDERMAN_HOLDABLE)} 且视线无障碍时搬方块。
 * 规则值为逗号分隔的方块 id 黑名单，命中时返回 false（不搬）。
 * 空值（默认）不限制，行为与原版一致。
 */
@Mixin(targets = "net.minecraft.world.entity.monster.EnderMan$EndermanTakeBlockGoal")
public class EnderManMixin {

    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z"
            )
    )
    private boolean blockBlacklisted(BlockState state, TagKey<Block> tag) {
        String blacklist = CarpetTNGSetting.endermanNoTakeBlocks;
        if (!blacklist.isBlank() && state.getBlock() != null && isBlacklisted(state.getBlock(), blacklist)) {
            return false;
        }
        return state.is(tag);
    }

    private static boolean isBlacklisted(Block block, String blacklist) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
        if (key == null) return false;
        for (String entry : blacklist.split(",")) {
            String trimmed = entry.trim();
            if (trimmed.isEmpty()) continue;
            ResourceLocation entryId = ResourceLocation.tryParse(trimmed);
            if (entryId != null && entryId.equals(key)) {
                return true;
            }
        }
        return false;
    }
}
