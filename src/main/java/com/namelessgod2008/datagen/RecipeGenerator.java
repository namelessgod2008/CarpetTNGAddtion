package com.namelessgod2008.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.core.registries.Registries;
import com.namelessgod2008.setting.RuleEnabledCondition;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public final class RecipeGenerator extends FabricRecipeProvider {

    public RecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        // Each recipe is gated by its rule so it disappears from REI when disabled
        RecipeOutput saddleOut = withConditions(output, new RuleEnabledCondition("craftableSaddle"));
        RecipeOutput nameTagOut = withConditions(output, new RuleEnabledCondition("craftableNameTag"));
        RecipeOutput bellOut = withConditions(output, new RuleEnabledCondition("craftableBell"));
        RecipeOutput stringOut = withConditions(output, new RuleEnabledCondition("craftableStringFromWool"));
        RecipeOutput cobwebOut = withConditions(output, new RuleEnabledCondition("craftableCobwebs"));
        RecipeOutput horseArmorOut = withConditions(output, new RuleEnabledCondition("craftableHorseArmor"));
        RecipeOutput blueIceOut = withConditions(output, new RuleEnabledCondition("craftableBlueIce"));
        RecipeOutput boneBlockOut = withConditions(output, new RuleEnabledCondition("boneToBoneBlock"));
        RecipeOutput chestOut = withConditions(output, new RuleEnabledCondition("woodToChest"));
        RecipeOutput dropperAndBowToDispenserOut = withConditions(output, new RuleEnabledCondition("dropperAndBowToDispenser"));
        RecipeOutput woodToStickOut = withConditions(output, new RuleEnabledCondition("woodToStick"));
        RecipeOutput blastFurnaceGlassOut = withConditions(output, new RuleEnabledCondition("blastFurnaceGlass"));
        RecipeOutput blastFurnaceGlazedTerracottaOut = withConditions(output, new RuleEnabledCondition("blastFurnaceGlazedTerracotta"));
        RecipeOutput blastFurnaceNetherBrickOut = withConditions(output, new RuleEnabledCondition("blastFurnaceNetherBrick"));
        RecipeOutput blastFurnaceSmoothQuartzOut = withConditions(output, new RuleEnabledCondition("blastFurnaceSmoothQuartz"));
        RecipeOutput blastFurnaceStoneOut = withConditions(output, new RuleEnabledCondition("blastFurnaceStone"));
        RecipeOutput blastFurnaceSmoothStoneOut = withConditions(output, new RuleEnabledCondition("blastFurnaceSmoothStone"));
        RecipeOutput smokerGreenDyeOut = withConditions(output, new RuleEnabledCondition("smokerGreenDye"));

        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                var items = this.registries.lookupOrThrow(Registries.ITEM);

                // Saddle: 3 leather + 1 iron ingot, shapeless
                ShapelessRecipeBuilder.shapeless(items, RecipeCategory.MISC, Items.SADDLE)
                        .requires(Items.LEATHER, 3)
                        .requires(Items.IRON_INGOT)
                        .unlockedBy("has_leather", has(Items.LEATHER))
                        .save(saddleOut);

                // Name tag: 1 iron nugget + 1 paper, diagonal 2×2
                ShapedRecipeBuilder.shaped(items, RecipeCategory.MISC, Items.NAME_TAG, 1)
                        .pattern("N ")
                        .pattern(" P")
                        .define('N', Items.IRON_NUGGET)
                        .define('P', Items.PAPER)
                        .unlockedBy("has_paper", has(Items.PAPER))
                        .save(nameTagOut);

                // Bell: 3 gold ingots top, 2 sticks sides, 3 smooth stone slabs bottom
                ShapedRecipeBuilder.shaped(items, RecipeCategory.MISC, Items.BELL, 1)
                        .pattern("GGG")
                        .pattern("S S")
                        .pattern("BBB")
                        .define('G', Items.GOLD_INGOT)
                        .define('S', Items.STICK)
                        .define('B', Items.SMOOTH_STONE_SLAB)
                        .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                        .save(bellOut);

                // String from wool: 1 wool (any color) -> 4 string, shapeless
                ShapelessRecipeBuilder.shapeless(items, RecipeCategory.MISC, Items.STRING, 4)
                        .requires(ItemTags.WOOL)
                        .unlockedBy("has_wool", has(ItemTags.WOOL))
                        .save(stringOut);

                // Cobweb from string: 9 string (3x3) -> 1 cobweb, the reverse of the old Bedrock
                // "1 cobweb -> 9 string" recipe. Bedrock cobweb -> string was removed in 1.20.10.
                ShapedRecipeBuilder.shaped(items, RecipeCategory.BUILDING_BLOCKS, Blocks.COBWEB, 1)
                        .pattern("SSS")
                        .pattern("SSS")
                        .pattern("SSS")
                        .define('S', Items.STRING)
                        .unlockedBy("has_string", has(Items.STRING))
                        .save(cobwebOut);

                // Horse armor (iron/gold/diamond): restores the 13w16a recipe, 6 ingots/gems + 1 wool.
                //   _ _ X
                //   X W X
                //   X X X
                // Leather horse armor already has a vanilla recipe, so it is not included.
                ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, Items.IRON_HORSE_ARMOR, 1)
                        .pattern("  X")
                        .pattern("XWX")
                        .pattern("XXX")
                        .define('X', Items.IRON_INGOT)
                        .define('W', ItemTags.WOOL)
                        .unlockedBy("has_iron", has(Items.IRON_INGOT))
                        .save(horseArmorOut);

                ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, Items.GOLDEN_HORSE_ARMOR, 1)
                        .pattern("  X")
                        .pattern("XWX")
                        .pattern("XXX")
                        .define('X', Items.GOLD_INGOT)
                        .define('W', ItemTags.WOOL)
                        .unlockedBy("has_gold", has(Items.GOLD_INGOT))
                        .save(horseArmorOut);

                ShapedRecipeBuilder.shaped(items, RecipeCategory.COMBAT, Items.DIAMOND_HORSE_ARMOR, 1)
                        .pattern("  X")
                        .pattern("XWX")
                        .pattern("XXX")
                        .define('X', Items.DIAMOND)
                        .define('W', ItemTags.WOOL)
                        .unlockedBy("has_diamond", has(Items.DIAMOND))
                        .save(horseArmorOut);

                // Blue ice: 8 ice + 1 blue dye (standard dyeing pattern) -> 1 blue ice
                // Vanilla requires 9 packed ice (= 81 ice) via the furnace-free crafting chain.
                ShapedRecipeBuilder.shaped(items, RecipeCategory.BUILDING_BLOCKS, Items.BLUE_ICE, 1)
                        .pattern("III")
                        .pattern("IDI")
                        .pattern("III")
                        .define('I', Items.ICE)
                        .define('D', Items.BLUE_DYE)
                        .unlockedBy("has_blue_dye", has(Items.BLUE_DYE))
                        .save(blueIceOut);

                // Bone block: 3 bones (shapeless) -> 1 bone block.
                // Vanilla requires 9 bone meal (smelt 3 bones first).
                ShapelessRecipeBuilder.shapeless(items, RecipeCategory.BUILDING_BLOCKS, Items.BONE_BLOCK, 1)
                        .requires(Items.BONE, 3)
                        .unlockedBy("has_bone", has(Items.BONE))
                        .save(boneBlockOut);

                // Chest: 2 of any log (shapeless) -> 1 chest.
                // Vanilla requires 8 planks instead.
                ShapelessRecipeBuilder.shapeless(items, RecipeCategory.DECORATIONS, Items.CHEST, 1)
                        .requires(this.tag(ItemTags.LOGS), 2)
                        .unlockedBy("has_logs", has(ItemTags.LOGS))
                        .save(chestOut);

                // Dispenser: 1 dropper + 1 bow (shapeless) -> 1 dispenser.
                // Vanilla requires 7 cobblestone + 1 bow + 1 redstone.
                ShapelessRecipeBuilder.shapeless(items, RecipeCategory.REDSTONE, Items.DISPENSER, 1)
                        .requires(Items.DROPPER)
                        .requires(Items.BOW)
                        .unlockedBy("has_dropper", has(Items.DROPPER))
                        .save(dropperAndBowToDispenserOut);

                // Sticks: 1 of any log (shapeless) -> 8 sticks, skipping the planks step.
                // Equivalent to vanilla (1 log = 4 planks = 8 sticks); same group as vanilla sticks.
                ShapelessRecipeBuilder.shapeless(items, RecipeCategory.MISC, Items.STICK, 8)
                        .requires(this.tag(ItemTags.LOGS))
                        .group("sticks")
                        .unlockedBy("has_logs", has(ItemTags.LOGS))
                        .save(woodToStickOut);

                // Blast furnace: sand & red sand -> glass (vanilla only allows this in the furnace)
                // Blasting speed is half the furnace's 200 ticks, same 0.1 experience.
                SimpleCookingRecipeBuilder.blasting(this.tag(ItemTags.SMELTS_TO_GLASS),
                                RecipeCategory.BUILDING_BLOCKS, Items.GLASS, 0.1F, 100)
                        .unlockedBy("has_sand", has(Items.SAND))
                        .save(blastFurnaceGlassOut, getBlastingRecipeName(Items.GLASS));

                // Blast furnace: all 16 colored terracotta -> glazed terracotta of the same color
                // (vanilla only allows these in the furnace). Blasting is half the furnace's 200 ticks.
                // Each color pair (terracotta, glazed) keeps 1:1 correspondence; none omitted.
                Block[] terracotta = {
                        Blocks.BLACK_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA,
                        Blocks.CYAN_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.GREEN_TERRACOTTA,
                        Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.LIME_TERRACOTTA,
                        Blocks.MAGENTA_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.PINK_TERRACOTTA,
                        Blocks.PURPLE_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.WHITE_TERRACOTTA,
                        Blocks.YELLOW_TERRACOTTA
                };
                Block[] glazedTerracotta = {
                        Blocks.BLACK_GLAZED_TERRACOTTA, Blocks.BLUE_GLAZED_TERRACOTTA, Blocks.BROWN_GLAZED_TERRACOTTA,
                        Blocks.CYAN_GLAZED_TERRACOTTA, Blocks.GRAY_GLAZED_TERRACOTTA, Blocks.GREEN_GLAZED_TERRACOTTA,
                        Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, Blocks.LIME_GLAZED_TERRACOTTA,
                        Blocks.MAGENTA_GLAZED_TERRACOTTA, Blocks.ORANGE_GLAZED_TERRACOTTA, Blocks.PINK_GLAZED_TERRACOTTA,
                        Blocks.PURPLE_GLAZED_TERRACOTTA, Blocks.RED_GLAZED_TERRACOTTA, Blocks.WHITE_GLAZED_TERRACOTTA,
                        Blocks.YELLOW_GLAZED_TERRACOTTA
                };
                for (int i = 0; i < terracotta.length; i++) {
                    SimpleCookingRecipeBuilder.blasting(Ingredient.of(terracotta[i]),
                                    RecipeCategory.DECORATIONS, glazedTerracotta[i].asItem(), 0.1F, 100)
                            .unlockedBy("has_" + getItemName(terracotta[i]), has(terracotta[i]))
                            .save(blastFurnaceGlazedTerracottaOut, getBlastingRecipeName(glazedTerracotta[i]));
                }

                // Blast furnace: netherrack -> nether brick (item), quartz block -> smooth quartz,
                // cobblestone -> stone, stone -> smooth stone (vanilla only allows these in the furnace).
                // Each mirrors the vanilla furnace recipe (xp 0.1, 200 ticks) at half the cooking time.
                SimpleCookingRecipeBuilder.blasting(Ingredient.of(Blocks.NETHERRACK),
                                RecipeCategory.MISC, Items.NETHER_BRICK, 0.1F, 100)
                        .unlockedBy("has_netherrack", has(Blocks.NETHERRACK))
                        .save(blastFurnaceNetherBrickOut, getBlastingRecipeName(Items.NETHER_BRICK));

                SimpleCookingRecipeBuilder.blasting(Ingredient.of(Blocks.QUARTZ_BLOCK),
                                RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_QUARTZ.asItem(), 0.1F, 100)
                        .unlockedBy("has_quartz_block", has(Blocks.QUARTZ_BLOCK))
                        .save(blastFurnaceSmoothQuartzOut, getBlastingRecipeName(Blocks.SMOOTH_QUARTZ));

                SimpleCookingRecipeBuilder.blasting(Ingredient.of(Blocks.COBBLESTONE),
                                RecipeCategory.BUILDING_BLOCKS, Blocks.STONE.asItem(), 0.1F, 100)
                        .unlockedBy("has_cobblestone", has(Blocks.COBBLESTONE))
                        .save(blastFurnaceStoneOut, getBlastingRecipeName(Blocks.STONE));

                SimpleCookingRecipeBuilder.blasting(Ingredient.of(Blocks.STONE),
                                RecipeCategory.BUILDING_BLOCKS, Blocks.SMOOTH_STONE.asItem(), 0.1F, 100)
                        .unlockedBy("has_stone", has(Blocks.STONE))
                        .save(blastFurnaceSmoothStoneOut, getBlastingRecipeName(Blocks.SMOOTH_STONE));

                // Smoker: cactus -> green dye (vanilla only allows this in the furnace).
                // Smoker time is half the furnace's 200 ticks, same 1.0 experience.
                // Custom id avoids clashing with the vanilla green_dye_from_smelting.
                SimpleCookingRecipeBuilder.smoking(Ingredient.of(Blocks.CACTUS),
                                RecipeCategory.MISC, Items.GREEN_DYE, 1.0F, 100)
                        .unlockedBy("has_cactus", has(Blocks.CACTUS))
                        .save(smokerGreenDyeOut, "green_dye_from_smoking");
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "TNG Recipes";
    }
}
