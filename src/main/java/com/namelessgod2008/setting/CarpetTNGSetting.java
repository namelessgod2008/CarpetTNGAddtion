package com.namelessgod2008.setting;

import carpet.api.settings.Rule;

public class CarpetTNGSetting {

    // ==================== 合成配方 ====================

    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean craftableSaddle = false;

    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean craftableNameTag = false;

    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean craftableBell = false;

    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean craftableStringFromWool = false;

    // ==================== 炼药锅 ====================

    @Rule(categories = {"porting", "TNG"}, options = {"true", "false"})
    public static boolean bedrockCauldronTippedArrows = false;

    // ==================== 发射器种植 ====================

    @Rule(categories = {"feature", "TNG", "dispenser"}, options = {"true", "false"})
    public static boolean dispenserPlanting = false;

    @Rule(categories = {"feature", "TNG", "dispenser"}, options = {"true", "false"})
    public static boolean dispenserPlantingGourds = false;

    @Rule(categories = {"feature", "TNG", "dispenser"}, options = {"true", "false"})
    public static boolean dispenserPlantingNetherWart = false;

    // ==================== 精准采集 ====================

    @Rule(categories = {"TNG", "survival"}, options = {"true", "false"})
    public static boolean silkTouchBuddingAmethyst = false;

    @Rule(categories = {"TNG", "survival"}, options = {"true", "false"})
    public static boolean silkTouchSuspiciousBlocks = false;

    // ==================== 附魔金苹果 ====================

    @Rule(categories = {"porting", "TNG"}, options = {"true", "false"})
    public static boolean legacyEnchantedGoldenApple = false;

    // ==================== 试炼刷怪笼 ====================

    @Rule(categories = {"feature", "TNG"}, options = {"true", "false"})
    public static boolean shortenedTrialSpawnerCooldown = false;

    // ==================== 灾厄药水 ====================

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean brewableOminousPotion = false;

    // ==================== 地狱疣催熟 ====================

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean blazePowderNetherWartGrowth = false;

    @Rule(categories = {"feature", "TNG", "dispenser"}, options = {"true", "false"}, validators = DependencyWarningValidator.class)
    public static boolean dispenserNetherWartGrowth = false;

    // ==================== 瓜类产果 ====================

    @Rule(categories = {"feature", "TNG", "dispenser"}, options = {"true", "false"}, validators = DependencyWarningValidator.class)
    public static boolean dispenserGourdFruit = false;

    // 必须显式 strict = false：Rule.strict() 默认 true，写 options 会触发
    // StrictValidator 按字符串比对（double 默认值 "0.0" ∉ options → 启动崩溃）
    // options 必须用 toRuleString 形式（"0.0" 而非 "0"）：与默认值字符串一致，
    // 否则分类页同时显示当前值 "0.0" 和选项 "0" 造成重复（参考 AMS 的
    // renewableNetheriteScrap 写法）；用户输入 0 或 0.0 均可（validator 数值比较）
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"0.0", "0.1", "0.15", "0.2", "0.25", "0.5", "0.75", "1.0"}, strict = false, validators = GourdFruitValidator.class)
    public static double bonemealGourdFruit = 0.0;

    // ==================== 坚固黑曜石 ====================

    @Rule(categories = {"feature", "TNG"}, options = {"true", "false"})
    public static boolean reinforcedObsidian = false;

    // ==================== 猪灵 ====================

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"119", "89", "59", "29", "0"}, strict = false, validators = PiglinBarterValidator.class)
    public static int shortenedPiglinBarterCooldown = 119;

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean neutralPiglins = false;

    // ==================== 末影龙 ====================

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean constantHighEnderDragonXp = false;

    // ==================== 铁砧 ====================

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean removeAnvilTooExpensive = false;

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean cheapAnvilRename = false;

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean durableAnvil = false;
}
