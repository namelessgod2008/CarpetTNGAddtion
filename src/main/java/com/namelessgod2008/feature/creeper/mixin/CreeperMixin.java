package com.namelessgod2008.feature.creeper.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.ServerExplosion;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

/**
 * 阻止苦力怕爆炸破坏方块：
 * 爆炸仍能造成伤害和击退，但不再破坏方块。
 * <p>
 * {@code ServerExplosion.explode()} 中实体伤害（interactWithEntities）
 * 与方块破坏（interactWithBlocks）分离。规则开启且爆炸间接源是苦力怕
 * 时跳过 {@code interactWithBlocks}（不调用原方法），保留伤害/击退/音效。
 * <p>
 * 恶魂火球由独立的 {@code GhastMixin} 处理（见 creeper 包）。
 */
@Mixin(ServerExplosion.class)
public class CreeperMixin {

    @WrapMethod(method = "interactWithBlocks")
    private void stopCreeperBlockDamage(List<?> blocks, Operation<Void> original) {
        if (CarpetTNGSetting.stopCreeperGriefing
                && ((ServerExplosion) (Object) this).getIndirectSourceEntity() instanceof Creeper) {
            return; // 不破坏方块
        }
        original.call(blocks);
    }
}
