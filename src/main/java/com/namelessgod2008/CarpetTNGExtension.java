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
                            "Dispensers shoot seeds in a straight line; seeds turn into crop sprouts when hitting farmland."),
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
                            "Brew awkward potion with a totem of undying for Ominous Potion I; upgrade with glowstone dust up to level V. Duration stays 1h40m (Bad Omen), same as vanilla.")
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
                    Map.entry("carpet.rule.dispenserPlanting.desc", "发射器喷出种子沿直线飞行，碰到耕地时变为作物幼苗。"),
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
                            "用不死图腾酿造粗制的药水获得 1 级灾厄药水，用萤石粉继续酿造可提升等级（最多 5 级），持续时间始终为 1 小时 40 分钟（不祥之兆），与原版一致。")
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
