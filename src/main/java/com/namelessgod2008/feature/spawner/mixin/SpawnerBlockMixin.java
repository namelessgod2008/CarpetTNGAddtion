package com.namelessgod2008.feature.spawner.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 精准采集可以采集刷怪笼：
 * 原版刷怪笼的掉落表为空（被任何工具破坏都不掉落物品），
 * 手持精准采集工具破坏时掉落刷怪笼自身，且完整保留方块实体的
 * 刷怪配置（SpawnData / SpawnPotentials / RequiredPlayerRange 等）。
 * <p>
 * 注入声明 {@code spawnAfterBreak} 的 {@code SpawnerBlock}（原版它自己覆盖了
 * 该方法，注入 {@code BlockBehaviour} 对刷怪笼不生效）。
 */
@Mixin(SpawnerBlock.class)
public class SpawnerBlockMixin {

    @Inject(method = "spawnAfterBreak", at = @At("HEAD"))
    private void dropSpawnerWithSilkTouch(BlockState state, ServerLevel level, BlockPos pos,
                                          ItemStack tool, boolean dropExperience, CallbackInfo ci) {
        if (!CarpetTNGSetting.silkTouchSpawners) return;
        level.registryAccess().lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT)
                .get(Enchantments.SILK_TOUCH)
                .ifPresent(silkTouch -> {
                    if (EnchantmentHelper.getItemEnchantmentLevel(silkTouch, tool) > 0) {
                        ItemStack drop = new ItemStack(state.getBlock());
                        // Preserve the full spawner config (entity type, spawn potentials, range).
                        if (level.getBlockEntity(pos) instanceof SpawnerBlockEntity spawner) {
                            CustomData.set(DataComponents.BLOCK_ENTITY_DATA, drop,
                                    spawner.saveWithFullMetadata(level.registryAccess()));
                        }
                        Block.popResource(level, pos, drop);
                    }
                });
    }
}
