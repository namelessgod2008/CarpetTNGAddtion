package com.namelessgod2008.feature.anvil.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * 耐摔的铁砧：铁砧方块下落落地时不因摔落损坏（原版 5% + 每格 5% 概率降一级，
 * 最低级直接消失）。铁砧仍可被挖掘等物理破坏。
 */
@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin {

    @Redirect(method = "causeFallDamage",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;nextFloat()F"))
    private float durableFallingAnvil(RandomSource random) {
        // 规则开启：让损坏判定（nextFloat() < 0.05 + i*0.05）不成立；否则原样
        return CarpetTNGSetting.durableFallingAnvil ? 1.0f : random.nextFloat();
    }
}
