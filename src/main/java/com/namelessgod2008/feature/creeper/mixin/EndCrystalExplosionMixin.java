package com.namelessgod2008.feature.creeper.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.ServerExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * 阻止末地水晶爆炸破坏方块：
 * 末地水晶爆炸仍能造成伤害，但不再破坏方块。
 * <p>
 * 末地水晶爆炸由 {@code EndCrystal.hurtServer} 用 {@code ExplosionInteraction.BLOCK}
 * 触发，其直接源是 EndCrystal 自身（非 LivingEntity，间接源为 null）。
 * 规则开启且爆炸直接源是 EndCrystal 时 cancel 掉 {@code ServerExplosion.interactWithBlocks}，
 * 跳过方块破坏，保留伤害/击退/音效。
 */
@Mixin(ServerExplosion.class)
public class EndCrystalExplosionMixin {

    @Inject(method = "interactWithBlocks", at = @At("HEAD"), cancellable = true)
    private void stopEndCrystalBlockDamage(List<?> blocks, CallbackInfo ci) {
        if (!CarpetTNGSetting.stopEndCrystalGriefing) return;
        if (((ServerExplosion) (Object) this).getDirectSourceEntity() instanceof EndCrystal) {
            ci.cancel();
        }
    }
}
