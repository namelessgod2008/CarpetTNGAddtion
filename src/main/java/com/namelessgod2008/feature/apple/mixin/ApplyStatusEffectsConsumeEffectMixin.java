package com.namelessgod2008.feature.apple.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * 恢复旧版（1.9 前）附魔金苹果的再生效果：
 * 再生 V（30 秒）替代新版的再生 II（20 秒），其余效果（伤害吸收 IV、抗性、抗火）保持新版。
 * <p>
 * 注入效果实际应用点 {@code ApplyStatusEffectsConsumeEffect.apply} 并完全接管，
 * 避免依赖食用回调的时序。
 */
@Mixin(ApplyStatusEffectsConsumeEffect.class)
public class ApplyStatusEffectsConsumeEffectMixin {

    @Inject(method = "apply", at = @At("HEAD"), cancellable = true)
    private void applyLegacyGoldenAppleEffects(Level level, ItemStack stack, LivingEntity entity,
                                               CallbackInfoReturnable<Boolean> cir) {
        if (!CarpetTNGSetting.legacyEnchantedGoldenApple) return;
        if (!stack.is(Items.ENCHANTED_GOLDEN_APPLE)) return;

        // 完全接管：新版效果（再生 II 20s、吸收 IV、抗性、抗火）→ 再生改旧版 V 30s，其余保持新版
        entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 4));         // V, 30s
        entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3));         // IV, 2min
        entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 0));  // I, 5min
        entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0));    // I, 5min
        cir.setReturnValue(true);
    }
}
