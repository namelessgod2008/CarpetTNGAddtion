package com.namelessgod2008.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public final class TranslationGenerator {

    private TranslationGenerator() {}

    public static class English extends FabricLanguageProvider {
        public English(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, "en_us", registries);
        }

        @Override
        public void generateTranslations(HolderLookup.Provider lookup, TranslationBuilder builder) {
            builder.add("carpet.rule.bedrockCauldronTippedArrows.name",
                    "Bedrock Cauldron Tipped Arrows");
            builder.add("carpet.rule.bedrockCauldronTippedArrows.desc",
                    "Pour potions into empty cauldrons and dip arrows to create tipped arrows, just like in Bedrock Edition. One potion bottle = 16 arrows.");
            builder.add("carpet.rule.craftableSaddle.name", "Craftable Saddle");
            builder.add("carpet.rule.craftableSaddle.desc", "Adds a crafting recipe for the saddle.");
            builder.add("carpet.rule.craftableNameTag.name", "Craftable Name Tag");
            builder.add("carpet.rule.craftableNameTag.desc", "Adds a crafting recipe for the name tag.");
            builder.add("carpet.rule.craftableBell.name", "Craftable Bell");
            builder.add("carpet.rule.craftableBell.desc", "Adds a crafting recipe for the bell.");
            builder.add("carpet.rule.dispenserPlanting.name", "Dispenser Planting");
            builder.add("carpet.rule.dispenserPlanting.desc",
                    "Dispensers shoot wheat, beetroot, carrot and potato seeds in a straight line; seeds turn into crop sprouts when hitting farmland.");
            builder.add("carpet.rule.dispenserPlantingGourds.name", "Dispenser Planting Gourds");
            builder.add("carpet.rule.dispenserPlantingGourds.desc",
                    "Dispensers shoot melon and pumpkin seeds in a straight line; seeds turn into stems when hitting farmland.");
            builder.add("carpet.rule.dispenserPlantingNetherWart.name", "Dispenser Planting Nether Wart");
            builder.add("carpet.rule.dispenserPlantingNetherWart.desc",
                    "Dispensers shoot nether wart in a straight line; it sprouts when hitting soul sand.");
            builder.add("carpet.rule.bonemealGourdFruit.name", "Bonemeal Gourd Fruit");
            builder.add("carpet.rule.bonemealGourdFruit.desc",
                    "Chance that bonemealing a mature melon or pumpkin stem grows a fruit on an adjacent block. 0 disables the feature; 1 means always fruiting.");
            builder.add("carpet.rule.dispenserGourdFruit.name", "Dispenser Gourd Fruit");
            builder.add("carpet.rule.dispenserGourdFruit.desc",
                    "Dispensers facing a mature melon or pumpkin stem use bone meal on it, growing a fruit with the Bonemeal Gourd Fruit chance. Requires the Bonemeal Gourd Fruit rule.");
            builder.add("carpet.rule.reinforcedObsidian.name", "Reinforced Obsidian");
            builder.add("carpet.rule.reinforcedObsidian.desc",
                    "Obsidian becomes immune to the Wither's block-breaking burst (the one it emits after taking damage); the Wither can no longer destroy obsidian. Regular explosions already cannot break obsidian.");
            builder.add("carpet.rule.basaltToBlackstoneConversion.name", "Basalt to Blackstone Conversion");
            builder.add("carpet.rule.basaltToBlackstoneConversion.desc",
                    "Basalt that touches both lava and water at the same time converts to blackstone.");
            builder.add("carpet.rule.stopCreeperGriefing.name", "Stop Creeper Griefing");
            builder.add("carpet.rule.stopCreeperGriefing.desc",
                    "Creeper explosions no longer destroy blocks; they still deal damage and knockback.");
            builder.add("carpet.rule.stopGhastGriefing.name", "Stop Ghast Griefing");
            builder.add("carpet.rule.stopGhastGriefing.desc",
                    "Ghast fireball explosions no longer destroy blocks; they still deal damage.");
            builder.add("carpet.rule.villagerBedExplosion.name", "Villager Bed Explosion");
            builder.add("carpet.rule.villagerBedExplosion.desc",
                    "Villagers using a bed in the nether or the end cause it to explode, like players do.");
            builder.add("carpet.rule.endCrystalPlacementRestriction.name", "End Crystal Placement Restriction");
            builder.add("carpet.rule.endCrystalPlacementRestriction.desc",
                    "End crystals can only be placed on obsidian or bedrock (vanilla behavior, enabled by default). When disabled, they can be placed on any block.");
            builder.add("carpet.rule.stopEndCrystalGriefing.name", "Stop End Crystal Griefing");
            builder.add("carpet.rule.stopEndCrystalGriefing.desc",
                    "End crystal explosions no longer destroy blocks; they still deal damage.");
            builder.add("carpet.rule.snowGolemNoMelt.name", "Snow Golem No Melt");
            builder.add("carpet.rule.snowGolemNoMelt.desc",
                    "Snow golems no longer take melting damage in hot biomes (deserts, the nether, badlands, etc.).");
            builder.add("carpet.rule.stackableProtection.name", "Stackable Protection");
            builder.add("carpet.rule.stackableProtection.desc",
                    "Different protection enchantments (protection, blast protection, fire protection, projectile protection) can be applied to the same piece of armor and stack.");
            builder.add("carpet.rule.villagerLightningNoWitch.name", "Villager Lightning No Witch");
            builder.add("carpet.rule.villagerLightningNoWitch.desc",
                    "Villagers struck by lightning no longer turn into witches.");
            builder.add("carpet.rule.grindstoneRemovesCurses.name", "Grindstone Removes Curses");
            builder.add("carpet.rule.grindstoneRemovesCurses.desc",
                    "Cursed enchantments (binding curse, vanishing curse) can be removed with a grindstone.");
            builder.add("carpet.rule.evokerDeathKillsVexes.name", "Evoker Death Kills Vexes");
            builder.add("carpet.rule.evokerDeathKillsVexes.desc",
                    "Vexes summoned by an evoker die immediately when the evoker dies.");
            builder.add("carpet.rule.shovelSnowLayer.name", "Shovel Snow Layer");
            builder.add("carpet.rule.shovelSnowLayer.desc",
                    "Right-clicking a snow layer with a shovel removes one layer.");
            builder.add("carpet.rule.shovelSnowLayerDropSnowball.name", "Shovel Snow Layer Drop Snowball");
            builder.add("carpet.rule.shovelSnowLayerDropSnowball.desc",
                    "Shoveling a snow layer drops one snowball per removed layer. Requires shovelSnowLayer.");
            builder.add("carpet.rule.tadpoleDyeColor.name", "Tadpole Dye Color");
            builder.add("carpet.rule.tadpoleDyeColor.desc",
                    "Feeding a tadpole one of the three dyes (orange/white/green) marks the color of the frog it grows into. Each tadpole can be dyed only once; growth is not sped up.");
            builder.add("carpet.rule.lootingSlimeSplit.name", "Looting Slime Split");
            builder.add("carpet.rule.lootingSlimeSplit.desc",
                    "Killing a large slime or magma cube with a Looting weapon splits it into more small ones, +1 per Looting level.");
            builder.add("carpet.rule.endermanNoTakeBlocks.name", "Enderman No Take Blocks");
            builder.add("carpet.rule.endermanNoTakeBlocks.desc",
                    "Comma-separated block ids that endermen are forbidden from picking up (e.g. \"minecraft:dirt,minecraft:grass_block\"). Empty = no restriction (vanilla).");
            builder.add("carpet.rule.dispenserIronGolemRepair.name", "Dispenser Iron Golem Repair");
            builder.add("carpet.rule.dispenserIronGolemRepair.desc",
                    "Dispensers use an iron ingot to repair an injured iron golem standing in front of them, restoring 25 HP per ingot (up to full health, like right-clicking with an ingot).");
            builder.add("carpet.rule.collectableReinforcedDeepslate.name", "Collectable Reinforced Deepslate");
            builder.add("carpet.rule.collectableReinforcedDeepslate.desc",
                    "Reinforced deepslate becomes collectable: its mining time is set to match obsidian (hardness 50), it drops itself when mined, and only a diamond or netherite pickaxe can mine it.");
            builder.add("carpet.rule.itemNeverDespawn.name", "Item Never Despawn");
            builder.add("carpet.rule.itemNeverDespawn.desc",
                    "Dropped items never despawn: item entities are no longer removed after 6000 ticks (5 minutes).");
            builder.add("carpet.rule.weakVindicator.name", "Weak Vindicator");
            builder.add("carpet.rule.weakVindicator.desc",
                    "Vindicator attack damage is locked to 1 (regardless of difficulty; vanilla base is 5.0 plus weapon/enchantment bonuses).");
            builder.add("carpet.rule.commandMods.name", "Command Mods");
            builder.add("carpet.rule.commandMods.desc",
                    "Enables the /mods command that lists all installed mods on the server. Takes effect immediately.");
            builder.add("carpet.rule.commandAddEnchantment.name", "Command Add Enchantment");
            builder.add("carpet.rule.commandAddEnchantment.desc",
                    "Enables /addEnchantment <enchantment> <level> to add an enchantment to the item in your main hand. Takes effect immediately.");
            builder.add("carpet.rule.blastFurnaceGlazedTerracotta.name", "Blast Furnace Glazed Terracotta");
            builder.add("carpet.rule.blastFurnaceGlazedTerracotta.desc",
                    "Blast furnaces can smelt all 16 colors of terracotta into the glazed terracotta of the same color. Vanilla only allows smelting these in a furnace.");
            builder.add("carpet.rule.copperUnderwaterOxidationMultiplier.name", "Copper Underwater Oxidation Multiplier");
            builder.add("carpet.rule.copperUnderwaterOxidationMultiplier.desc",
                    "Multiplier for copper oxidation speed when a copper block is in contact with water (a water source, flowing water, or a waterlogged block on any adjacent face). 1.0 is the vanilla rate; values above 1 speed it up, below 1 slow it down. Vanilla oxidation is not affected by water.");
            builder.add("carpet.rule.splashOxidizeCopper.name", "Splash Oxidize Copper");
            builder.add("carpet.rule.splashOxidizeCopper.desc",
                    "Throwing a splash water bottle at a copper block instantly oxidizes it to the next stage.");
            builder.add("carpet.rule.piglinBarterDisabledTime.name", "Custom Piglin Trade Disabled Time");
            builder.add("carpet.rule.piglinBarterDisabledTime.desc",
                    "Sets how long (in ticks) a piglin refuses to barter after being hit by a player. Vanilla is 400 ticks (20 seconds); 0 disables the refusal.");
            builder.add("carpet.rule.neutralPiglins.name", "Neutral Piglins");
            builder.add("carpet.rule.neutralPiglins.desc",
                    "Piglins no longer proactively attack players who are not wearing gold armor, but the pack-aggression mechanic (e.g. after you attack a piglin or a piglin brute) still works as vanilla.");
            builder.add("carpet.rule.constantHighEnderDragonXp.name", "Constant High Ender Dragon XP");
            builder.add("carpet.rule.constantHighEnderDragonXp.desc",
                    "Killing the ender dragon always drops the first-kill experience amount (12000) instead of the reduced 500 on repeat kills.");
            builder.add("carpet.rule.removeAnvilTooExpensive.name", "Remove Anvil Too Expensive");
            builder.add("carpet.rule.removeAnvilTooExpensive.desc",
                    "Anvil operations (repair, enchant, rename) no longer become 'Too Expensive' when the cost reaches 40 levels; they remain available.");
            builder.add("carpet.rule.cheapAnvilRename.name", "Cheap Anvil Rename");
            builder.add("carpet.rule.cheapAnvilRename.desc",
                    "Renaming an item in the anvil always costs only 1 experience level, and cannot be blocked by 'Too Expensive'.");
            builder.add("carpet.rule.durableAnvil.name", "Durable Anvil");
            builder.add("carpet.rule.durableAnvil.desc",
                    "Using the anvil (repair, enchant, rename) no longer damages it; the anvil can still be broken physically.");
            builder.add("carpet.rule.durableFallingAnvil.name", "Durable Falling Anvil");
            builder.add("carpet.rule.durableFallingAnvil.desc",
                    "Falling anvils no longer get damaged when they hit the ground; they can still be broken physically.");
            builder.add("carpet.rule.blazeStickDebug.name", "Blaze Stick Debug");
            builder.add("carpet.rule.blazeStickDebug.desc",
                    "Activates the special functions of the blaze rod; related blaze stick rules require this rule to be enabled.");
            builder.add("carpet.rule.blazeStickFurnaceXp.name", "Blaze Stick Furnace XP");
            builder.add("carpet.rule.blazeStickFurnaceXp.desc",
                    "While holding a blaze rod, right-clicking a furnace does not open its GUI; instead it clears all accumulated furnace experience and spawns the XP orbs at the player's position. Requires the Blaze Stick Debug rule.");
            builder.add("carpet.rule.blazeStickSmokerXp.name", "Blaze Stick Smoker XP");
            builder.add("carpet.rule.blazeStickSmokerXp.desc",
                    "While holding a blaze rod, right-clicking a smoker does not open its GUI; instead it clears all accumulated smoker experience and spawns the XP orbs at the player's position. Requires the Blaze Stick Debug rule.");
            builder.add("carpet.rule.blazeStickBlastFurnaceXp.name", "Blaze Stick Blast Furnace XP");
            builder.add("carpet.rule.blazeStickBlastFurnaceXp.desc",
                    "While holding a blaze rod, right-clicking a blast furnace does not open its GUI; instead it clears all accumulated blast furnace experience and spawns the XP orbs at the player's position. Requires the Blaze Stick Debug rule.");
            builder.add("carpet.rule.craftableStringFromWool.name", "Craftable String From Wool");
            builder.add("carpet.rule.craftableStringFromWool.desc",
                    "Adds a crafting recipe to convert wool back into string (1 wool = 4 string).");
            builder.add("carpet.rule.craftableCobwebs.name", "Craftable Cobwebs");
            builder.add("carpet.rule.craftableCobwebs.desc",
                    "Adds a crafting recipe for cobwebs from 9 string, the reverse of the old Bedrock cobweb-to-string recipe.");
            builder.add("carpet.rule.craftableHorseArmor.name", "Craftable Horse Armor");
            builder.add("carpet.rule.craftableHorseArmor.desc",
                    "Adds crafting recipes for iron, golden and diamond horse armor using the 1.6-era pattern (6 ingots/gems + 1 wool). Leather horse armor already has a vanilla recipe.");
            builder.add("carpet.rule.craftableBlueIce.name", "Craftable Blue Ice");
            builder.add("carpet.rule.craftableBlueIce.desc",
                    "Adds a crafting recipe for blue ice from 8 ice and 1 blue dye (standard dyeing pattern).");
            builder.add("carpet.rule.boneToBoneBlock.name", "Craftable Bone Block");
            builder.add("carpet.rule.boneToBoneBlock.desc",
                    "Adds a crafting recipe for bone blocks from 3 bones (shapeless). Vanilla requires 9 bone meal instead.");
            builder.add("carpet.rule.woodToChest.name", "Craftable Chest");
            builder.add("carpet.rule.woodToChest.desc",
                    "Adds a crafting recipe for a chest from 2 of any log (shapeless). Vanilla requires 8 planks instead.");
            builder.add("carpet.rule.dropperAndBowToDispenser.name", "Dropper and Bow to Dispenser");
            builder.add("carpet.rule.dropperAndBowToDispenser.desc",
                    "Adds a crafting recipe for a dispenser from 1 dropper and 1 bow (shapeless). Vanilla requires 7 cobblestone, 1 bow and 1 redstone.");
            builder.add("carpet.rule.shapelessCraftingBread.name", "Shapeless Bread");
            builder.add("carpet.rule.shapelessCraftingBread.desc",
                    "Allows crafting bread from 3 wheat in any arrangement, instead of the vanilla horizontal row.");
            builder.add("carpet.rule.shapelessCraftingPaper.name", "Shapeless Paper");
            builder.add("carpet.rule.shapelessCraftingPaper.desc",
                    "Allows crafting 3 paper from 3 sugar cane in any arrangement, instead of the vanilla vertical column.");
            builder.add("carpet.rule.shapelessCraftingShulkerBox.name", "Shapeless Shulker Box");
            builder.add("carpet.rule.shapelessCraftingShulkerBox.desc",
                    "Allows crafting a shulker box from 1 chest and 2 shulker shells in any arrangement, instead of the vanilla vertical column.");
            builder.add("carpet.rule.quartzBlockToQuartz.name", "Quartz Block to Quartz");
            builder.add("carpet.rule.quartzBlockToQuartz.desc",
                    "Adds a crafting recipe to convert a quartz block back into 4 quartz, the reverse of the vanilla 2x2 recipe.");
            builder.add("carpet.rule.blastFurnaceGlass.name", "Blast Furnace Glass");
            builder.add("carpet.rule.blastFurnaceGlass.desc",
                    "Blast furnaces can smelt sand and red sand into glass. Vanilla only allows smelting these in a furnace.");
            builder.add("carpet.rule.blastFurnaceNetherBrick.name", "Blast Furnace Nether Brick");
            builder.add("carpet.rule.blastFurnaceNetherBrick.desc",
                    "Blast furnaces can smelt netherrack into nether brick. Vanilla only allows smelting netherrack in a furnace.");
            builder.add("carpet.rule.blastFurnaceSmoothQuartz.name", "Blast Furnace Smooth Quartz");
            builder.add("carpet.rule.blastFurnaceSmoothQuartz.desc",
                    "Blast furnaces can smelt quartz blocks into smooth quartz. Vanilla only allows smelting quartz blocks in a furnace.");
            builder.add("carpet.rule.blastFurnaceStone.name", "Blast Furnace Stone");
            builder.add("carpet.rule.blastFurnaceStone.desc",
                    "Blast furnaces can smelt cobblestone into stone. Vanilla only allows smelting cobblestone in a furnace.");
            builder.add("carpet.rule.blastFurnaceSmoothStone.name", "Blast Furnace Smooth Stone");
            builder.add("carpet.rule.blastFurnaceSmoothStone.desc",
                    "Blast furnaces can smelt stone into smooth stone. Vanilla only allows smelting stone in a furnace.");
            builder.add("carpet.rule.smokerGreenDye.name", "Smoker Green Dye");
            builder.add("carpet.rule.smokerGreenDye.desc",
                    "Smokers can smelt cactus into green dye. Vanilla only allows smelting cactus in a furnace.");
            builder.add("carpet.rule.legacyEnchantedGoldenApple.name", "Legacy Enchanted Golden Apple");
            builder.add("carpet.rule.legacyEnchantedGoldenApple.desc",
                    "Restores the pre-1.9 enchanted golden apple Regeneration V (30s) effect, replacing Regeneration II (20s). Other effects stay as in the new version.");
            builder.add("carpet.rule.silkTouchBuddingAmethyst.name", "Silk Touch Budding Amethyst");
            builder.add("carpet.rule.silkTouchBuddingAmethyst.desc",
                    "Allows collecting budding amethyst blocks with a Silk Touch tool.");
            builder.add("carpet.rule.silkTouchSuspiciousBlocks.name", "Silk Touch Suspicious Blocks");
            builder.add("carpet.rule.silkTouchSuspiciousBlocks.desc",
                    "Allows collecting suspicious sand and gravel with a Silk Touch tool.");
            builder.add("carpet.rule.silkTouchSpawners.name", "Silk Touch Spawners");
            builder.add("carpet.rule.silkTouchSpawners.desc",
                    "Mining a monster spawner with a Silk Touch tool drops the spawner itself, preserving its spawn configuration (entity type and spawn potentials).");
            builder.add("carpet.rule.silkTouchPathBlocks.name", "Silk Touch Path Blocks");
            builder.add("carpet.rule.silkTouchPathBlocks.desc",
                    "Mining a dirt path with a Silk Touch tool drops the path block itself instead of dirt.");
            builder.add("carpet.rule.silkTouchFarmland.name", "Silk Touch Farmland");
            builder.add("carpet.rule.silkTouchFarmland.desc",
                    "Mining farmland with a Silk Touch tool drops the farmland block itself instead of dirt.");
            builder.add("carpet.rule.featherFallingProtectsFarmland.name", "Feather Falling Protects Farmland");
            builder.add("carpet.rule.featherFallingProtectsFarmland.desc",
                    "Landing on farmland while wearing boots with Feather Falling no longer turns it into dirt.");
            builder.add("carpet.rule.shortenedTrialSpawnerCooldown.name", "Shortened Trial Spawner Cooldown");
            builder.add("carpet.rule.shortenedTrialSpawnerCooldown.desc",
                    "Shortens the trial spawner cooldown to 5 minutes (instead of the vanilla 30 minutes).");
            builder.add("carpet.rule.brewableOminousPotion.name", "Brewable Ominous Potion");
            builder.add("carpet.rule.brewableOminousPotion.desc",
                    "Brew awkward potion with a totem of undying for Ominous Potion I; upgrade with glowstone dust up to level V. Duration stays 1h40m (Bad Omen), same as vanilla.");
            builder.add("carpet.rule.blazePowderNetherWartGrowth.name", "Blaze Powder Nether Wart Growth");
            builder.add("carpet.rule.blazePowderNetherWartGrowth.desc",
                    "Right-click nether wart with blaze powder to advance one growth stage. Vanilla bonemeal does not work on nether wart.");
            builder.add("carpet.rule.dispenserNetherWartGrowth.name", "Dispenser Nether Wart Growth");
            builder.add("carpet.rule.dispenserNetherWartGrowth.desc",
                    "Dispensers facing nether wart fertilize it with blaze powder, advancing one growth stage per use. Requires the Blaze Powder Nether Wart Growth rule.");
            builder.add("carpettngaddtion.command.addEnchantment.success",
                    "Added %s to your held item.");
        }
    }

    public static class Chinese extends FabricLanguageProvider {
        public Chinese(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, "zh_cn", registries);
        }

        @Override
        public void generateTranslations(HolderLookup.Provider lookup, TranslationBuilder builder) {
            builder.add("carpet.rule.bedrockCauldronTippedArrows.name", "基岩版炼药锅制箭");
            builder.add("carpet.rule.bedrockCauldronTippedArrows.desc",
                    "将药水倒入空的炼药锅，用箭蘸取获得药箭，类似基岩版机制。一瓶药水 = 16 支箭。");
            builder.add("carpet.rule.craftableSaddle.name", "马鞍合成");
            builder.add("carpet.rule.craftableSaddle.desc", "添加马鞍的合成配方。");
            builder.add("carpet.rule.craftableNameTag.name", "命名牌合成");
            builder.add("carpet.rule.craftableNameTag.desc", "添加命名牌的合成配方。");
            builder.add("carpet.rule.craftableBell.name", "钟合成");
            builder.add("carpet.rule.craftableBell.desc", "添加钟的合成配方。");
            builder.add("carpet.rule.dispenserPlanting.name", "发射器种植");
            builder.add("carpet.rule.dispenserPlanting.desc", "发射器喷出小麦、甜菜、胡萝卜、马铃薯种子，沿直线飞行，碰到耕地时变为作物幼苗。");
            builder.add("carpet.rule.dispenserPlantingGourds.name", "发射器种植西瓜南瓜");
            builder.add("carpet.rule.dispenserPlantingGourds.desc", "发射器喷出西瓜、南瓜种子，沿直线飞行，碰到耕地时变为瓜藤。");
            builder.add("carpet.rule.dispenserPlantingNetherWart.name", "发射器种植地狱疣");
            builder.add("carpet.rule.dispenserPlantingNetherWart.desc", "发射器喷出地狱疣，沿直线飞行，碰到灵魂沙时种下。");
            builder.add("carpet.rule.bonemealGourdFruit.name", "骨粉产瓜");
            builder.add("carpet.rule.bonemealGourdFruit.desc",
                    "对成熟的西瓜/南瓜瓜苗使用骨粉时，按所选概率在相邻格结出果实。0 为禁用，1 为必定产瓜。");
            builder.add("carpet.rule.dispenserGourdFruit.name", "发射器骨粉催瓜产果");
            builder.add("carpet.rule.dispenserGourdFruit.desc",
                    "发射器正对成熟的西瓜/南瓜瓜苗喷射骨粉，按骨粉产瓜规则的概率结出果实。需先开启骨粉产瓜规则。");
            builder.add("carpet.rule.reinforcedObsidian.name", "坚固黑曜石");
            builder.add("carpet.rule.reinforcedObsidian.desc",
                    "黑曜石免疫凋零的主动方块破坏（凋零受击后的破坏冲击），凋零不再能破坏黑曜石。普通爆炸本就无法破坏黑曜石。");
            builder.add("carpet.rule.basaltToBlackstoneConversion.name", "玄武岩转黑石");
            builder.add("carpet.rule.basaltToBlackstoneConversion.desc",
                    "同时接触到熔岩和水的玄武岩会转化为黑石。");
            builder.add("carpet.rule.stopCreeperGriefing.name", "阻止苦力怕破坏地形");
            builder.add("carpet.rule.stopCreeperGriefing.desc",
                    "苦力怕爆炸不再破坏方块，但仍会造成伤害和击退。");
            builder.add("carpet.rule.stopGhastGriefing.name", "阻止恶魂破坏地形");
            builder.add("carpet.rule.stopGhastGriefing.desc",
                    "恶魂火球爆炸不再破坏方块，但仍会造成伤害。");
            builder.add("carpet.rule.villagerBedExplosion.name", "村民睡床爆炸");
            builder.add("carpet.rule.villagerBedExplosion.desc",
                    "村民在下界和末地使用床时，床会像玩家使用一样爆炸。");
            builder.add("carpet.rule.endCrystalPlacementRestriction.name", "末地水晶放置限制");
            builder.add("carpet.rule.endCrystalPlacementRestriction.desc",
                    "末地水晶只能放在黑曜石和基岩上（原版行为，默认开启）。关闭后可在任意方块上放置。");
            builder.add("carpet.rule.stopEndCrystalGriefing.name", "阻止末地水晶破坏地形");
            builder.add("carpet.rule.stopEndCrystalGriefing.desc",
                    "末地水晶爆炸不再破坏方块，但仍会造成伤害。");
            builder.add("carpet.rule.snowGolemNoMelt.name", "雪傀儡不融化");
            builder.add("carpet.rule.snowGolemNoMelt.desc",
                    "雪傀儡在炎热生物群系（沙漠、下界、恶地等）不再受到融化伤害。");
            builder.add("carpet.rule.stackableProtection.name", "保护魔咒可叠加");
            builder.add("carpet.rule.stackableProtection.desc",
                    "不同类型保护魔咒（保护、爆炸保护、火焰保护、弹射物保护）可以附在同一件装备上并叠加效果。");
            builder.add("carpet.rule.villagerLightningNoWitch.name", "村民雷击不变女巫");
            builder.add("carpet.rule.villagerLightningNoWitch.desc",
                    "村民被闪电击中后不再变成女巫。");
            builder.add("carpet.rule.grindstoneRemovesCurses.name", "砂轮移除诅咒");
            builder.add("carpet.rule.grindstoneRemovesCurses.desc",
                    "诅咒附魔（绑定诅咒、消失诅咒）可以通过砂轮移除。");
            builder.add("carpet.rule.evokerDeathKillsVexes.name", "唤魔者死亡恼鬼死亡");
            builder.add("carpet.rule.evokerDeathKillsVexes.desc",
                    "唤魔者死亡后，其召唤的恼鬼立即死亡。");
            builder.add("carpet.rule.shovelSnowLayer.name", "锹铲雪层");
            builder.add("carpet.rule.shovelSnowLayer.desc",
                    "手持锹右键雪层方块可以铲掉一层雪。");
            builder.add("carpet.rule.shovelSnowLayerDropSnowball.name", "锹铲雪掉雪球");
            builder.add("carpet.rule.shovelSnowLayerDropSnowball.desc",
                    "锹铲雪时每铲掉一层掉落一个雪球。依赖锹铲雪规则。");
            builder.add("carpet.rule.tadpoleDyeColor.name", "蝌蚪喂染料定色");
            builder.add("carpet.rule.tadpoleDyeColor.desc",
                    "喂食蝌蚪橙色/白色/绿色三种染料之一，会标记它长大后青蛙的颜色（对应温带/暖/冷变体）。每只蝌蚪只能喂一次染料，不会加快生长。");
            builder.add("carpet.rule.lootingSlimeSplit.name", "抢夺史莱姆分裂");
            builder.add("carpet.rule.lootingSlimeSplit.desc",
                    "用附有抢夺的武器击杀大史莱姆或岩浆怪时，会分裂成更多小史莱姆，每 1 级抢夺多分裂 1 个。");
            builder.add("carpet.rule.endermanNoTakeBlocks.name", "末影人禁止搬方块");
            builder.add("carpet.rule.endermanNoTakeBlocks.desc",
                    "逗号分隔的方块 id 黑名单，末影人不能搬起这些方块（如 \"minecraft:dirt,minecraft:grass_block\"）。空值=不限制（原版行为）。");
            builder.add("carpet.rule.dispenserIronGolemRepair.name", "发射器修复铁傀儡");
            builder.add("carpet.rule.dispenserIronGolemRepair.desc",
                    "发射器朝向前方 1 格内的受伤铁傀儡使用铁锭，每锭恢复 25 点生命值（回复至满血为止，语义同手持铁锭右键修复）。");
            builder.add("carpet.rule.collectableReinforcedDeepslate.name", "可获取的强化深板岩");
            builder.add("carpet.rule.collectableReinforcedDeepslate.desc",
                    "强化深板岩变为可获取：挖掘时间设置为与黑曜石相同（硬度 50），挖掘后掉落自身，且只有钻石镐或下界合金镐才能挖掉。");
            builder.add("carpet.rule.itemNeverDespawn.name", "掉落物永不消失");
            builder.add("carpet.rule.itemNeverDespawn.desc",
                    "掉落物永不消失：物品实体不再因达到 6000 tick（5 分钟）而消失。");
            builder.add("carpet.rule.weakVindicator.name", "弱化卫道士");
            builder.add("carpet.rule.weakVindicator.desc",
                    "卫道士的攻击伤害锁定为 1（不论难度；原版基础 5.0 加武器/附魔加成）。");
            builder.add("carpet.rule.commandMods.name", "命令 /mods");
            builder.add("carpet.rule.commandMods.desc",
                    "启用 /mods 命令，列出服务器安装的所有 Mod。立即生效。");
            builder.add("carpet.rule.commandAddEnchantment.name", "命令 /addEnchantment");
            builder.add("carpet.rule.commandAddEnchantment.desc",
                    "启用 /addEnchantment <附魔> <等级>，给主手物品添加对应附魔。立即生效。");
            builder.add("carpet.rule.blastFurnaceGlazedTerracotta.name", "高炉烧制带釉陶瓦");
            builder.add("carpet.rule.blastFurnaceGlazedTerracotta.desc",
                    "高炉可以将全部 16 种染色的陶瓦烧炼为对应颜色的带釉陶瓦。原版只能在熔炉中烧制。");
            builder.add("carpet.rule.copperUnderwaterOxidationMultiplier.name", "铜水下氧化倍率");
            builder.add("carpet.rule.copperUnderwaterOxidationMultiplier.desc",
                    "铜方块接触水时的氧化速度倍率（任一相邻面有水：水方块、流水、含水方块都算）。1.0 为原版速率；大于 1 加快，小于 1 减慢。原版氧化不受水影响。");
            builder.add("carpet.rule.splashOxidizeCopper.name", "喷溅水瓶氧化铜");
            builder.add("carpet.rule.splashOxidizeCopper.desc",
                    "向铜方块投掷喷溅水瓶会使其立即氧化到下一阶段。");
            builder.add("carpet.rule.piglinBarterDisabledTime.name", "自定义猪灵受击拒绝交易时间");
            builder.add("carpet.rule.piglinBarterDisabledTime.desc",
                    "设置猪灵被玩家攻击后拒绝交易的时间（tick）。原版为 400 tick（20 秒），0 为不拒绝。");
            builder.add("carpet.rule.neutralPiglins.name", "完全中立猪灵");
            builder.add("carpet.rule.neutralPiglins.desc",
                    "猪灵不再主动攻击未穿金护甲的玩家；但群起而攻之的仇恨机制（如攻击猪灵或猪灵蛮兵后）保持原版。");
            builder.add("carpet.rule.constantHighEnderDragonXp.name", "持续高经验打龙");
            builder.add("carpet.rule.constantHighEnderDragonXp.desc",
                    "击杀末影龙始终掉落首次击杀的经验量（12000），而非重复击杀的 500。");
            builder.add("carpet.rule.removeAnvilTooExpensive.name", "移除铁砧过于昂贵");
            builder.add("carpet.rule.removeAnvilTooExpensive.desc",
                    "铁砧的修复、附魔、重命名费用达到 40 级后不再显示'过于昂贵'，仍可进行操作。");
            builder.add("carpet.rule.cheapAnvilRename.name", "铁砧低价改名");
            builder.add("carpet.rule.cheapAnvilRename.desc",
                    "在铁砧中修改物品名称始终只消耗 1 级经验，且不会被'过于昂贵'拦截。");
            builder.add("carpet.rule.durableAnvil.name", "耐用的铁砧");
            builder.add("carpet.rule.durableAnvil.desc",
                    "使用铁砧进行修复、附魔、改名等操作不再使铁砧损坏；铁砧仍可被物理破坏。");
            builder.add("carpet.rule.durableFallingAnvil.name", "耐摔的铁砧");
            builder.add("carpet.rule.durableFallingAnvil.desc",
                    "铁砧方块从高处掉落落地时不再因摔落而损坏；铁砧仍可被物理破坏。");
            builder.add("carpet.rule.blazeStickDebug.name", "烈焰棒调试");
            builder.add("carpet.rule.blazeStickDebug.desc",
                    "激活烈焰棒的特殊功能；烈焰棒调试相关的规则都需要先开启本规则。");
            builder.add("carpet.rule.blazeStickFurnaceXp.name", "烈焰棒掏炉渣");
            builder.add("carpet.rule.blazeStickFurnaceXp.desc",
                    "手持烈焰棒右键熔炉不会打开熔炉界面，而是清空熔炉积累的所有经验，经验球出现在玩家所在坐标。需先开启烈焰棒调试规则。");
            builder.add("carpet.rule.blazeStickSmokerXp.name", "烈焰棒掏烟熏炉渣");
            builder.add("carpet.rule.blazeStickSmokerXp.desc",
                    "手持烈焰棒右键烟熏炉不会打开烟熏炉界面，而是清空烟熏炉积累的所有经验，经验球出现在玩家所在坐标。需先开启烈焰棒调试规则。");
            builder.add("carpet.rule.blazeStickBlastFurnaceXp.name", "烈焰棒掏高炉渣");
            builder.add("carpet.rule.blazeStickBlastFurnaceXp.desc",
                    "手持烈焰棒右键高炉不会打开高炉界面，而是清空高炉积累的所有经验，经验球出现在玩家所在坐标。需先开启烈焰棒调试规则。");
            builder.add("carpet.rule.craftableStringFromWool.name", "羊毛合成线");
            builder.add("carpet.rule.craftableStringFromWool.desc", "添加羊毛合成线的配方（1 羊毛 = 4 线）。");
            builder.add("carpet.rule.craftableCobwebs.name", "蜘蛛网合成");
            builder.add("carpet.rule.craftableCobwebs.desc", "添加用 9 根线合成蜘蛛网的配方，是基岩版旧版'蜘蛛网拆线'配方的反向。");
            builder.add("carpet.rule.craftableHorseArmor.name", "马铠合成");
            builder.add("carpet.rule.craftableHorseArmor.desc",
                    "添加铁、金、钻石马铠的合成配方，采用 1.6 时代的图案（6 个锭/宝石 + 1 块羊毛）。皮革马铠原版已有配方。");
            builder.add("carpet.rule.craftableBlueIce.name", "蓝冰合成");
            builder.add("carpet.rule.craftableBlueIce.desc",
                    "添加用 8 个冰和 1 个蓝色染料合成蓝冰的配方（标准染色图案）。");
            builder.add("carpet.rule.boneToBoneBlock.name", "骨块合成");
            builder.add("carpet.rule.boneToBoneBlock.desc",
                    "添加用 3 根骨头（无序）合成骨块的配方。原版需要用 9 个骨粉合成。");
            builder.add("carpet.rule.woodToChest.name", "箱子合成");
            builder.add("carpet.rule.woodToChest.desc",
                    "添加用 2 个任意原木（无序）合成箱子的配方。原版需要用 8 个木板合成。");
            builder.add("carpet.rule.dropperAndBowToDispenser.name", "投掷器加弓合成发射器");
            builder.add("carpet.rule.dropperAndBowToDispenser.desc",
                    "添加用 1 个投掷器和 1 把弓（无序）合成发射器的配方。原版需要 7 个圆石、1 把弓和 1 个红石粉。");
            builder.add("carpet.rule.shapelessCraftingBread.name", "面包无序合成");
            builder.add("carpet.rule.shapelessCraftingBread.desc",
                    "允许用 3 个小麦以任意排列合成面包，取代原版的横排图案。");
            builder.add("carpet.rule.shapelessCraftingPaper.name", "纸无序合成");
            builder.add("carpet.rule.shapelessCraftingPaper.desc",
                    "允许用 3 个甘蔗以任意排列合成 3 张纸，取代原版的竖排图案。");
            builder.add("carpet.rule.shapelessCraftingShulkerBox.name", "潜影盒无序合成");
            builder.add("carpet.rule.shapelessCraftingShulkerBox.desc",
                    "允许用 1 个箱子和 2 个潜影壳以任意排列合成潜影盒，取代原版的竖排图案。");
            builder.add("carpet.rule.quartzBlockToQuartz.name", "石英块拆解");
            builder.add("carpet.rule.quartzBlockToQuartz.desc",
                    "添加将 1 个石英块拆解回 4 个下界石英的配方，是原版 2×2 合成石英块的逆向。");
            builder.add("carpet.rule.blastFurnaceGlass.name", "高炉烧沙成玻璃");
            builder.add("carpet.rule.blastFurnaceGlass.desc",
                    "高炉可以将沙子和红沙烧炼为玻璃。原版沙子/红沙只能由熔炉烧成玻璃。");
            builder.add("carpet.rule.blastFurnaceNetherBrick.name", "高炉烧制下界砖");
            builder.add("carpet.rule.blastFurnaceNetherBrick.desc",
                    "高炉可以将下界岩烧炼为下界砖。原版下界岩只能由熔炉烧成下界砖。");
            builder.add("carpet.rule.blastFurnaceSmoothQuartz.name", "高炉烧制平滑石英");
            builder.add("carpet.rule.blastFurnaceSmoothQuartz.desc",
                    "高炉可以将石英块烧炼为平滑石英块。原版石英块只能由熔炉烧成平滑石英块。");
            builder.add("carpet.rule.blastFurnaceStone.name", "高炉烧制石头");
            builder.add("carpet.rule.blastFurnaceStone.desc",
                    "高炉可以将圆石烧炼为石头。原版圆石只能由熔炉烧成石头。");
            builder.add("carpet.rule.blastFurnaceSmoothStone.name", "高炉烧制平滑石头");
            builder.add("carpet.rule.blastFurnaceSmoothStone.desc",
                    "高炉可以将石头烧炼为平滑石头。原版石头只能由熔炉烧成平滑石头。");
            builder.add("carpet.rule.smokerGreenDye.name", "烟熏炉烧制绿色染料");
            builder.add("carpet.rule.smokerGreenDye.desc",
                    "烟熏炉可以将仙人掌烟熏为绿色染料。原版仙人掌只能由熔炉烧成绿色染料。");
            builder.add("carpet.rule.legacyEnchantedGoldenApple.name", "旧版附魔金苹果生命恢复");
            builder.add("carpet.rule.legacyEnchantedGoldenApple.desc",
                    "恢复 1.9 前的附魔金苹果再生效果：再生 V（30 秒）替代新版的再生 II（20 秒），其他效果保持新版不变。");
            builder.add("carpet.rule.silkTouchBuddingAmethyst.name", "精准采集紫水晶母岩");
            builder.add("carpet.rule.silkTouchBuddingAmethyst.desc", "允许使用精准采集工具采集紫水晶母岩。");
            builder.add("carpet.rule.silkTouchSuspiciousBlocks.name", "精准采集可疑方块");
            builder.add("carpet.rule.silkTouchSuspiciousBlocks.desc", "允许使用精准采集工具采集可疑的沙子和砂砾。");
            builder.add("carpet.rule.silkTouchSpawners.name", "精准采集刷怪笼");
            builder.add("carpet.rule.silkTouchSpawners.desc",
                    "使用精准采集工具挖掘刷怪笼时，会掉落刷怪笼自身，并完整保留其刷怪配置（实体类型与刷怪池）。");
            builder.add("carpet.rule.silkTouchPathBlocks.name", "精准采集土径");
            builder.add("carpet.rule.silkTouchPathBlocks.desc",
                    "使用精准采集工具挖掘土径时，会掉落土径方块自身，而不是泥土。");
            builder.add("carpet.rule.silkTouchFarmland.name", "精准采集耕地");
            builder.add("carpet.rule.silkTouchFarmland.desc",
                    "使用精准采集工具挖掘耕地时，会掉落耕地方块自身，而不是泥土。");
            builder.add("carpet.rule.featherFallingProtectsFarmland.name", "摔落缓冲保护耕地");
            builder.add("carpet.rule.featherFallingProtectsFarmland.desc",
                    "穿着带摔落缓冲附魔的靴子落到耕地上时，不再会把耕地踩成泥土。");
            builder.add("carpet.rule.shortenedTrialSpawnerCooldown.name", "缩短试炼刷怪笼冷却");
            builder.add("carpet.rule.shortenedTrialSpawnerCooldown.desc", "将试炼刷怪笼冷却缩短为 5 分钟（原版为 30 分钟）。");
            builder.add("carpet.rule.brewableOminousPotion.name", "灾厄药水酿造");
            builder.add("carpet.rule.brewableOminousPotion.desc",
                    "用不死图腾酿造粗制的药水获得 1 级灾厄药水，用萤石粉继续酿造可提升等级（最多 5 级），持续时间始终为 1 小时 40 分钟（不祥之兆），与原版一致。");
            builder.add("carpet.rule.blazePowderNetherWartGrowth.name", "烈焰粉催熟地狱疣");
            builder.add("carpet.rule.blazePowderNetherWartGrowth.desc",
                    "手持烈焰粉右键地狱疣可推进一个生长阶段（原版骨粉对地狱疣无效）。");
            builder.add("carpet.rule.dispenserNetherWartGrowth.name", "发射器催熟地狱疣");
            builder.add("carpet.rule.dispenserNetherWartGrowth.desc",
                    "发射器正对地狱疣喷射烈焰粉可推进一个生长阶段，每次消耗 1 个烈焰粉。需先开启烈焰粉催熟地狱疣规则。");
            builder.add("carpettngaddtion.command.addEnchantment.success",
                    "已给你的手持物品添加 %s。");
        }
    }
}
