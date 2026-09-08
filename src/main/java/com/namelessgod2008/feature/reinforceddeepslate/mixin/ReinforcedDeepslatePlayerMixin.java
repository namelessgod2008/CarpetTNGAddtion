package com.namelessgod2008.feature.reinforceddeepslate.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

/**
 * 强化深板岩"仅钻石/下界合金镐可挖"：
 * 注入 {@link Player#hasCorrectToolForDrops(BlockState)}，
 * 规则开启且目标是强化深板岩时，只有手持钻石镐或下界合金镐才返回 true。
 * <p>
 * 该方法的返回值同时决定两件事：
 * 1. 挖掘进度倍率（{@code BlockBehaviour.getDestroyProgress} 里正确工具 30 / 无正确工具 100）
 * 2. 破坏时是否触发掉落（{@code ServerPlayerGameMode.destroyBlock} 里 bl2 判断）
 * 语义与黑曜石一致。
 */
@Mixin(Player.class)
public class ReinforcedDeepslatePlayerMixin {

    @WrapMethod(method = "hasCorrectToolForDrops")
    private boolean deepslateCorrectTool(BlockState state, Operation<Boolean> original) {
        boolean hasTool = original.call(state);
        if (!CarpetTNGSetting.collectableReinforcedDeepslate
                || !state.is(Blocks.REINFORCED_DEEPSLATE)) {
            return hasTool;
        }
        Player self = (Player) (Object) this;
        var hand = self.getMainHandItem();
        return hand.is(Items.DIAMOND_PICKAXE) || hand.is(Items.NETHERITE_PICKAXE);
    }
}
