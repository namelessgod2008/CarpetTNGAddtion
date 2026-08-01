package com.namelessgod2008;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.namelessgod2008.setting.CarpetTNGSetting;

import java.util.HashMap;
import java.util.Map;

public class CarpetTNGExtension implements CarpetExtension {

    private static final Map<String, Map<String, String>> TRANSLATIONS = Map.of(
            "en_us", Map.of(
                    "carpet.rule.bedrockCauldronTippedArrows.name", "Bedrock Cauldron Tipped Arrows",
                    "carpet.rule.bedrockCauldronTippedArrows.desc",
                        "Pour potions into empty cauldrons and dip arrows to create tipped arrows, just like in Bedrock Edition. One potion bottle = 16 arrows.",
                    "carpet.rule.craftableSaddle.name", "Craftable Saddle",
                    "carpet.rule.craftableSaddle.desc", "Adds a crafting recipe for the saddle.",
                    "carpet.rule.craftableNameTag.name", "Craftable Name Tag",
                    "carpet.rule.craftableNameTag.desc", "Adds a crafting recipe for the name tag.",
                    "carpet.rule.craftableBell.name", "Craftable Bell",
                    "carpet.rule.craftableBell.desc", "Adds a crafting recipe for the bell.",
                    "carpet.rule.dispenserPlanting.name", "Dispenser Planting",
                    "carpet.rule.dispenserPlanting.desc", "Dispensers shoot seeds in a straight line; seeds turn into crop sprouts when hitting farmland."
            ),
            "zh_cn", Map.of(
                    "carpet.rule.bedrockCauldronTippedArrows.name", "基岩版炼药锅制箭",
                    "carpet.rule.bedrockCauldronTippedArrows.desc",
                        "将药水倒入空的炼药锅，用箭蘸取获得药箭，类似基岩版机制。一瓶药水 = 16 支箭。",
                    "carpet.rule.craftableSaddle.name", "马鞍合成",
                    "carpet.rule.craftableSaddle.desc", "添加马鞍的合成配方。",
                    "carpet.rule.craftableNameTag.name", "命名牌合成",
                    "carpet.rule.craftableNameTag.desc", "添加命名牌的合成配方。",
                    "carpet.rule.craftableBell.name", "钟合成",
                    "carpet.rule.craftableBell.desc", "添加钟的合成配方。",
                    "carpet.rule.dispenserPlanting.name", "发射器种植",
                    "carpet.rule.dispenserPlanting.desc", "发射器喷出种子沿直线飞行，碰到耕地时变为作物幼苗。"
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
