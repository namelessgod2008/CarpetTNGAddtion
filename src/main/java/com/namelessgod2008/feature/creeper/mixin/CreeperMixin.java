package com.namelessgod2008.feature.creeper.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.ServerExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * 阻止苦力怕爆炸破坏方块：
 * 爆炸仍能造成伤害和击退，但不再破坏方块。
 * <p>
 * {@code ServerExplosion.explode()} 中实体伤害（interactWithEntities）
 * 与方块破坏（interactWithBlocks）分离。规则开启且爆炸间接源是苦力怕
 * 时 cancel 掉 {@code interactWithBlocks}，跳过方块破坏，保留伤害/击退/音效。
 * <p>
 * 恶魂火球由独立的 {@code GhastMixin} 处理（见 creeper 包）。
 */
@Mixin(ServerExplosion.class)
public class CreeperMixin {

    @Inject(method = "interactWithBlocks", at = @At("HEAD"), cancellable = true)
    private void stopCreeperBlockDamage(List<?> blocks, CallbackInfo ci) {
        if (!CarpetTNGSetting.stopCreeperGriefing) return;
        if (((ServerExplosion) (Object) this).getIndirectSourceEntity() instanceof Creeper) {
            ci.cancel();
        }
    }
}
