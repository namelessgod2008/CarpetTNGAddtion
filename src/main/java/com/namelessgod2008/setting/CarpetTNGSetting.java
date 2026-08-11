package com.namelessgod2008.setting;

import carpet.api.settings.Rule;

public class CarpetTNGSetting {

    // ==================== 合成配方 ====================
    // 配方类规则用 @RecipeRule 标记：RecipeRuleRegistry 反射收集，
    // 规则开关时自动 reloadResources 让带条件配方 (de)注册。

    @RecipeRule
    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean craftableSaddle = false;

    @RecipeRule
    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean craftableNameTag = false;

    @RecipeRule
    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean craftableBell = false;

    @RecipeRule
    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean craftableStringFromWool = false;

    @RecipeRule
    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean craftableCobwebs = false;

    @RecipeRule
    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean craftableHorseArmor = false;

    @RecipeRule
    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean craftableBlueIce = false;

    @RecipeRule
    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean boneToBoneBlock = false;

    @RecipeRule
    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean woodToChest = false;

    @RecipeRule
    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean dropperAndBowToDispenser = false;

    @RecipeRule
    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean shapelessCraftingBread = false;

    @RecipeRule
    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean shapelessCraftingPaper = false;

    @RecipeRule
    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean shapelessCraftingShulkerBox = false;

    @RecipeRule
    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean quartzBlockToQuartz = false;

    // ==================== 高炉烧沙 ====================

    @RecipeRule
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean blastFurnaceGlass = false;

    @RecipeRule
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean blastFurnaceGlazedTerracotta = false;

    @RecipeRule
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean blastFurnaceNetherBrick = false;

    @RecipeRule
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean blastFurnaceSmoothQuartz = false;

    @RecipeRule
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean blastFurnaceStone = false;

    @RecipeRule
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean blastFurnaceSmoothStone = false;

    @RecipeRule
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean smokerGreenDye = false;

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

    @Rule(categories = {"TNG", "survival"}, options = {"true", "false"})
    public static boolean silkTouchSpawners = false;

    @Rule(categories = {"TNG", "survival"}, options = {"true", "false"})
    public static boolean silkTouchPathBlocks = false;

    @Rule(categories = {"TNG", "survival"}, options = {"true", "false"})
    public static boolean silkTouchFarmland = false;

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean featherFallingProtectsFarmland = false;

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

    // ==================== 玄武岩转黑石 ====================

    @Rule(categories = {"feature", "TNG"}, options = {"true", "false"})
    public static boolean basaltToBlackstoneConversion = false;

    // ==================== 苦力怕 / 恶魂 ====================

    @Rule(categories = {"feature", "TNG"}, options = {"true", "false"})
    public static boolean stopCreeperGriefing = false;

    @Rule(categories = {"feature", "TNG"}, options = {"true", "false"})
    public static boolean stopGhastGriefing = false;

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean villagerBedExplosion = false;

    // 默认开启（原版行为）：末地水晶只能在黑曜石/基岩上放置。
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean endCrystalPlacementRestriction = true;

    @Rule(categories = {"feature", "TNG"}, options = {"true", "false"})
    public static boolean stopEndCrystalGriefing = false;

    // ==================== 雪傀儡 ====================

    // 雪傀儡不再因炎热生物群系（沙漠/下界/恶地等）而融化受伤
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean snowGolemNoMelt = false;

    // ==================== 附魔 ====================

    // 不同类型保护魔咒（保护/爆炸保护/火焰保护/弹射物保护）可以叠加在同一件装备上
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean stackableProtection = false;

    // 村民被闪电击中后不会变成女巫
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean villagerLightningNoWitch = false;

    // 诅咒附魔（绑定诅咒/消失诅咒）可以通过砂轮移除
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean grindstoneRemovesCurses = false;

    // 唤魔者死亡后，其召唤的恼鬼立即死亡
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean evokerDeathKillsVexes = false;

    // 手持锹右键雪层方块可以铲掉一层雪
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean shovelSnowLayer = false;

    // 锹铲雪时掉落一个雪球（依赖 shovelSnowLayer）
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"}, validators = DependencyWarningValidator.class)
    public static boolean shovelSnowLayerDropSnowball = false;

    // 蝌蚪喂食染料标记长大后青蛙的颜色（映射到现有 3 种变体）
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean tadpoleDyeColor = false;

    // 抢夺附魔击杀大史莱姆/岩浆怪后多分裂小史莱姆（每级多 1 个）
    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean lootingSlimeSplit = false;

    // ==================== 命令 ====================

    // 启用 /mods 命令：列出服务器安装的所有 Mod（需要服务器重启后生效）
    @Rule(categories = {"feature", "TNG", "command"}, options = {"true", "false"})
    public static boolean commandMods = false;

    // 启用 /addEnchantment 命令：给手上物品添加附魔（<附魔> <等级>）
    @Rule(categories = {"feature", "TNG", "command"}, options = {"true", "false"})
    public static boolean commandAddEnchantment = false;

    // ==================== 铜氧化 ====================

    // 铜接触水时的氧化速度倍率（自由数值，无 options 限制，与 piglinBarterDisabledTime 同模式）。
    // 1.0 = 原版速率（不加速也不减速）；大于 1 加快，小于 1 减慢。
    // 原版氧化无水下判定，仅当方块接触水（自身被淹没或相邻面有水）时该倍率生效。
    @Rule(categories = {"feature", "TNG", "survival"})
    public static double copperUnderwaterOxidationMultiplier = 1.0;

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean splashOxidizeCopper = false;

    // ==================== 猪灵 ====================

    // 自定义猪灵受击拒绝交易时间（模仿 ORG customPiglinBarteringTime 的自由数值方式，无 options 限制）
    @Rule(categories = {"feature", "TNG", "survival"})
    public static int piglinBarterDisabledTime = 400;

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean neutralPiglins = false;

    // ==================== 末影龙 ====================

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean constantHighEnderDragonXp = false;

    // ==================== 铁砧 ====================

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"}, validators = OrgCompatWarningValidator.class)
    public static boolean removeAnvilTooExpensive = false;

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"}, validators = OrgCompatWarningValidator.class)
    public static boolean cheapAnvilRename = false;

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean durableAnvil = false;

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean durableFallingAnvil = false;

    // ==================== 烈焰棒调试 ====================

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"})
    public static boolean blazeStickDebug = false;

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"}, validators = {DependencyWarningValidator.class, OrgCompatWarningValidator.class})
    public static boolean blazeStickFurnaceXp = false;

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"}, validators = DependencyWarningValidator.class)
    public static boolean blazeStickSmokerXp = false;

    @Rule(categories = {"feature", "TNG", "survival"}, options = {"true", "false"}, validators = DependencyWarningValidator.class)
    public static boolean blazeStickBlastFurnaceXp = false;
}
