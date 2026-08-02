package com.namelessgod2008;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.namelessgod2008.setting.CarpetTNGSetting;

import java.util.HashMap;
import java.util.Map;

public class CarpetTNGExtension implements CarpetExtension {

    private static final Map<String, Map<String, String>> TRANSLATIONS = Map.of(
            "en_us", Map.ofEntries(
                    Map.entry("carpet.rule.bedrockCauldronTippedArrows.name", "Bedrock Cauldron Tipped Arrows"),
                    Map.entry("carpet.rule.bedrockCauldronTippedArrows.desc",
                            "Pour potions into empty cauldrons and dip arrows to create tipped arrows, just like in Bedrock Edition. One potion bottle = 16 arrows."),
                    Map.entry("carpet.rule.craftableSaddle.name", "Craftable Saddle"),
                    Map.entry("carpet.rule.craftableSaddle.desc", "Adds a crafting recipe for the saddle."),
                    Map.entry("carpet.rule.craftableNameTag.name", "Craftable Name Tag"),
                    Map.entry("carpet.rule.craftableNameTag.desc", "Adds a crafting recipe for the name tag."),
                    Map.entry("carpet.rule.craftableBell.name", "Craftable Bell"),
                    Map.entry("carpet.rule.craftableBell.desc", "Adds a crafting recipe for the bell."),
                    Map.entry("carpet.rule.dispenserPlanting.name", "Dispenser Planting"),
                    Map.entry("carpet.rule.dispenserPlanting.desc",
                            "Dispensers shoot wheat, beetroot, carrot and potato seeds in a straight line; seeds turn into crop sprouts when hitting farmland."),
                    Map.entry("carpet.rule.dispenserPlantingGourds.name", "Dispenser Planting Gourds"),
                    Map.entry("carpet.rule.dispenserPlantingGourds.desc",
                            "Dispensers shoot melon and pumpkin seeds in a straight line; seeds turn into stems when hitting farmland."),
                    Map.entry("carpet.rule.dispenserPlantingNetherWart.name", "Dispenser Planting Nether Wart"),
                    Map.entry("carpet.rule.dispenserPlantingNetherWart.desc",
                            "Dispensers shoot nether wart in a straight line; it sprouts when hitting soul sand."),
                    Map.entry("carpet.rule.bonemealGourdFruit.name", "Bonemeal Gourd Fruit"),
                    Map.entry("carpet.rule.bonemealGourdFruit.desc",
                            "Chance that bonemealing a mature melon or pumpkin stem grows a fruit on an adjacent block. 0 disables the feature; 1 means always fruiting."),
                    Map.entry("carpet.rule.dispenserGourdFruit.name", "Dispenser Gourd Fruit"),
                    Map.entry("carpet.rule.dispenserGourdFruit.desc",
                            "Dispensers facing a mature melon or pumpkin stem use bone meal on it, growing a fruit with the Bonemeal Gourd Fruit chance. Requires the Bonemeal Gourd Fruit rule."),
                    Map.entry("carpet.rule.reinforcedObsidian.name", "Reinforced Obsidian"),
                    Map.entry("carpet.rule.reinforcedObsidian.desc",
                            "Obsidian becomes immune to the Wither's block-breaking burst (the one it emits after taking damage); the Wither can no longer destroy obsidian. Regular explosions already cannot break obsidian."),
                    Map.entry("carpet.rule.shortenedPiglinBarterCooldown.name", "Shortened Piglin Barter Cooldown"),
                    Map.entry("carpet.rule.shortenedPiglinBarterCooldown.desc",
                            "Sets how many ticks a piglin admires a gold ingot before bartering. Vanilla is 119 ticks; 0 makes bartering instant. Options: 119, 89, 59, 29, 0."),
                    Map.entry("carpet.rule.neutralPiglins.name", "Neutral Piglins"),
                    Map.entry("carpet.rule.neutralPiglins.desc",
                            "Piglins no longer proactively attack players who are not wearing gold armor, but the pack-aggression mechanic (e.g. after you attack a piglin or a piglin brute) still works as vanilla."),
                    Map.entry("carpet.rule.craftableStringFromWool.name", "Craftable String From Wool"),
                    Map.entry("carpet.rule.craftableStringFromWool.desc",
                            "Adds a crafting recipe to convert wool back into string (1 wool = 4 string)."),
                    Map.entry("carpet.rule.legacyEnchantedGoldenApple.name", "Legacy Enchanted Golden Apple"),
                    Map.entry("carpet.rule.legacyEnchantedGoldenApple.desc",
                            "Restores the pre-1.9 enchanted golden apple Regeneration V (30s) effect, replacing Regeneration II (20s). Other effects stay as in the new version."),
                    Map.entry("carpet.rule.silkTouchBuddingAmethyst.name", "Silk Touch Budding Amethyst"),
                    Map.entry("carpet.rule.silkTouchBuddingAmethyst.desc",
                            "Allows collecting budding amethyst blocks with a Silk Touch tool."),
                    Map.entry("carpet.rule.silkTouchSuspiciousBlocks.name", "Silk Touch Suspicious Blocks"),
                    Map.entry("carpet.rule.silkTouchSuspiciousBlocks.desc",
                            "Allows collecting suspicious sand and gravel with a Silk Touch tool."),
                    Map.entry("carpet.rule.shortenedTrialSpawnerCooldown.name", "Shortened Trial Spawner Cooldown"),
                    Map.entry("carpet.rule.shortenedTrialSpawnerCooldown.desc",
                            "Shortens the trial spawner cooldown to 5 minutes (instead of the vanilla 30 minutes)."),
                    Map.entry("carpet.rule.brewableOminousPotion.name", "Brewable Ominous Potion"),
                    Map.entry("carpet.rule.brewableOminousPotion.desc",
                            "Brew awkward potion with a totem of undying for Ominous Potion I; upgrade with glowstone dust up to level V. Duration stays 1h40m (Bad Omen), same as vanilla."),
                    Map.entry("carpet.rule.blazePowderNetherWartGrowth.name", "Blaze Powder Nether Wart Growth"),
                    Map.entry("carpet.rule.blazePowderNetherWartGrowth.desc",
                            "Right-click nether wart with blaze powder to advance one growth stage. Vanilla bonemeal does not work on nether wart."),
                    Map.entry("carpet.rule.dispenserNetherWartGrowth.name", "Dispenser Nether Wart Growth"),
                    Map.entry("carpet.rule.dispenserNetherWartGrowth.desc",
                            "Dispensers facing nether wart fertilize it with blaze powder, advancing one growth stage per use. Requires the Blaze Powder Nether Wart Growth rule.")
            ),
            "zh_cn", Map.ofEntries(
                    Map.entry("carpet.rule.bedrockCauldronTippedArrows.name", "基岩版炼药锅制箭"),
                    Map.entry("carpet.rule.bedrockCauldronTippedArrows.desc",
                            "将药水倒入空的炼药锅，用箭蘸取获得药箭，类似基岩版机制。一瓶药水 = 16 支箭。"),
                    Map.entry("carpet.rule.craftableSaddle.name", "马鞍合成"),
                    Map.entry("carpet.rule.craftableSaddle.desc", "添加马鞍的合成配方。"),
                    Map.entry("carpet.rule.craftableNameTag.name", "命名牌合成"),
                    Map.entry("carpet.rule.craftableNameTag.desc", "添加命名牌的合成配方。"),
                    Map.entry("carpet.rule.craftableBell.name", "钟合成"),
                    Map.entry("carpet.rule.craftableBell.desc", "添加钟的合成配方。"),
                    Map.entry("carpet.rule.dispenserPlanting.name", "发射器种植"),
                    Map.entry("carpet.rule.dispenserPlanting.desc", "发射器喷出小麦、甜菜、胡萝卜、马铃薯种子，沿直线飞行，碰到耕地时变为作物幼苗。"),
                    Map.entry("carpet.rule.dispenserPlantingGourds.name", "发射器种植西瓜南瓜"),
                    Map.entry("carpet.rule.dispenserPlantingGourds.desc", "发射器喷出西瓜、南瓜种子，沿直线飞行，碰到耕地时变为瓜藤。"),
                    Map.entry("carpet.rule.dispenserPlantingNetherWart.name", "发射器种植地狱疣"),
                    Map.entry("carpet.rule.dispenserPlantingNetherWart.desc", "发射器喷出地狱疣，沿直线飞行，碰到灵魂沙时种下。"),
                    Map.entry("carpet.rule.bonemealGourdFruit.name", "骨粉产瓜"),
                    Map.entry("carpet.rule.bonemealGourdFruit.desc",
                            "对成熟的西瓜/南瓜瓜苗使用骨粉时，按所选概率在相邻格结出果实。0 为禁用，1 为必定产瓜。"),
                    Map.entry("carpet.rule.dispenserGourdFruit.name", "发射器骨粉催瓜产果"),
                    Map.entry("carpet.rule.dispenserGourdFruit.desc",
                            "发射器正对成熟的西瓜/南瓜瓜苗喷射骨粉，按骨粉产瓜规则的概率结出果实。需先开启骨粉产瓜规则。"),
                    Map.entry("carpet.rule.reinforcedObsidian.name", "坚固黑曜石"),
                    Map.entry("carpet.rule.reinforcedObsidian.desc",
                            "黑曜石免疫凋零的主动方块破坏（凋零受击后的破坏冲击），凋零不再能破坏黑曜石。"),
                    Map.entry("carpet.rule.shortenedPiglinBarterCooldown.name", "缩短猪灵交易冷却"),
                    Map.entry("carpet.rule.shortenedPiglinBarterCooldown.desc",
                            "设置猪灵交易前欣赏金锭的时长（tick）。原版为 119 tick，0 为立即交易。可选：119、89、59、29、0。"),
                    Map.entry("carpet.rule.neutralPiglins.name", "完全中立猪灵"),
                    Map.entry("carpet.rule.neutralPiglins.desc",
                            "猪灵不再主动攻击未穿金护甲的玩家；但群起而攻之的仇恨机制（如攻击猪灵或猪灵蛮兵后）保持原版。"),
                    Map.entry("carpet.rule.craftableStringFromWool.name", "羊毛合成线"),
                    Map.entry("carpet.rule.craftableStringFromWool.desc", "添加羊毛合成线的配方（1 羊毛 = 4 线）。"),
                    Map.entry("carpet.rule.legacyEnchantedGoldenApple.name", "旧版附魔金苹果生命恢复"),
                    Map.entry("carpet.rule.legacyEnchantedGoldenApple.desc",
                            "恢复 1.9 前的附魔金苹果再生效果：再生 V（30 秒）替代新版的再生 II（20 秒），其他效果保持新版不变。"),
                    Map.entry("carpet.rule.silkTouchBuddingAmethyst.name", "精准采集紫水晶母岩"),
                    Map.entry("carpet.rule.silkTouchBuddingAmethyst.desc", "允许使用精准采集工具采集紫水晶母岩。"),
                    Map.entry("carpet.rule.silkTouchSuspiciousBlocks.name", "精准采集可疑方块"),
                    Map.entry("carpet.rule.silkTouchSuspiciousBlocks.desc", "允许使用精准采集工具采集可疑的沙子和砂砾。"),
                    Map.entry("carpet.rule.shortenedTrialSpawnerCooldown.name", "缩短试炼刷怪笼冷却"),
                    Map.entry("carpet.rule.shortenedTrialSpawnerCooldown.desc", "将试炼刷怪笼冷却缩短为 5 分钟（原版为 30 分钟）。"),
                    Map.entry("carpet.rule.brewableOminousPotion.name", "灾厄药水酿造"),
                    Map.entry("carpet.rule.brewableOminousPotion.desc",
                            "用不死图腾酿造粗制的药水获得 1 级灾厄药水，用萤石粉继续酿造可提升等级（最多 5 级），持续时间始终为 1 小时 40 分钟（不祥之兆），与原版一致。"),
                    Map.entry("carpet.rule.blazePowderNetherWartGrowth.name", "烈焰粉催熟地狱疣"),
                    Map.entry("carpet.rule.blazePowderNetherWartGrowth.desc",
                            "手持烈焰粉右键地狱疣可推进一个生长阶段（原版骨粉对地狱疣无效）。"),
                    Map.entry("carpet.rule.dispenserNetherWartGrowth.name", "发射器催熟地狱疣"),
                    Map.entry("carpet.rule.dispenserNetherWartGrowth.desc",
                            "发射器正对地狱疣喷射烈焰粉可推进一个生长阶段，每次消耗 1 个烈焰粉。需先开启烈焰粉催熟地狱疣规则。")
            )
    );

    @Override
    public void onGameStarted() {
        // Register rules under Carpet's main /carpet command
        CarpetServer.settingsManager.parseSettingsClass(CarpetTNGSetting.class);
    }

    @Override
    public String version() {
        return "1.0.0";
    }

    @Override
    public Map<String, String> canHasTranslations(String lang) {
        Map<String, String> fallback = TRANSLATIONS.get("en_us");
        Map<String, String> translations = TRANSLATIONS.getOrDefault(lang, fallback);
        Map<String, String> result = new HashMap<>(fallback);
        result.putAll(translations);
        return result;
    }
}
