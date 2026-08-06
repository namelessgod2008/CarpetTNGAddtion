# CarpetTNGAddtion

## English

CarpetTNGAddtion is a Carpet addon for Minecraft 1.21.4 (Fabric), porting Bedrock Edition and newer mechanics into the current version. Every feature is a Carpet rule, disabled by default, toggled anytime with the /carpet command, so vanilla gameplay stays untouched. It contains 32 rules:

1. **Crafting Recipes** (craftableSaddle / craftableNameTag / craftableBell / craftableStringFromWool): craftable saddle (3 leather + 1 iron ingot), name tag (iron nugget + paper), bell (3 gold ingots, 2 sticks and 3 smooth stone slabs), and string from wool (1 wool = 4 string).
2. **Bedrock Cauldron Tipped Arrows** (bedrockCauldronTippedArrows): pour potions into an empty cauldron and dip arrows to make tipped arrows. One potion bottle makes 16 arrows.
3. **Dispenser Planting** (dispenserPlanting / dispenserPlantingGourds / dispenserPlantingNetherWart): dispensers shoot seeds in a straight line; seeds sprout into crops when hitting farmland, gourds become stems, and nether wart sprouts on soul sand.
4. **Silk Touch Collection** (silkTouchBuddingAmethyst / silkTouchSuspiciousBlocks): collect budding amethyst blocks, suspicious sand and gravel with a Silk Touch tool.
5. **Legacy Enchanted Golden Apple** (legacyEnchantedGoldenApple): restores Regeneration V (30 seconds) for the enchanted golden apple.
6. **Shortened Trial Spawner Cooldown** (shortenedTrialSpawnerCooldown): trial spawner cooldown shortened from 30 minutes to 5 minutes.
7. **Brewable Ominous Potion** (brewableOminousPotion): brew awkward potion with a totem of undying to get Ominous Potion, upgrade with glowstone dust up to level V.
8. **Nether Wart Growth** (blazePowderNetherWartGrowth / dispenserNetherWartGrowth): right-click nether wart with blaze powder or use a dispenser to advance one growth stage.
9. **Gourd Fruiting** (bonemealGourdFruit / dispenserGourdFruit): bonemealing a mature melon or pumpkin stem grows a fruit on an adjacent block with a configurable chance; dispensers work too.
10. **Reinforced Obsidian** (reinforcedObsidian): the Wither can no longer destroy obsidian.
11. **Piglin Tweaks** (piglinBarterDisabledTime / neutralPiglins): customize how long a piglin refuses to barter after being hit; piglins no longer proactively attack players without gold armor.
12. **Constant High Ender Dragon XP** (constantHighEnderDragonXp): every ender dragon kill drops the first-kill experience amount (12000).
13. **Anvil Improvements** (removeAnvilTooExpensive / cheapAnvilRename / durableAnvil / durableFallingAnvil): no more "Too Expensive" at 40 levels, renaming always costs 1 level, using an anvil no longer damages it, and falling anvils never break.
14. **Blaze Stick Debug** (blazeStickDebug / blazeStickFurnaceXp / blazeStickSmokerXp / blazeStickBlastFurnaceXp): while holding a blaze rod, right-clicking a furnace, smoker or blast furnace does not open its GUI; instead it clears all accumulated experience and spawns the XP orbs at your position.
15. **Basalt to Blackstone Conversion** (basaltToBlackstoneConversion): basalt that touches both lava and water at the same time converts to blackstone.
16. **Blast Furnace Glass** (blastFurnaceGlass): blast furnaces can smelt sand and red sand into glass.
17. **Blast Furnace Glazed Terracotta** (blastFurnaceGlazedTerracotta): blast furnaces can smelt all 16 colors of terracotta into the glazed terracotta of the same color.

Requirements: Minecraft 1.21.4, Fabric Loader 0.19.3 or later, Fabric API 0.119.4 or later, Carpet 1.4.161 or later, Java 21 or later.

License: MIT.

## 中文

CarpetTNGAddtion 是 Minecraft 1.21.4（Fabric）的 Carpet 附属模组，将基岩版与后续版本的游戏机制移植到当前版本。所有功能均为 Carpet 规则，默认全部关闭，随时可通过 /carpet 指令开关，不影响原版体验。共包含 32 项规则：

1. **合成配方**（craftableSaddle / craftableNameTag / craftableBell / craftableStringFromWool）：马鞍（3 皮革加 1 铁锭）、命名牌（铁粒加纸）、钟（3 金锭、2 木棍加 3 平滑石台阶）、羊毛合成线（1 羊毛 = 4 线）。
2. **基岩版炼药锅制箭**（bedrockCauldronTippedArrows）：将药水倒入空的炼药锅，用箭蘸取制成药箭，一瓶药水可制作 16 支药箭。
3. **发射器种植**（dispenserPlanting / dispenserPlantingGourds / dispenserPlantingNetherWart）：发射器喷射种子沿直线飞行，命中耕地变为作物幼苗、瓜藤，命中灵魂沙种下地狱疣。
4. **精准采集**（silkTouchBuddingAmethyst / silkTouchSuspiciousBlocks）：使用精准采集工具可采集紫水晶母岩、可疑的沙子和砂砾。
5. **旧版附魔金苹果**（legacyEnchantedGoldenApple）：恢复附魔金苹果的再生 V 效果（30 秒）。
6. **试炼刷怪笼冷却**（shortenedTrialSpawnerCooldown）：试炼刷怪笼冷却从 30 分钟缩短为 5 分钟。
7. **灾厄药水酿造**（brewableOminousPotion）：粗制药水加不死图腾酿造灾厄药水，萤石粉可继续升级至 V 级。
8. **地狱疣催熟**（blazePowderNetherWartGrowth / dispenserNetherWartGrowth）：手持烈焰粉右键地狱疣或发射器喷射烈焰粉，推进一个生长阶段。
9. **骨粉产瓜**（bonemealGourdFruit / dispenserGourdFruit）：对成熟的西瓜/南瓜瓜苗使用骨粉按可配置概率在相邻格结瓜，发射器也可使用。
10. **坚固黑曜石**（reinforcedObsidian）：凋零不再能破坏黑曜石。
11. **猪灵调整**（piglinBarterDisabledTime / neutralPiglins）：自定义猪灵受击后拒绝交易的时间；猪灵不再主动攻击未穿金护甲的玩家。
12. **持续高经验打龙**（constantHighEnderDragonXp）：每次击杀末影龙都掉落首次击杀的经验量（12000）。
13. **铁砧改进**（removeAnvilTooExpensive / cheapAnvilRename / durableAnvil / durableFallingAnvil）：40 级不再"过于昂贵"、改名恒 1 级、使用铁砧不再损坏、铁砧摔落不损坏。
14. **烈焰棒调试**（blazeStickDebug / blazeStickFurnaceXp / blazeStickSmokerXp / blazeStickBlastFurnaceXp）：手持烈焰棒右键熔炉、烟熏炉或高炉不会打开界面，而是清空积累的所有经验，经验球出现在玩家所在坐标。
15. **玄武岩转黑石**（basaltToBlackstoneConversion）：同时接触到熔岩和水的玄武岩会转化为黑石。
16. **高炉烧沙成玻璃**（blastFurnaceGlass）：高炉可以将沙子和红沙烧炼为玻璃。
17. **高炉烧制带釉陶瓦**（blastFurnaceGlazedTerracotta）：高炉可以将全部 16 种染色陶瓦烧炼为对应颜色的带釉陶瓦。

环境要求：Minecraft 1.21.4，Fabric Loader 0.19.3 及以上，Fabric API 0.119.4 及以上，Carpet 1.4.161 及以上，Java 21 及以上。

许可：MIT 协议。
