package com.namelessgod2008.feature.tadpole.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ConversionParams;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.FrogVariant;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.frog.Tadpole;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 蝌蚪喂食染料标记长大后青蛙的颜色：
 * 仅橙色/白色/绿色三种染料（分别对应温带/暖/冷三种青蛙变体）。每次喂食
 * 不增加生长进度（不 ageUp），用 {@code ServerLevel.sendParticles} 发送
 * END_ROD 粒子（区别于原版喂食的 HAPPY_VILLAGER，且能正常显示）。
 * 一只蝌蚪只能被喂一次染料（喂过后再喂无效）。
 * <p>
 * 注入：mobInteract HEAD 拦截染料；addAdditionalSaveData/readAdditionalSaveData
 * 持久化颜色；ageUp 的 convertTo 用 {@code @ModifyArg} 包裹 AfterConversion
 * lambda 设置变体。
 */
@Mixin(Tadpole.class)
public abstract class TadpoleMixin {

    @Unique
    @Nullable
    private DyeColor carpettng$dyeColor;

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void feedDye(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (!CarpetTNGSetting.tadpoleDyeColor) return;
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getItem() instanceof DyeItem dyeItem) {
            DyeColor color = dyeItem.getDyeColor();
            // Only the three dyes matching the vanilla frog variants are accepted.
            if (color != DyeColor.ORANGE && color != DyeColor.WHITE && color != DyeColor.GREEN) {
                return;
            }
            // A tadpole can only be dyed once.
            if (this.carpettng$dyeColor != null) {
                return;
            }
            Tadpole tadpole = (Tadpole) (Object) this;
            this.carpettng$dyeColor = color;
            stack.consume(1, player);
            if (tadpole.level() instanceof ServerLevel serverLevel) {
                // Dust particle tinted with the fed dye's own color.
                serverLevel.sendParticles(new DustParticleOptions(color.getTextColor(), 1.0f), false, true,
                        tadpole.getRandomX(1.0), tadpole.getRandomY() + 0.5, tadpole.getRandomZ(1.0),
                        10, 0.25, 0.15, 0.25, 0.02);
            }
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void saveDye(CompoundTag tag, CallbackInfo ci) {
        if (this.carpettng$dyeColor != null) {
            tag.putByte("TNGDyeColor", (byte) this.carpettng$dyeColor.getId());
        }
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void loadDye(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains("TNGDyeColor")) {
            this.carpettng$dyeColor = DyeColor.byId(tag.getByte("TNGDyeColor"));
        }
    }

    @ModifyArg(
            method = "ageUp()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/animal/frog/Tadpole;convertTo(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/entity/ConversionParams;Lnet/minecraft/world/entity/ConversionParams$AfterConversion;)Lnet/minecraft/world/entity/Mob;"
            ),
            index = 2
    )
    private ConversionParams.AfterConversion<Mob> colorAfterConversion(ConversionParams.AfterConversion<Mob> original) {
        if (!CarpetTNGSetting.tadpoleDyeColor || this.carpettng$dyeColor == null) {
            return original;
        }
        ResourceKey<FrogVariant> variantKey = mapVariant(this.carpettng$dyeColor);
        return frog -> {
            original.finalizeConversion(frog);
            if (frog instanceof Frog frogEntity && variantKey != null) {
                frogEntity.setVariant(BuiltInRegistries.FROG_VARIANT.getOrThrow(variantKey));
            }
        };
    }

    /** 染料颜色 1:1 映射到青蛙变体（橙→温带、白→暖、绿→冷）。 */
    @Unique
    private static ResourceKey<FrogVariant> mapVariant(DyeColor color) {
        return switch (color) {
            case ORANGE -> FrogVariant.TEMPERATE;
            case WHITE -> FrogVariant.WARM;
            case GREEN -> FrogVariant.COLD;
            default -> FrogVariant.TEMPERATE; // unreachable (only 3 dyes accepted)
        };
    }
}
