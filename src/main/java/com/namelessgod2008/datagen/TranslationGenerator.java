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
                    "Dispensers shoot seeds in a straight line; seeds turn into crop sprouts when hitting farmland.");
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
            builder.add("carpet.rule.dispenserPlanting.desc", "发射器喷出种子沿直线飞行，碰到耕地时变为作物幼苗。");
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
        }
    }
}
