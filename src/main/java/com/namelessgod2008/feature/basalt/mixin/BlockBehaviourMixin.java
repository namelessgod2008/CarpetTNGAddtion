package com.namelessgod2008.feature.basalt.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 玄武岩转化为黑石：同时接触到熔岩和水的玄武岩会转化为黑石。
 * <p>
 * 玄武岩在 Java 版是普通 {@link Block}（无自有 onPlace/neighborChanged 逻辑），
 * 因此注入声明这两个方法的基类 {@link BlockBehaviour}，通过方块过滤仅处理玄武岩：
 * <ul>
 *   <li>{@code onPlace}：玄武岩被放置时，检查周围是否已有熔岩和水；</li>
 *   <li>{@code neighborChanged}：玄武岩的相邻方块变化（熔岩/水流到它旁边、或液体被放置/移除）时检查。</li>
 * </ul>
 * "同时接触"判定：玄武岩 6 个方向的相邻方块中，至少一面流体为熔岩、至少一面为水
 * （含水方块/含水状态的流体也计入，与 {@code getFluidState().is(FluidTags.WATER)} 一致）。
 */
@Mixin(BlockBehaviour.class)
public abstract class BlockBehaviourMixin {

    @Inject(method = "onPlace", at = @At("HEAD"))
    private void basaltOnPlace(BlockState state, Level level, BlockPos pos,
                               BlockState oldState, boolean movedByPiston, CallbackInfo ci) {
        if (state.is(Blocks.BASALT)) {
            checkBasaltConversion(level, pos);
        }
    }

    @Inject(method = "neighborChanged", at = @At("HEAD"))
    private void basaltNeighborChanged(BlockState state, Level level, BlockPos pos,
                                       net.minecraft.world.level.block.Block neighborBlock,
                                       @Nullable Orientation orientation,
                                       boolean movedByPiston, CallbackInfo ci) {
        if (state.is(Blocks.BASALT)) {
            checkBasaltConversion(level, pos);
        }
    }

    /** 玄武岩同时接触熔岩和水则转化为黑石。 */
    private static void checkBasaltConversion(Level level, BlockPos pos) {
        if (!CarpetTNGSetting.basaltToBlackstoneConversion) return;
        if (level.isClientSide()) return;

        boolean hasLava = false;
        boolean hasWater = false;
        for (Direction direction : Direction.values()) {
            BlockPos neighbor = pos.relative(direction);
            if (level.getFluidState(neighbor).is(FluidTags.LAVA)) {
                hasLava = true;
            }
            if (level.getFluidState(neighbor).is(FluidTags.WATER)) {
                hasWater = true;
            }
            if (hasLava && hasWater) {
                level.setBlockAndUpdate(pos, Blocks.BLACKSTONE.defaultBlockState());
                level.levelEvent(1501, pos, 0);
                return;
            }
        }
    }
}
