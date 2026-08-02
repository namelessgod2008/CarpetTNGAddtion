package com.namelessgod2008.feature.wart;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/**
 * 烈焰粉催熟地狱疣：手持烈焰粉右键地狱疣推进一个生长阶段（blazePowderNetherWartGrowth）；
 * 发射器正对地狱疣喷射烈焰粉也可催熟（dispenserNetherWartGrowth，可联动自动化农场）。
 * 原版地狱疣不可被骨粉催熟（NetherWartBlock 未实现 BonemealItem），本功能补上催熟手段。
 */
public class NetherWartBlazeHandler {

    public static void register() {
        UseBlockCallback.EVENT.register(NetherWartBlazeHandler::onUseBlock);
        DispenserBlock.registerBehavior(Items.BLAZE_POWDER, new BlazeDispenseBehavior());
    }

    private static InteractionResult onUseBlock(Player player, Level world,
                                                net.minecraft.world.InteractionHand hand,
                                                BlockHitResult hitResult) {
        if (!CarpetTNGSetting.blazePowderNetherWartGrowth) return InteractionResult.PASS;
        if (world.isClientSide()) return InteractionResult.PASS;

        ItemStack held = player.getItemInHand(hand);
        if (!held.is(Items.BLAZE_POWDER)) return InteractionResult.PASS;

        BlockPos pos = hitResult.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if (!state.is(Blocks.NETHER_WART) || state.getValue(NetherWartBlock.AGE) >= NetherWartBlock.MAX_AGE) {
            return InteractionResult.PASS;
        }

        growNetherWart(world, pos, state);
        if (!player.getAbilities().instabuild) held.shrink(1);
        return InteractionResult.SUCCESS;
    }

    /** 推进地狱疣一个生长阶段，播烈焰粉颜色粒子与骨粉催熟音效。 */
    private static void growNetherWart(Level world, BlockPos pos, BlockState state) {
        int newAge = state.getValue(NetherWartBlock.AGE) + 1;
        world.setBlock(pos, state.setValue(NetherWartBlock.AGE, newAge), 2);

        if (world instanceof ServerLevel serverLevel) {
            // 粒子位置取催熟后的植株顶部高度（AGE 1/2/3 -> 8/11/14 像素）
            double y = pos.getY() + switch (newAge) {
                case 1 -> 8.0 / 16.0;
                case 2 -> 11.0 / 16.0;
                default -> 14.0 / 16.0;
            };
            serverLevel.sendParticles(new DustParticleOptions(0xFFB400, 1.0f),
                    pos.getX() + 0.5, y, pos.getZ() + 0.5, 6, 0.25, 0.1, 0.25, 0.02);
        }
        world.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    /** 发射器行为：对正前方未成熟的地狱疣催熟一级，成功消耗 1 个烈焰粉。 */
    private static class BlazeDispenseBehavior extends OptionalDispenseItemBehavior {
        @Override
        protected ItemStack execute(BlockSource pointer, ItemStack stack) {
            // 发射器功能依赖右键催熟规则：两者都开启才生效
            if (!CarpetTNGSetting.dispenserNetherWartGrowth || !CarpetTNGSetting.blazePowderNetherWartGrowth) {
                return super.execute(pointer, stack);
            }

            Level world = pointer.level();
            Direction facing = pointer.state().getValue(DispenserBlock.FACING);
            BlockPos target = pointer.pos().relative(facing);
            BlockState state = world.getBlockState(target);

            if (state.is(Blocks.NETHER_WART) && state.getValue(NetherWartBlock.AGE) < NetherWartBlock.MAX_AGE) {
                growNetherWart(world, target, state);
                stack.shrink(1);
                setSuccess(true);
            } else {
                setSuccess(false);
            }
            return stack;
        }
    }
}
