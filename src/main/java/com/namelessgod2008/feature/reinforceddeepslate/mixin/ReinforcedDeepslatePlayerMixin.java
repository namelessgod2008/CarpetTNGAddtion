package com.namelessgod2008.feature.reinforceddeepslate.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @Inject(method = "hasCorrectToolForDrops", at = @At("RETURN"), cancellable = true)
    private void deepslateCorrectTool(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (!CarpetTNGSetting.collectableReinforcedDeepslate
                || !state.is(Blocks.REINFORCED_DEEPSLATE)) {
            return;
        }
        Player self = (Player) (Object) this;
        var hand = self.getMainHandItem();
        cir.setReturnValue(hand.is(Items.DIAMOND_PICKAXE) || hand.is(Items.NETHERITE_PICKAXE));
    }
}
