package com.namelessgod2008.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Persistent storage for potion-filled cauldrons, saved per-dimension with the world.
 */
public class CauldronSavedData extends SavedData {

    private static final String DATA_NAME = "carpettngaddtion_cauldrons";
    private static final String KEY_POS = "pos";
    private static final String KEY_POTION = "potion";
    private static final String KEY_TYPE = "type";
    private static final String KEY_SIXTHS = "sixths";

    private final Map<BlockPos, Entry> cauldrons = new HashMap<>();

    record Entry(PotionContents potion, String bottleType, int sixths) {}

    // ---- Factory ----

    private static final Supplier<CauldronSavedData> CONSTRUCTOR = CauldronSavedData::new;

    private static final SavedData.Factory<CauldronSavedData> FACTORY =
            new SavedData.Factory<>(CONSTRUCTOR, CauldronSavedData::load, null);

    static CauldronSavedData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(FACTORY, DATA_NAME);
    }

    // ---- Access ----

    Map<BlockPos, Entry> entries() {
        return cauldrons;
    }

    void put(BlockPos pos, PotionContents potion, String bottleType, int sixths) {
        cauldrons.put(pos, new Entry(potion, bottleType, sixths));
        setDirty();
    }

    void remove(BlockPos pos) {
        if (cauldrons.remove(pos) != null) {
            setDirty();
        }
    }

    void clearAll() {
        if (!cauldrons.isEmpty()) {
            cauldrons.clear();
            setDirty();
        }
    }

    // ---- Serialization ----

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        ListTag list = new ListTag();
        for (var e : cauldrons.entrySet()) {
            BlockPos pos = e.getKey();
            Entry entry = e.getValue();

            CompoundTag item = new CompoundTag();
            item.putLong(KEY_POS, pos.asLong());
            // Serialize PotionContents via its codec
            Tag potionTag = PotionContents.CODEC.encodeStart(NbtOps.INSTANCE, entry.potion).getOrThrow();
            item.put(KEY_POTION, potionTag);
            item.putString(KEY_TYPE, entry.bottleType);
            item.putInt(KEY_SIXTHS, entry.sixths);

            list.add(item);
        }
        tag.put("cauldrons", list);
        return tag;
    }

    private static CauldronSavedData load(CompoundTag tag, HolderLookup.Provider registries) {
        CauldronSavedData data = new CauldronSavedData();
        ListTag list = tag.getList("cauldrons", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag item = list.getCompound(i);
            BlockPos pos = BlockPos.of(item.getLong(KEY_POS));
            PotionContents potion = PotionContents.CODEC
                    .parse(NbtOps.INSTANCE, item.get(KEY_POTION))
                    .getOrThrow();
            String type = item.getString(KEY_TYPE);
            int sixths = item.getInt(KEY_SIXTHS);

            data.cauldrons.put(pos, new Entry(potion, type, sixths));
        }
        return data;
    }
}
