package com.namelessgod2008.feature.copper.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 向铜方块投掷喷溅水瓶会使其氧化到下一阶段：
 * 命中方块时，若目标是可氧化的铜方块（{@code ChangeOverTimeBlock}），
 * 且水瓶是普通水瓶（{@code Potions.WATER}），立即氧化到下一阶段。
 * <p>
 * 注入 {@code ThrownPotion.onHitBlock} HEAD（不 cancel，保留原版扑火等行为）。
 * 与 {@link ChangeOverTimeMixin} 同属 copper 功能包。
 */
@Mixin(ThrownPotion.class)
public class ThrownPotionMixin {

    @Inject(method = "onHitBlock", at = @At("HEAD"))
    private void oxidizeCopperOnWaterSplash(BlockHitResult result, CallbackInfo ci) {
        if (!CarpetTNGSetting.splashOxidizeCopper) return;
        ThrownPotion potion = (ThrownPotion) (Object) this;
        if (potion.level().isClientSide) return;
        if (!(potion.level() instanceof ServerLevel level)) return;

        // Only plain splash water bottles trigger oxidation.
        PotionContents potionContents = potion.getItem()
                .getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        if (!potionContents.is(Potions.WATER)) return;

        BlockPos pos = result.getBlockPos();
        BlockState state = level.getBlockState(pos);
        // Oxidize to the next stage only for oxidizable copper blocks.
        // getNext is an interface default method, call it through the block instance.
        if (state.getBlock() instanceof ChangeOverTimeBlock<?> changeOverTime) {
            changeOverTime.getNext(state).ifPresent(next -> level.setBlockAndUpdate(pos, next));
        }
    }
}
