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
            builder.add("carpet.rule.shortenedPiglinBarterCooldown.name", "Shortened Piglin Barter Cooldown");
            builder.add("carpet.rule.shortenedPiglinBarterCooldown.desc",
                    "Sets how many ticks a piglin admires a gold ingot before bartering. Vanilla is 119 ticks; 0 makes bartering instant. Options: 119, 89, 59, 29, 0.");
            builder.add("carpet.rule.neutralPiglins.name", "Neutral Piglins");
            builder.add("carpet.rule.neutralPiglins.desc",
                    "Piglins no longer proactively attack players who are not wearing gold armor, but the pack-aggression mechanic (e.g. after you attack a piglin or a piglin brute) still works as vanilla.");
            builder.add("carpet.rule.constantHighEnderDragonXp.name", "Constant High Ender Dragon XP");
            builder.add("carpet.rule.constantHighEnderDragonXp.desc",
                    "Killing the ender dragon always drops the first-kill experience amount (12000) instead of the reduced 500 on repeat kills.");
            builder.add("carpet.rule.craftableStringFromWool.name", "Craftable String From Wool");
            builder.add("carpet.rule.craftableStringFromWool.desc",
                    "Adds a crafting recipe to convert wool back into string (1 wool = 4 string).");
            builder.add("carpet.rule.legacyEnchantedGoldenApple.name", "Legacy Enchanted Golden Apple");
            builder.add("carpet.rule.legacyEnchantedGoldenApple.desc",
                    "Restores the pre-1.9 enchanted golden apple Regeneration V (30s) effect, replacing Regeneration II (20s). Other effects stay as in the new version.");
            builder.add("carpet.rule.silkTouchBuddingAmethyst.name", "Silk Touch Budding Amethyst");
            builder.add("carpet.rule.silkTouchBuddingAmethyst.desc",
                    "Allows collecting budding amethyst blocks with a Silk Touch tool.");
            builder.add("carpet.rule.silkTouchSuspiciousBlocks.name", "Silk Touch Suspicious Blocks");
            builder.add("carpet.rule.silkTouchSuspiciousBlocks.desc",
                    "Allows collecting suspicious sand and gravel with a Silk Touch tool.");
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
            builder.add("carpet.rule.shortenedPiglinBarterCooldown.name", "缩短猪灵交易冷却");
            builder.add("carpet.rule.shortenedPiglinBarterCooldown.desc",
                    "设置猪灵交易前欣赏金锭的时长（tick）。原版为 119 tick，0 为立即交易。可选：119、89、59、29、0。");
            builder.add("carpet.rule.neutralPiglins.name", "完全中立猪灵");
            builder.add("carpet.rule.neutralPiglins.desc",
                    "猪灵不再主动攻击未穿金护甲的玩家；但群起而攻之的仇恨机制（如攻击猪灵或猪灵蛮兵后）保持原版。");
            builder.add("carpet.rule.constantHighEnderDragonXp.name", "持续高经验打龙");
            builder.add("carpet.rule.constantHighEnderDragonXp.desc",
                    "击杀末影龙始终掉落首次击杀的经验量（12000），而非重复击杀的 500。");
            builder.add("carpet.rule.craftableStringFromWool.name", "羊毛合成线");
            builder.add("carpet.rule.craftableStringFromWool.desc", "添加羊毛合成线的配方（1 羊毛 = 4 线）。");
            builder.add("carpet.rule.legacyEnchantedGoldenApple.name", "旧版附魔金苹果生命恢复");
            builder.add("carpet.rule.legacyEnchantedGoldenApple.desc",
                    "恢复 1.9 前的附魔金苹果再生效果：再生 V（30 秒）替代新版的再生 II（20 秒），其他效果保持新版不变。");
            builder.add("carpet.rule.silkTouchBuddingAmethyst.name", "精准采集紫水晶母岩");
            builder.add("carpet.rule.silkTouchBuddingAmethyst.desc", "允许使用精准采集工具采集紫水晶母岩。");
            builder.add("carpet.rule.silkTouchSuspiciousBlocks.name", "精准采集可疑方块");
            builder.add("carpet.rule.silkTouchSuspiciousBlocks.desc", "允许使用精准采集工具采集可疑的沙子和砂砾。");
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
        }
    }
}
