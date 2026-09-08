package com.namelessgod2008.feature.reinforceddeepslate.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 强化深板岩变为可获取：
 * <ul>
 *   <li>{@code getDestroySpeed}：挖掘时间与黑曜石相同（hardness 55 → 50）</li>
 *   <li>{@code spawnAfterBreak}：方块被破坏后掉落自身（原版掉落表为空，不掉任何东西）</li>
 * </ul>
 * 注入 {@code BlockBehaviour$BlockStateBase}（两方法都声明在该内部类，非 {@code BlockBehaviour}）。
 * "仅钻石/下界合金镐可挖"由 {@link ReinforcedDeepslatePlayerMixin} 控制掉落触发。
 * <p>
 * 注：注入内部类方法时 handler 不带 self 参数，
 * 通过 {@code (BlockBehaviour.BlockStateBase)(Object)this} 访问目标实例。
 */
@Mixin(targets = "net.minecraft.world.level.block.state.BlockBehaviour$BlockStateBase")
public class ReinforcedDeepslateBlockStateMixin {

    /** 黑曜石的 hardness，作为强化深板岩的目标挖掘时间。 */
    private static final float OBSIDIAN_DESTROY_SPEED = 50.0F;

    @WrapMethod(method = "getDestroySpeed")
    private float deepslateGetDestroySpeed(BlockGetter level, BlockPos pos, Operation<Float> original) {
        float speed = original.call(level, pos);
        if (CarpetTNGSetting.collectableReinforcedDeepslate
                && ((BlockBehaviour.BlockStateBase) (Object) this).getBlock() == Blocks.REINFORCED_DEEPSLATE) {
            return OBSIDIAN_DESTROY_SPEED;
        }
        return speed;
    }

    @Inject(method = "spawnAfterBreak", at = @At("HEAD"))
    private void deepslateSpawnAfterBreak(ServerLevel level, BlockPos pos,
                                          ItemStack stack, boolean dropExperience,
                                          CallbackInfo ci) {
        if (CarpetTNGSetting.collectableReinforcedDeepslate
                && ((BlockBehaviour.BlockStateBase) (Object) this).getBlock() == Blocks.REINFORCED_DEEPSLATE) {
            net.minecraft.world.level.block.Block.popResource(level, pos,
                    new ItemStack(Blocks.REINFORCED_DEEPSLATE));
        }
    }
}
