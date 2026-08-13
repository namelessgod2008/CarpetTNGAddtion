package com.namelessgod2008.feature.dispenser;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;

/**
 * 发射器修复铁傀儡：红石激活发射器时，若发射口正前方一格内存在受伤的铁傀儡，
 * 消耗 1 个铁锭为其恢复 25 点生命值（回复至满血为止，语义同原版玩家手持铁锭右键
 * {@link IronGolem#mobInteract}）。铁傀儡满血时不消耗铁锭（发射器播放失败音效）。
 * <p>
 * 规则关闭时回退原版喷出行为（{@code super.execute}）。
 */
public class DispenserIronGolemRepairHandler {

    /** 单次修复生命值，与原版 {@code IronGolem.IRON_INGOT_HEAL_AMOUNT} 一致。 */
    private static final float HEAL_AMOUNT = 25.0F;

    public static void register() {
        DispenseItemBehavior behavior = new IronGolemRepairDispenseBehavior();
        DispenserBlock.registerBehavior(Items.IRON_INGOT, behavior);
    }

    private static class IronGolemRepairDispenseBehavior extends OptionalDispenseItemBehavior {
        @Override
        protected ItemStack execute(BlockSource pointer, ItemStack stack) {
            if (!CarpetTNGSetting.dispenserIronGolemRepair) {
                return super.execute(pointer, stack);
            }

            ServerLevel level = pointer.level();
            BlockPos pos = pointer.pos().relative(pointer.state().getValue(DispenserBlock.FACING));
            IronGolem golem = findHurtGolem(level, pos);

            if (golem == null) {
                // 前方无可修复铁傀儡：按原版喷出
                return super.execute(pointer, stack);
            }

            // 语义同原版 mobInteract：先治疗再判断是否有效（避免满血时消耗）
            float oldHealth = golem.getHealth();
            golem.heal(HEAL_AMOUNT);
            if (golem.getHealth() == oldHealth) {
                // 满血无效果：不消耗铁锭，发射器播放失败音效（setSuccess(false)）
                setSuccess(false);
                return stack;
            }

            float pitch = 1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F;
            golem.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, pitch);
            stack.shrink(1);
            setSuccess(true);
            return stack;
        }
    }

    /** 查找发射口前方一格 AABB 内第一个受伤的铁傀儡（排除旁观者）。 */
    private static IronGolem findHurtGolem(ServerLevel level, BlockPos pos) {
        for (IronGolem golem : level.getEntitiesOfClass(IronGolem.class, new AABB(pos), EntitySelector.NO_SPECTATORS)) {
            if (golem.getHealth() < golem.getMaxHealth()) {
                return golem;
            }
        }
        return null;
    }
}
