package com.namelessgod2008.feature;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CauldronArrowHandler {
    private static final int SIXTHS_PER_BOTTLE = 2;
    private static final int MAX_SIXTHS = 6;
    private static final int PARTICLE_TICK_INTERVAL = 10;

    private static final Map<BlockPos, CauldronData> POTION_CAULDRONS = new ConcurrentHashMap<>();

    private enum BottleType { NORMAL, SPLASH, LINGERING }

    private record CauldronData(PotionContents potion, BottleType bottleType, int sixths) {}

    // ---- Registration ----

    public static void register() {
        UseBlockCallback.EVENT.register(CauldronArrowHandler::onUseBlock);
        PlayerBlockBreakEvents.BEFORE.register(CauldronArrowHandler::onBlockBreak);
        ServerTickEvents.END_SERVER_TICK.register(CauldronArrowHandler::onServerTick);
        ServerWorldEvents.LOAD.register(CauldronArrowHandler::onWorldLoad);
    }

    // ---- Block state helper ----

    private static void updateBlockState(Level world, BlockPos pos, CauldronData data) {
        if (data == null || data.sixths <= 0) {
            world.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
        } else {
            int waterLevel = Math.max(1, Math.min(3, (data.sixths + 1) / 2));
            world.setBlock(pos, Blocks.WATER_CAULDRON.defaultBlockState()
                    .setValue(LayeredCauldronBlock.LEVEL, waterLevel), 3);
        }
    }

    // ---- Persistence ----

    private static void saveToDisk(Level world, BlockPos pos, CauldronData data) {
        if (world instanceof ServerLevel serverLevel) {
            if (data == null) {
                CauldronSavedData.get(serverLevel).remove(pos);
            } else {
                CauldronSavedData.get(serverLevel).put(pos, data.potion,
                        data.bottleType.name(), data.sixths);
            }
        }
    }

    private static void onWorldLoad(net.minecraft.server.MinecraftServer server, ServerLevel level) {
        CauldronSavedData saved = CauldronSavedData.get(level);
        for (var entry : saved.entries().entrySet()) {
            BlockPos pos = entry.getKey();
            CauldronSavedData.Entry e = entry.getValue();
            BottleType type = BottleType.valueOf(e.bottleType());
            CauldronData data = new CauldronData(e.potion(), type, e.sixths());
            POTION_CAULDRONS.put(pos, data);
            // Restore water cauldron block state
            updateBlockState(level, pos, data);
        }
    }

    // ---- Particles ----

    /** Returns the water surface Y offset (0..1) for the given fill level in sixths. */
    private static double waterSurfaceY(int sixths) {
        int waterLevel = Math.max(1, Math.min(3, (sixths + 1) / 2));
        return switch (waterLevel) {
            case 1 -> 9.0 / 16.0;
            case 2 -> 12.0 / 16.0;
            default -> 15.0 / 16.0; // level 3 (full)
        };
    }

    private static void spawnPotionParticles(Level world, BlockPos pos, int color, int sixths) {
        if (!(world instanceof ServerLevel serverLevel)) return;
        var dust = new DustParticleOptions(color, 1.2f);
        double x = pos.getX() + 0.5;
        double y = pos.getY() + waterSurfaceY(sixths) + 0.05;
        double z = pos.getZ() + 0.5;
        serverLevel.sendParticles(dust, x, y, z, 8, 0.25, 0.05, 0.25, 0.02);
    }

    private static void onServerTick(net.minecraft.server.MinecraftServer server) {
        if (server.getTickCount() % PARTICLE_TICK_INTERVAL != 0) return;
        for (var entry : POTION_CAULDRONS.entrySet()) {
            BlockPos pos = entry.getKey();
            CauldronData data = entry.getValue();
            for (ServerLevel level : server.getAllLevels()) {
                if (level.isLoaded(pos)) {
                    int color = data.potion.getColor();
                    var dust = new DustParticleOptions(color, 0.8f);
                    double x = pos.getX() + 0.5;
                    double y = pos.getY() + waterSurfaceY(data.sixths) + 0.05;
                    double z = pos.getZ() + 0.5;
                    level.sendParticles(dust, x, y, z, 2, 0.2, 0.03, 0.2, 0.01);
                    break;
                }
            }
        }
    }

    // ---- Event handlers ----

    private static InteractionResult onUseBlock(Player player, Level world,
                                                net.minecraft.world.InteractionHand hand,
                                                BlockHitResult hitResult) {
        if (!CarpetTNGSetting.bedrockCauldronTippedArrows) return InteractionResult.PASS;
        if (world.isClientSide()) return InteractionResult.PASS;

        BlockPos pos = hitResult.getBlockPos();
        BlockState state = world.getBlockState(pos);

        if (!state.is(Blocks.CAULDRON) && !state.is(Blocks.WATER_CAULDRON)) return InteractionResult.PASS;
        if (state.is(Blocks.WATER_CAULDRON) && !POTION_CAULDRONS.containsKey(pos)) return InteractionResult.PASS;

        ItemStack held = player.getItemInHand(hand);

        if (isPotionItem(held)) return handlePotionPour(player, world, pos, held);
        if (held.is(Items.GLASS_BOTTLE)) return handleBottleExtract(player, world, pos, held);
        if (held.is(Items.ARROW)) return handleArrowDip(player, world, pos, held);

        // Block vanilla interactions (bucket, water bucket, etc.) on potion cauldrons
        if (POTION_CAULDRONS.containsKey(pos)) return InteractionResult.SUCCESS;

        return InteractionResult.PASS;
    }

    private static boolean onBlockBreak(Level world, Player player, BlockPos pos,
                                        BlockState state, /* nullable */ net.minecraft.world.level.block.entity.BlockEntity blockEntity) {
        if (POTION_CAULDRONS.containsKey(pos)) {
            CauldronData broken = POTION_CAULDRONS.get(pos);
            POTION_CAULDRONS.remove(pos);
            updateBlockState(world, pos, null);
            saveToDisk(world, pos, null);
            spawnPotionParticles(world, pos, broken.potion.getColor(), broken.sixths);
        }
        return true;
    }

    // ---- Potion helpers ----

    private static boolean isPotionItem(ItemStack stack) {
        return stack.is(Items.POTION) || stack.is(Items.SPLASH_POTION) || stack.is(Items.LINGERING_POTION);
    }

    private static boolean isBasePotion(PotionContents contents) {
        return !contents.getAllEffects().iterator().hasNext();
    }

    private static BottleType getBottleType(ItemStack stack) {
        if (stack.is(Items.SPLASH_POTION)) return BottleType.SPLASH;
        if (stack.is(Items.LINGERING_POTION)) return BottleType.LINGERING;
        return BottleType.NORMAL;
    }

    private static ItemStack createPotionStack(BottleType type) {
        return switch (type) {
            case SPLASH -> new ItemStack(Items.SPLASH_POTION);
            case LINGERING -> new ItemStack(Items.LINGERING_POTION);
            case NORMAL -> new ItemStack(Items.POTION);
        };
    }

    private static boolean effectsMatch(PotionContents a, PotionContents b) {
        if (!a.potion().equals(b.potion())) return false;
        var ae = a.customEffects().listIterator();
        var be = b.customEffects().listIterator();
        while (ae.hasNext() && be.hasNext()) {
            var ai = ae.next();
            var bi = be.next();
            if (!ai.getEffect().equals(bi.getEffect())) return false;
            if (ai.getAmplifier() != bi.getAmplifier()) return false;
        }
        return !ae.hasNext() && !be.hasNext();
    }

    // ---- Arrow table ----

    private static int maxArrowsAt(int sixths) {
        return switch (sixths) {
            case 6 -> 64;
            case 5 -> 48;
            case 4 -> 32;
            default -> 16;
        };
    }

    private static int consumedSixths(int currentSixths, int count) {
        if (currentSixths >= 6) {
            if (count <= 16) return 1;
            if (count <= 32) return 2;
            if (count <= 48) return 3;
            return 6;
        }
        int max = maxArrowsAt(currentSixths);
        if (count >= max) return currentSixths;
        return Math.min(currentSixths, Math.max(1, (int) Math.round((double) count / max * currentSixths)));
    }

    // ---- Actions ----

    private static InteractionResult handlePotionPour(Player player, Level world, BlockPos pos, ItemStack held) {
        PotionContents contents = held.get(DataComponents.POTION_CONTENTS);
        if (contents == null || isBasePotion(contents)) return InteractionResult.PASS;

        CauldronData existing = POTION_CAULDRONS.get(pos);
        BottleType newType = getBottleType(held);

        if (existing != null) {
            if (!effectsMatch(existing.potion, contents)) {
                POTION_CAULDRONS.remove(pos);
                updateBlockState(world, pos, null);
                saveToDisk(world, pos, null);
                if (!player.getAbilities().instabuild) {
                    held.shrink(1);
                    ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
                    if (!player.getInventory().add(bottle)) player.drop(bottle, false);
                }
                spawnPotionParticles(world, pos, contents.getColor(), SIXTHS_PER_BOTTLE);
                world.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 0.5f, 1.5f);
                return InteractionResult.SUCCESS;
            }
            if (existing.sixths >= MAX_SIXTHS) return InteractionResult.PASS;
        }

        int newSixths = (existing == null) ? SIXTHS_PER_BOTTLE
                : Math.min(existing.sixths + SIXTHS_PER_BOTTLE, MAX_SIXTHS);

        CauldronData pourResult = new CauldronData(contents, newType, newSixths);
        POTION_CAULDRONS.put(pos, pourResult);
        updateBlockState(world, pos, pourResult);
        saveToDisk(world, pos, pourResult);

        if (!player.getAbilities().instabuild) {
            held.shrink(1);
            ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
            if (!player.getInventory().add(bottle)) player.drop(bottle, false);
        }

        spawnPotionParticles(world, pos, contents.getColor(), newSixths);
        world.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0f, 1.0f);
        return InteractionResult.SUCCESS;
    }

    private static InteractionResult handleBottleExtract(Player player, Level world, BlockPos pos, ItemStack held) {
        CauldronData data = POTION_CAULDRONS.get(pos);
        if (data == null) return InteractionResult.PASS;

        ItemStack potion = createPotionStack(data.bottleType);
        potion.set(DataComponents.POTION_CONTENTS, data.potion);

        if (!player.getAbilities().instabuild) {
            held.shrink(1);
        }
        if (!player.getInventory().add(potion)) {
            player.drop(potion, false);
        }

        int newSixths = data.sixths - SIXTHS_PER_BOTTLE;
        if (newSixths <= 0) {
            POTION_CAULDRONS.remove(pos);
            updateBlockState(world, pos, null);
            saveToDisk(world, pos, null);
        } else {
            CauldronData updated = new CauldronData(data.potion, data.bottleType, newSixths);
            POTION_CAULDRONS.put(pos, updated);
            updateBlockState(world, pos, updated);
            saveToDisk(world, pos, updated);
        }

        spawnPotionParticles(world, pos, data.potion.getColor(), data.sixths);
        world.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0f, 1.0f);
        return InteractionResult.SUCCESS;
    }

    private static InteractionResult handleArrowDip(Player player, Level world, BlockPos pos, ItemStack held) {
        CauldronData data = POTION_CAULDRONS.get(pos);
        if (data == null || data.sixths <= 0) {
            POTION_CAULDRONS.remove(pos);
            updateBlockState(world, pos, null);
            saveToDisk(world, pos, null);
            return InteractionResult.PASS;
        }

        int maxArrows = maxArrowsAt(data.sixths);
        int amount = Math.min(held.getCount(), maxArrows);
        int consumed = consumedSixths(data.sixths, amount);

        if (!player.getAbilities().instabuild) {
            held.shrink(amount);
        }

        ItemStack tippedArrow = new ItemStack(Items.TIPPED_ARROW, amount);
        tippedArrow.set(DataComponents.POTION_CONTENTS, data.potion);

        if (!player.getInventory().add(tippedArrow)) player.drop(tippedArrow, false);

        int newSixths = data.sixths - consumed;
        if (newSixths <= 0) {
            POTION_CAULDRONS.remove(pos);
            updateBlockState(world, pos, null);
            saveToDisk(world, pos, null);
        } else {
            CauldronData updated = new CauldronData(data.potion, data.bottleType, newSixths);
            POTION_CAULDRONS.put(pos, updated);
            updateBlockState(world, pos, updated);
            saveToDisk(world, pos, updated);
        }

        spawnPotionParticles(world, pos, data.potion.getColor(), data.sixths);
        world.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 0.5f, 1.0f);
        return InteractionResult.SUCCESS;
    }
}
