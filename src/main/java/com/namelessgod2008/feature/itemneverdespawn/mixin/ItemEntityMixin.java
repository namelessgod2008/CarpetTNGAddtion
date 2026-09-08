package com.namelessgod2008.feature.itemneverdespawn.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * 掉落物永不消失：
 * 原版 {@link ItemEntity#tick()} 中 {@code age >= 6000}（5 分钟）时调用
 * {@code discard()} 移除掉落物。注入该调用（tick 内第二处 discard，ordinal=1，
 * 第一处是空物品清理不应拦截），规则开启时跳过，掉落物永远不消失。
 */
@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    @WrapOperation(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/item/ItemEntity;discard()V",
                    ordinal = 1
            )
    )
    private void skipAgeDespawn(ItemEntity self, Operation<Void> original) {
        // 规则关闭：照常 discard（掉落物 5 分钟消失，原版行为）
        if (!CarpetTNGSetting.itemNeverDespawn) {
            original.call(self);
        }
    }
}
