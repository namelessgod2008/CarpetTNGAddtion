package com.namelessgod2008.feature.creeper.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.ServerExplosion;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

/**
 * 阻止末地水晶爆炸破坏方块：
 * 末地水晶爆炸仍能造成伤害，但不再破坏方块。
 * <p>
 * 末地水晶爆炸由 {@code EndCrystal.hurtServer} 用 {@code ExplosionInteraction.BLOCK}
 * 触发，其直接源是 EndCrystal 自身（非 LivingEntity，间接源为 null）。
 * 规则开启且爆炸直接源是 EndCrystal 时跳过 {@code ServerExplosion.interactWithBlocks}，
 * 保留伤害/击退/音效。
 */
@Mixin(ServerExplosion.class)
public class EndCrystalExplosionMixin {

    @WrapMethod(method = "interactWithBlocks")
    private void stopEndCrystalBlockDamage(List<?> blocks, Operation<Void> original) {
        if (CarpetTNGSetting.stopEndCrystalGriefing
                && ((ServerExplosion) (Object) this).getDirectSourceEntity() instanceof EndCrystal) {
            return; // 不破坏方块
        }
        original.call(blocks);
    }
}
