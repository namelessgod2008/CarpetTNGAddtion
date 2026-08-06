package com.namelessgod2008.feature.creeper.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.level.ServerExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * 阻止恶魂火球爆炸破坏方块：
 * 爆炸仍能造成伤害，但不再破坏方块。
 * <p>
 * {@code LargeFireball.onHit} 用 {@code ExplosionInteraction.MOB} 触发爆炸，
 * {@code ServerExplosion.getIndirectSourceEntity()} 对火球返回其 owner
 * （恶魂 Ghast）。规则开启且间接源是恶魂时 cancel 掉 {@code interactWithBlocks}，
 * 跳过方块破坏，保留伤害/击退/音效。
 * <p>
 * 与 {@code CreeperMixin} 各自独立判断，互不干扰。
 */
@Mixin(ServerExplosion.class)
public class GhastMixin {

    @Inject(method = "interactWithBlocks", at = @At("HEAD"), cancellable = true)
    private void stopGhastBlockDamage(List<?> blocks, CallbackInfo ci) {
        if (!CarpetTNGSetting.stopGhastGriefing) return;
        if (((ServerExplosion) (Object) this).getIndirectSourceEntity() instanceof Ghast) {
            ci.cancel();
        }
    }
}
