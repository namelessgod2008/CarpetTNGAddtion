package com.namelessgod2008.feature.dispenser;

import net.minecraft.nbt.CompoundTag;

/**
 * 由 {@code ItemEntityMixin} 实现，用于在实体上存取种子飞行数据。
 * 数据存于实体自身而非物品，避免污染物品 NBT 导致无法堆叠。
 */
public interface PlantingSeedAccessor {

    CompoundTag carpettng$getPlantingData();

    void carpettng$setPlantingData(CompoundTag tag);
}
