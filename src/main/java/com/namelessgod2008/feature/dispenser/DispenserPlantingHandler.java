package com.namelessgod2008.feature.dispenser;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.item.ItemEntity;

import java.util.Map;

/**
 * 发射器种植：红石激活发射器时，种子以实体形式沿直线轨道飞行，
 * 碰到耕地变为作物幼苗；未碰到耕地则落地成为可拾取的普通掉落物。
 * <p>
 * 规则开启时始终走本功能路径（不回退原版喷出）。
 * 飞行数据存于实体（见 {@link PlantingSeedAccessor}），物品本身无 NBT。
 */
public class DispenserPlantingHandler {

    /** 种子 -> 作物幼苗映射。 */
    private static final Map<Item, Block> SEED_TO_CROP = Map.of(
            Items.WHEAT_SEEDS, Blocks.WHEAT,
            Items.CARROT, Blocks.CARROTS,
            Items.POTATO, Blocks.POTATOES,
            Items.BEETROOT_SEEDS, Blocks.BEETROOTS,
            Items.MELON_SEEDS, Blocks.MELON_STEM,
            Items.PUMPKIN_SEEDS, Blocks.PUMPKIN_STEM
    );

    /** 直线飞行速度（格/tick）。 */
    public static final double SPEED = 0.6;
    /** 飞行最大 tick 数，超过后种子落地为普通掉落物。 */
    public static final int MAX_TICKS = 15;

    public static final String KEY_DX = "plant_dx";
    public static final String KEY_DY = "plant_dy";
    public static final String KEY_DZ = "plant_dz";
    public static final String KEY_TICKS = "plant_ticks";
    public static final String KEY_CROP = "plant_crop";

    public static void register() {
        DispenseItemBehavior behavior = new SeedPlantingDispenseBehavior();
        for (Item seed : SEED_TO_CROP.keySet()) {
            DispenserBlock.registerBehavior(seed, behavior);
        }
    }

    public static Block getCrop(String id) {
        return BuiltInRegistries.BLOCK.getValue(net.minecraft.resources.ResourceLocation.parse(id));
    }

    private static class SeedPlantingDispenseBehavior extends OptionalDispenseItemBehavior {
        @Override
        protected ItemStack execute(BlockSource pointer, ItemStack stack) {
            if (!CarpetTNGSetting.dispenserPlanting) {
                return super.execute(pointer, stack);
            }

            Level level = pointer.level();
            Direction facing = pointer.state().getValue(DispenserBlock.FACING);
            Vec3 center = pointer.center();

            // 从发射口喷出 1 颗种子（实体飞行）。
            // y 补偿 -0.4：原版掉落物渲染在实体位置上方 0.35~0.45 格
            // （ItemEntityRenderer 的 translate(0, g + 0.25*h, 0)），
            // 补偿后种子视觉上正好从发射器嘴中心飞出。
            ItemStack seed = stack.split(1);
            ItemEntity entity = new ItemEntity(level,
                    center.x() + facing.getStepX() * 0.7,
                    center.y() + facing.getStepY() * 0.7 - 0.4,
                    center.z() + facing.getStepZ() * 0.7,
                    seed);

            // 飞行数据存于实体自身（不污染物品 NBT，保证可堆叠）
            CompoundTag flight = new CompoundTag();
            flight.putDouble(KEY_DX, facing.getStepX() * SPEED);
            flight.putDouble(KEY_DY, facing.getStepY() * SPEED);
            flight.putDouble(KEY_DZ, facing.getStepZ() * SPEED);
            flight.putInt(KEY_TICKS, 0);
            flight.putString(KEY_CROP, BuiltInRegistries.BLOCK.getKey(SEED_TO_CROP.get(seed.getItem())).toString());
            ((PlantingSeedAccessor) entity).carpettng$setPlantingData(flight);

            level.addFreshEntity(entity);

            setSuccess(true);
            playSound(pointer);
            playAnimation(pointer, facing);
            return stack;
        }
    }
}
