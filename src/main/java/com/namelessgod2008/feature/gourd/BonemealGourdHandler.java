package com.namelessgod2008.feature.gourd;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 骨粉产瓜：对成熟的西瓜/南瓜瓜苗（AGE 7）使用骨粉时，按规则选项概率结出果实。
 * <p>
 * 两条规则共用 {@link #tryUseBonemealOnMatureStem}：
 * <ul>
 *   <li>bonemealGourdFruit（手持）：UseBlockCallback 拦截"骨粉 + 成熟瓜苗"</li>
 *   <li>dispenserGourdFruit（发射器）：DispenseItemBehavior，依赖 bonemealGourdFruit > 0</li>
 * </ul>
 * 结瓜语义（规则值 = 结出瓜的概率）：
 * <ul>
 *   <li>先收集四周可结瓜空位；四方向全被占 → 不消耗骨粉（手持返回 PASS，发射器回退弹出）</li>
 *   <li>有空位 → 概率判定，通过则从空位随机选一方向结瓜（概率 1 时只要有空位必结）</li>
 *   <li>概率未通过 → 消耗 1 骨粉但不出瓜（一次骨粉 = 一次产瓜机会）</li>
 * </ul>
 * 原版骨粉对成熟瓜苗无效：{@code StemBlock.isValidBonemealTarget} 仅 AGE != 7 时返回 true。
 */
public class BonemealGourdHandler {

    public static void register() {
        UseBlockCallback.EVENT.register(BonemealGourdHandler::onUseBlock);
        DispenserBlock.registerBehavior(Items.BONE_MEAL, new DispenserGourdBehavior());
    }

    private static InteractionResult onUseBlock(Player player, Level world,
                                                net.minecraft.world.InteractionHand hand,
                                                BlockHitResult hitResult) {
        if (CarpetTNGSetting.bonemealGourdFruit <= 0.0) return InteractionResult.PASS;
        if (world.isClientSide()) return InteractionResult.PASS;

        ItemStack held = player.getItemInHand(hand);
        if (!held.is(Items.BONE_MEAL)) return InteractionResult.PASS;

        BlockPos pos = hitResult.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if (!(state.getBlock() instanceof StemBlock) || state.getValue(StemBlock.AGE) != StemBlock.MAX_AGE) {
            return InteractionResult.PASS;
        }

        // 四方向全被占：不消耗骨粉，让原版处理（原版对成熟瓜苗骨粉无效，也不消耗）
        if (!tryUseBonemealOnMatureStem(world, pos, state)) {
            return InteractionResult.PASS;
        }

        // 概率未通过也消耗骨粉（一次骨粉 = 一次产瓜机会）
        if (!player.getAbilities().instabuild) held.shrink(1);
        return InteractionResult.SUCCESS;
    }

    /**
     * 对成熟瓜苗使用骨粉催熟。返回 true 表示消耗了骨粉（概率未中也消耗）；
     * false 表示四方向全被占、无法结瓜（不消耗，调用方应回退）。
     */
    private static boolean tryUseBonemealOnMatureStem(Level world, BlockPos pos, BlockState state) {
        // 收集四周可结瓜的空位（参照原版 randomTick 的位置条件：相邻格空气 + 下方耕地/泥土）
        List<Direction> available = new ArrayList<>(4);
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos fruitPos = pos.relative(direction);
            BlockState below = world.getBlockState(fruitPos.below());
            if (world.getBlockState(fruitPos).isAir() && (below.is(Blocks.FARMLAND) || below.is(BlockTags.DIRT))) {
                available.add(direction);
            }
        }
        if (available.isEmpty()) {
            return false; // 四周全被占：无法结瓜，不消耗
        }

        // 概率判定：通过则从空位中随机选一个方向结瓜
        if (world.random.nextDouble() < CarpetTNGSetting.bonemealGourdFruit) {
            Direction direction = available.get(world.random.nextInt(available.size()));
            growFruit(world, pos, state, direction);
        }
        return true;
    }

    /** 在指定方向结瓜：相邻格放果实，瓜苗原地变 attached stem（FACING 朝向果实）。 */
    private static void growFruit(Level world, BlockPos pos, BlockState stem, Direction direction) {
        BlockPos fruitPos = pos.relative(direction);

        if (stem.is(Blocks.MELON_STEM)) {
            world.setBlockAndUpdate(fruitPos, Blocks.MELON.defaultBlockState());
            world.setBlockAndUpdate(pos, Blocks.ATTACHED_MELON_STEM.defaultBlockState()
                    .setValue(HorizontalDirectionalBlock.FACING, direction));
        } else if (stem.is(Blocks.PUMPKIN_STEM)) {
            world.setBlockAndUpdate(fruitPos, Blocks.PUMPKIN.defaultBlockState());
            world.setBlockAndUpdate(pos, Blocks.ATTACHED_PUMPKIN_STEM.defaultBlockState()
                    .setValue(HorizontalDirectionalBlock.FACING, direction));
        } else {
            return;
        }

        world.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
        if (world instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                    pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5, 6, 0.3, 0.2, 0.3, 0.05);
        }
    }

    /** 发射器行为：对正前方成熟瓜苗喷骨粉，按骨粉产瓜概率结瓜；目标不匹配时回退原版弹出。 */
    private static class DispenserGourdBehavior extends OptionalDispenseItemBehavior {
        @Override
        protected ItemStack execute(BlockSource pointer, ItemStack stack) {
            // 发射器产瓜依赖：发射器规则开启 且 骨粉产瓜概率 > 0
            if (!CarpetTNGSetting.dispenserGourdFruit || CarpetTNGSetting.bonemealGourdFruit <= 0.0) {
                return super.execute(pointer, stack);
            }

            Level world = pointer.level();
            Direction facing = pointer.state().getValue(DispenserBlock.FACING);
            BlockPos target = pointer.pos().relative(facing);
            BlockState state = world.getBlockState(target);

            if (state.getBlock() instanceof StemBlock && state.getValue(StemBlock.AGE) == StemBlock.MAX_AGE
                    && tryUseBonemealOnMatureStem(world, target, state)) {
                stack.shrink(1);
                setSuccess(true);
                return stack;
            }
            // 目标不是可催熟成熟瓜苗（或四方向卡死）：回退原版弹出骨粉
            return super.execute(pointer, stack);
        }
    }
}
