package com.namelessgod2008.feature.blazestick;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.SmokerBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/**
 * 烈焰棒掏炉渣系列（依赖 blazeStickDebug），按方块类型分别由三条规则控制：
 * <ul>
 *   <li>blazeStickFurnaceXp：熔炉（FurnaceBlock）</li>
 *   <li>blazeStickSmokerXp：烟熏炉（SmokerBlock）</li>
 *   <li>blazeStickBlastFurnaceXp：高炉（BlastFurnaceBlock）</li>
 * </ul>
 * 手持烈焰棒右键对应方块时——
 * <ul>
 *   <li>不打开 GUI（返回 SUCCESS 阻止原版菜单）</li>
 *   <li>调用 {@code AbstractFurnaceBlockEntity.getRecipesToAwardAndPopExperience}：
 *       积累的全部经验以经验球出现在玩家所在坐标，随后清空经验计数（可随时再掏）</li>
 * </ul>
 */
public class BlazeStickFurnaceXpHandler {

    public static void register() {
        UseBlockCallback.EVENT.register(BlazeStickFurnaceXpHandler::onUseBlock);
    }

    private static InteractionResult onUseBlock(Player player, Level world,
                                                net.minecraft.world.InteractionHand hand,
                                                BlockHitResult hitResult) {
        // 附属规则：烈焰棒调试开启 且 对应方块规则开启 才生效
        if (!CarpetTNGSetting.blazeStickDebug) {
            return InteractionResult.PASS;
        }
        if (world.isClientSide()) return InteractionResult.PASS;

        ItemStack held = player.getItemInHand(hand);
        if (!held.is(Items.BLAZE_ROD)) return InteractionResult.PASS;

        BlockState state = world.getBlockState(hitResult.getBlockPos());
        boolean enabled;
        if (state.getBlock() instanceof FurnaceBlock) {
            enabled = CarpetTNGSetting.blazeStickFurnaceXp;
        } else if (state.getBlock() instanceof SmokerBlock) {
            enabled = CarpetTNGSetting.blazeStickSmokerXp;
        } else if (state.getBlock() instanceof BlastFurnaceBlock) {
            enabled = CarpetTNGSetting.blazeStickBlastFurnaceXp;
        } else {
            return InteractionResult.PASS;
        }
        if (!enabled) return InteractionResult.PASS;

        if (world.getBlockEntity(hitResult.getBlockPos()) instanceof AbstractFurnaceBlockEntity furnace
                && furnace instanceof BlazeStickFurnaceAccessor accessor
                && world instanceof ServerLevel serverLevel) {
            // 掉经验球在玩家位置（原版方法只掉经验不清空计数）
            furnace.getRecipesToAwardAndPopExperience(serverLevel, player.position());
            accessor.carpettng$clearRecipesUsed(); // 清空经验计数：否则每次掏都会按完整计数重复掉经验
            return InteractionResult.SUCCESS; // 阻止打开 GUI
        }
        return InteractionResult.PASS;
    }
}
