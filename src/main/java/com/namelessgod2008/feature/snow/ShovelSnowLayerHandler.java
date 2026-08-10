package com.namelessgod2008.feature.snow;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

/**
 * 手持锹右键雪层方块可以铲掉一层雪：
 * 右键 {@code Blocks.SNOW}（雪层）时，若层数 > 1 则减一层，
 * 若只有 1 层则移除整个方块。原版锹右键雪层无特殊行为（PASS）。
 * <p>
 * 用 Fabric {@code UseBlockCallback}（项目现有 handler 同模式），
 * 规则开启且手持锹 + 目标是雪层时执行并返回 SUCCESS。
 */
public class ShovelSnowLayerHandler {

    public static void register() {
        UseBlockCallback.EVENT.register(ShovelSnowLayerHandler::onUseBlock);
    }

    private static InteractionResult onUseBlock(Player player, Level level, InteractionHand hand,
                                                BlockHitResult hitResult) {
        if (!CarpetTNGSetting.shovelSnowLayer) return InteractionResult.PASS;
        if (!(player.getItemInHand(hand).getItem() instanceof ShovelItem)) return InteractionResult.PASS;
        if (level.isClientSide) return InteractionResult.SUCCESS;

        BlockPos pos = hitResult.getBlockPos();
        BlockState state = level.getBlockState(pos);
        if (!state.is(Blocks.SNOW)) return InteractionResult.PASS;

        int layers = state.getValue(SnowLayerBlock.LAYERS);
        if (layers <= 1) {
            level.removeBlock(pos, false);
        } else {
            level.setBlock(pos, state.setValue(SnowLayerBlock.LAYERS, layers - 1), 11);
        }
        if (CarpetTNGSetting.shovelSnowLayerDropSnowball) {
            // Drop one snowball per layer removed.
            Block.popResource(level, pos, new ItemStack(Items.SNOWBALL));
        }
        level.playSound(null, pos, SoundEvents.SNOW_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
        return InteractionResult.SUCCESS;
    }
}
