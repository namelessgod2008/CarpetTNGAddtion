package com.namelessgod2008.feature.farmland.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * 穿着带摔落缓冲（Feather Falling）附魔的靴子时，落到耕地上不会把它踩成泥土。
 * <p>
 * 原版 {@code FarmBlock.fallOn} 在实体落到耕地且满足条件时调用
 * {@code turnToDirt}。规则开启且实体是 LivingEntity、其靴子带摔落缓冲时，
 * redirect 掉 {@code turnToDirt} 调用，保留 {@code super.fallOn} 的其余行为
 * （摔落伤害等）。
 */
@Mixin(FarmBlock.class)
public class FarmBlockMixin {

    @Redirect(
            method = "fallOn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/FarmBlock;turnToDirt(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"
            )
    )
    private void protectFarmlandWithFeatherFalling(Entity entity, BlockState state, Level level, BlockPos pos) {
        if (CarpetTNGSetting.featherFallingProtectsFarmland
                && entity instanceof LivingEntity living
                && level instanceof ServerLevel serverLevel
                && hasFeatherFalling(serverLevel, living)) {
            return; // 不踩坏耕地
        }
        FarmBlock.turnToDirt(entity, state, level, pos);
    }

    private static boolean hasFeatherFalling(ServerLevel level, LivingEntity living) {
        return level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT)
                .get(Enchantments.FEATHER_FALLING)
                .map(featherFalling ->
                        EnchantmentHelper.getItemEnchantmentLevel(featherFalling, living.getItemBySlot(EquipmentSlot.FEET)) > 0)
                .orElse(false);
    }
}
