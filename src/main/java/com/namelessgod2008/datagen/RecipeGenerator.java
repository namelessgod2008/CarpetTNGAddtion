package com.namelessgod2008.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.core.registries.Registries;
import com.namelessgod2008.setting.RuleEnabledCondition;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public final class RecipeGenerator extends FabricRecipeProvider {

    public RecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        // Each recipe is gated by its rule so it disappears from REI when disabled
        RecipeOutput saddleOut = withConditions(output, new RuleEnabledCondition("craftableSaddle"));
        RecipeOutput nameTagOut = withConditions(output, new RuleEnabledCondition("craftableNameTag"));
        RecipeOutput bellOut = withConditions(output, new RuleEnabledCondition("craftableBell"));

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
            }
        };
    }

    @Override
    public String getName() {
        return "TNG Recipes";
    }
}
