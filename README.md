# CarpetTNGAddtion

Minecraft 1.21.4 (Fabric) 的 [Carpet](https://github.com/gnembon/fabric-carpet) 附属模组，将基岩版与后续版本的游戏机制移植到当前版本（"porting" 类规则）。

## 功能

所有规则默认关闭，通过 `/carpet <name> true` 统一开关，分类均带 `TNG`；合成类规则另带 `survival`，炼药锅制箭与旧版附魔金苹果另带 `porting`，发射器种植另带 `dispenser`：

| 规则 | 功能 | 分类 |
|------|------|------|
| `bedrockCauldronTippedArrows` | 基岩版炼药锅制箭 | porting, TNG |
| `craftableSaddle` | 马鞍合成（3 皮革 + 1 铁锭，无序） | survival, TNG |
| `craftableNameTag` | 命名牌合成（铁粒 + 纸，2×2 对角） | survival, TNG |
| `craftableBell` | 钟合成（3 金锭 + 2 木棍 + 3 平滑石台阶） | survival, TNG |
| `craftableStringFromWool` | 羊毛合成线（1 羊毛 = 4 线） | survival, TNG |
| `craftableCobwebs` | 蜘蛛网合成（9 线 → 蜘蛛网） | survival, TNG |
| `craftableHorseArmor` | 马铠合成（铁/金/钻石，6 材料 + 1 羊毛） | survival, TNG |
| `craftableBlueIce` | 蓝冰合成（8 冰 + 1 蓝色染料） | survival, TNG |
| `boneToBoneBlock` | 骨块合成（3 骨头无序） | survival, TNG |
| `woodToChest` | 箱子合成（2 任意原木无序） | survival, TNG |
| `dropperAndBowToDispenser` | 投掷器加弓合成发射器（1 投掷器 + 1 弓无序） | survival, TNG |
| `shapelessCraftingBread` | 面包无序合成（3 小麦任意排列） | survival, TNG |
| `shapelessCraftingPaper` | 纸无序合成（3 甘蔗任意排列） | survival, TNG |
| `shapelessCraftingShulkerBox` | 潜影盒无序合成（1 箱 + 2 潜影壳任意排列） | survival, TNG |
| `quartzBlockToQuartz` | 石英块拆解（1 石英块 = 4 下界石英） | survival, TNG |
| `blastFurnaceGlass` | 高炉烧沙成玻璃（沙子/红沙 → 玻璃） | feature, TNG, survival |
| `blastFurnaceGlazedTerracotta` | 高炉烧制带釉陶瓦（16 种染色陶瓦 → 对应带釉陶瓦） | feature, TNG, survival |
| `blastFurnaceNetherBrick` | 高炉烧制下界砖（下界岩 → 下界砖） | feature, TNG, survival |
| `blastFurnaceSmoothQuartz` | 高炉烧制平滑石英（石英块 → 平滑石英块） | feature, TNG, survival |
| `blastFurnaceStone` | 高炉烧制石头（圆石 → 石头） | feature, TNG, survival |
| `blastFurnaceSmoothStone` | 高炉烧制平滑石头（石头 → 平滑石头） | feature, TNG, survival |
| `smokerGreenDye` | 烟熏炉烧制绿色染料（仙人掌 → 绿色染料） | feature, TNG, survival |
| `dispenserPlanting` | 发射器直线喷射种子种田（小麦/甜菜/胡萝卜/马铃薯） | feature, TNG, dispenser |
| `dispenserPlantingGourds` | 发射器种植西瓜南瓜（种子→瓜藤） | feature, TNG, dispenser |
| `dispenserPlantingNetherWart` | 发射器种植地狱疣（命中灵魂沙种下） | feature, TNG, dispenser |
| `bonemealGourdFruit` | 骨粉产瓜（成熟瓜苗按概率结瓜，0 禁用~1 必结） | feature, TNG, survival |
| `dispenserGourdFruit` | 发射器骨粉催瓜产果（复用骨粉产瓜概率） | feature, TNG, dispenser |
| `reinforcedObsidian` | 坚固黑曜石（免疫凋零的方块破坏） | feature, TNG |
| `basaltToBlackstoneConversion` | 玄武岩转黑石（同时接触熔岩和水） | feature, TNG |
| `stopCreeperGriefing` | 阻止苦力怕破坏地形（爆炸不破坏方块但保留伤害） | feature, TNG |
| `stopGhastGriefing` | 阻止恶魂破坏地形（火球爆炸不破坏方块但保留伤害） | feature, TNG |
| `villagerBedExplosion` | 村民睡床爆炸（下界/末地用床会爆炸） | feature, TNG, survival |
| `endCrystalPlacementRestriction` | 末地水晶放置限制（默认开=只能放黑曜石/基岩） | feature, TNG, survival |
| `stopEndCrystalGriefing` | 阻止末地水晶破坏地形（爆炸不破坏方块但保留伤害） | feature, TNG |
| `commandMods` | 启用 /mods 命令（列出服务器所有已安装 Mod） | feature, TNG, command |
| `commandAddEnchantment` | 启用 /addEnchantment 命令（给主手物品加附魔） | feature, TNG, command |
| `snowGolemNoMelt` | 雪傀儡不融化（炎热生物群系不再受伤） | feature, TNG, survival |
| `stackableProtection` | 保护魔咒可叠加（不同类型保护可附同一装备） | feature, TNG, survival |
| `villagerLightningNoWitch` | 村民雷击不变女巫（被闪电击中保持村民） | feature, TNG, survival |
| `grindstoneRemovesCurses` | 砂轮移除诅咒（绑定/消失诅咒可移除） | feature, TNG, survival |
| `evokerDeathKillsVexes` | 唤魔者死亡恼鬼死亡（恼鬼立即消失） | feature, TNG, survival |
| `shovelSnowLayer` | 锹铲雪层（右键雪层铲掉一层） | feature, TNG, survival |
| `shovelSnowLayerDropSnowball` | 锹铲雪掉雪球（每层掉 1 个雪球，依赖铲雪） | feature, TNG, survival |
| `tadpoleDyeColor` | 蝌蚪喂染料定色（长大后青蛙颜色） | feature, TNG, survival |
| `lootingSlimeSplit` | 抢夺史莱姆分裂（附抢夺击杀大史莱姆多分裂） | feature, TNG, survival |
| `copperUnderwaterOxidationMultiplier` | 铜水下氧化倍率（接触水时氧化速度倍率，默认 1.0=原版） | feature, TNG, survival |
| `splashOxidizeCopper` | 喷溅水瓶氧化铜（投掷水瓶使铜氧化到下一阶段） | feature, TNG, survival |
| `piglinBarterDisabledTime` | 自定义猪灵受击拒绝交易时间（tick，默认 400） | feature, TNG, survival |
| `neutralPiglins` | 完全中立猪灵（不主动攻击无金甲玩家） | feature, TNG, survival |
| `constantHighEnderDragonXp` | 持续高经验打龙（重复击杀也掉 12000 经验） | feature, TNG, survival |
| `removeAnvilTooExpensive` | 移除铁砧过于昂贵（费用 ≥40 级仍可操作） | feature, TNG, survival |
| `cheapAnvilRename` | 铁砧低价改名（改名恒 1 级，免疫过于昂贵） | feature, TNG, survival |
| `durableAnvil` | 耐用的铁砧（使用操作不损坏铁砧） | feature, TNG, survival |
| `durableFallingAnvil` | 耐摔的铁砧（掉落不损坏铁砧） | feature, TNG, survival |
| `blazeStickDebug` | 烈焰棒调试（烈焰棒特殊功能总开关） | feature, TNG, survival |
| `blazeStickFurnaceXp` | 烈焰棒掏炉渣（右键熔炉清经验，不掉 GUI） | feature, TNG, survival |
| `blazeStickSmokerXp` | 烈焰棒掏烟熏炉渣（右键烟熏炉清经验） | feature, TNG, survival |
| `blazeStickBlastFurnaceXp` | 烈焰棒掏高炉渣（右键高炉清经验） | feature, TNG, survival |
| `legacyEnchantedGoldenApple` | 旧版附魔金苹果再生 V（30 秒） | porting, TNG |
| `silkTouchBuddingAmethyst` | 精准采集紫水晶母岩 | TNG, survival |
| `silkTouchSuspiciousBlocks` | 精准采集可疑沙/砂砾 | TNG, survival |
| `silkTouchSpawners` | 精准采集刷怪笼（掉落自身并保留配置） | TNG, survival |
| `silkTouchPathBlocks` | 精准采集土径（掉落土径自身而非泥土） | TNG, survival |
| `silkTouchFarmland` | 精准采集耕地（掉落耕地自身而非泥土） | TNG, survival |
| `featherFallingProtectsFarmland` | 摔落缓冲保护耕地（穿摔落缓冲靴子不踩坏耕地） | feature, TNG, survival |
| `shortenedTrialSpawnerCooldown` | 试炼刷怪笼冷却缩短为 5 分钟 | feature, TNG, survival |
| `brewableOminousPotion` | 灾厄药水酿造（图腾 + 萤石，I–V 级） | feature, TNG, survival |
| `blazePowderNetherWartGrowth` | 烈焰粉催熟地狱疣（右键 +1 级） | feature, TNG, survival |
| `dispenserNetherWartGrowth` | 发射器催熟地狱疣（烈焰粉，+1 级） | feature, TNG, dispenser |

### 基岩版炼药锅制箭

- 将药水 / 喷溅药水 / 滞留药水倒入空的炼药锅，填充 1⁄3 药水位，药水变为玻璃瓶
- 玻璃瓶可装回药水（返回最近一次倒入的瓶子类型），减少 1⁄3 药水位
- 药水效果或等级不同的药水倒入同一炼药锅会清空炼药锅并消耗手中的药水
- 用箭右键炼药锅蘸取获得药箭，药水位消耗规则：

| 箭的数量 | 原药水位消耗 |
|----------|--------------|
| 1-16 | 满锅 1⁄6；5⁄6 锅清空（限 48 支）；2⁄3 锅清空（限 32 支）；1⁄2 及以下清空（限 16 支） |
| 17-32 | 满锅 1⁄3 |
| 33-48 | 满锅 1⁄2 |
| 49-64 | 满锅清空 |

- 药水炼药锅上会持续飘起对应药水颜色的粒子
- 药水数据随世界存档持久化（每维度 `CauldronSavedData`）
- 禁止对药水炼药锅使用原版交互（如空桶取水）

### 发射器种植

- `dispenserPlanting`：发射器喷射小麦、甜菜、胡萝卜、马铃薯种子，沿直线飞行，命中耕地时变为作物幼苗
- `dispenserPlantingGourds`：发射器喷射西瓜、南瓜种子，命中耕地时变为瓜藤
- `dispenserPlantingNetherWart`：发射器喷射地狱疣，命中灵魂沙时种下，可与其他下界功能联动做自动化农场
- 种子飞行数据存于实体字段，不污染物品 NBT（不同发射器喷出的种子可正常堆叠）

### 旧版附魔金苹果

- 恢复 1.9 前的附魔金苹果再生效果：再生 V（30 秒）替代新版的再生 II（20 秒）
- 其余效果（吸收、抗性、抗火）保持新版不变

### 精准采集

- `silkTouchBuddingAmethyst`：精准采集工具可采集紫水晶母岩
- `silkTouchSuspiciousBlocks`：精准采集工具可采集可疑的沙子和砂砾
- `silkTouchSpawners`：精准采集工具挖掘刷怪笼时掉落刷怪笼自身，并完整保留其刷怪配置（实体类型与刷怪池）；原版刷怪笼掉落表为空，任何工具都不掉落物品
- `featherFallingProtectsFarmland`：穿着带摔落缓冲附魔的靴子落到耕地上时，不再把耕地踩成泥土；注入 `FarmBlock.fallOn` 的 `turnToDirt` 调用
- `silkTouchPathBlocks`：精准采集工具挖掘土径时掉落土径自身，而非泥土
- `silkTouchFarmland`：精准采集工具挖掘耕地时掉落耕地自身，而非泥土

### 试炼刷怪笼冷却

- `shortenedTrialSpawnerCooldown`：试炼刷怪笼冷却从原版 30 分钟缩短为 5 分钟
- 关闭规则不影响已在进行中的冷却

### 灾厄药水酿造

- `brewableOminousPotion`：粗制药水 + 不死图腾 → 灾厄药水 I 级；+ 萤石粉继续酿造可升至 V 级
- 效果为不祥之兆，持续时间 1 小时 40 分钟，与原版一致

### 烈焰粉催熟地狱疣

- `blazePowderNetherWartGrowth`：手持烈焰粉右键地狱疣推进一个生长阶段（原版骨粉对地狱疣无效）
- `dispenserNetherWartGrowth`：发射器正对地狱疣喷射烈焰粉推进一个生长阶段，可与发射器种植联动做自动化农场；需先开启 `blazePowderNetherWartGrowth` 才生效

### 铜水下氧化倍率

- `copperUnderwaterOxidationMultiplier`：铜方块接触水（任一相邻面有水：水方块、流水、含水方块都算）时的氧化速度倍率，自由数值，默认 1.0 = 原版速率；大于 1 加快，小于 1 减慢
- 原版氧化不受水影响（随机 tick 驱动，无水下判定），本功能新增"接触水加速/减速"机制
- 实现：注入 7 种铜方块的 `randomTick`（接口 default 方法不可注入，改为逐类注入），接触水时把基础氧化概率 0.05688889F 乘以倍率；保留铜门下半格氧化判定
- `splashOxidizeCopper`：向铜方块投掷喷溅水瓶使其立即氧化到下一阶段；注入 `ThrownPotion.onHitBlock`，仅普通水瓶（Potions.WATER）触发

### 自定义猪灵受击拒绝交易时间

- `piglinBarterDisabledTime`：设置猪灵被玩家攻击后拒绝交易的时间（tick，自由数值），原版 400 tick（20 秒），0 为不拒绝
- 模仿 ORG `customPiglinBarteringTime` 的自由数值实现方式

### 移除铁砧过于昂贵

- `removeAnvilTooExpensive`：铁砧修复/附魔/重命名费用达到 40 级后不再显示"过于昂贵"，操作仍可进行
- 机制：原版 `AnvilMenu.createResult` 在 `cost >= 40` 时清空结果，规则开启时跳过该判定；仅重命名的费用 cap（39 级）保持原版

### 铁砧低价改名

- `cheapAnvilRename`：在铁砧中修改物品名称始终只消耗 1 级经验，且不会被"过于昂贵"拦截（费用恒 1 < 40）
- 仅纯改名操作生效（不涉及修复/附魔时改名）

### 烈焰棒调试

- `blazeStickDebug`：烈焰棒特殊功能的总开关，相关规则（如 `blazeStickFurnaceXp`）需先开启本规则
- `blazeStickFurnaceXp`（熔炉）/ `blazeStickSmokerXp`（烟熏炉）/ `blazeStickBlastFurnaceXp`（高炉）：手持烈焰棒右键对应方块不打开 GUI，清空积累的全部经验并作为经验球出现在玩家所在坐标（1.21.4 熔炉经验按配方计数存储，调用原版 `getRecipesToAwardAndPopExperience` + 手动清空计数）
- **随时可掏**：方块持续烧炼会重新积累经验，每次右键都掏当前积累；每次掏出后计数清空，不会重复掉落

### 耐用的铁砧

- `durableAnvil`：使用铁砧（修复、附魔、改名）不再使其损坏（原版 12% 概率降一级）
- `durableFallingAnvil`：铁砧方块从高处掉落落地时不再因摔落损坏（原版 5% + 每格 5%）
- 铁砧仍可被物理破坏（挖掘掉落等原版行为不变）

### 持续高经验打龙

- `constantHighEnderDragonXp`：击杀末影龙始终掉落首次击杀的经验量（12000），而非重复击杀的 500
- 机制：原版 `EnderDragon.tickDeath` 按 `dragonFight.hasPreviouslyKilledDragon()` 判定经验量，规则开启时恒按首次击杀计算

### 完全中立猪灵

- `neutralPiglins`：猪灵不再主动攻击未穿金护甲的玩家
- 群起而攻之的仇恨机制保留原版：攻击猪灵/猪灵蛮兵后（ANGRY_AT、猪灵蛮兵目标）仍会遭群体反击

### 坚固黑曜石

- `reinforcedObsidian`：黑曜石免疫凋零的主动方块破坏（凋零受击后的破坏冲击），凋零不再能破坏黑曜石
- 机制说明：原版爆炸（强度 7）本就破不了黑曜石（抗性 1200），破坏来自凋零每 20 tick 的主动清方块（`canDestroy` 只看 `WITHER_IMMUNE` tag）

### 玄武岩转黑石

- `basaltToBlackstoneConversion`：同时接触到熔岩和水的玄武岩会转化为黑石
- 触发时机：玄武岩被放置、或相邻方块变化（熔岩/水流到它旁边、液体被放置/移除）时检查
- 接触判定：玄武岩 6 个方向的相邻方块中，至少一面流体为熔岩、至少一面为水（含水方块/含水状态的流体也计入）
- 机制说明：Java 版玄武岩是普通方块（无自有逻辑），通过注入基类 `BlockBehaviour` 的 `onPlace`/`neighborChanged` 并过滤玄武岩实现

### 阻止苦力怕/恶魂破坏地形

- `stopCreeperGriefing`：苦力怕爆炸不再破坏方块，但仍造成伤害和击退（"能炸但炸不坏"）
- `stopGhastGriefing`：恶魂火球爆炸不再破坏方块，但仍造成伤害
- 机制：注入 `ServerExplosion.interactWithBlocks`（实体伤害 `interactWithEntities` 与方块破坏分离），规则开启且爆炸间接源是苦力怕（`getIndirectSourceEntity() instanceof Creeper`）或恶魂（`instanceof Ghast`，恶魂火球的 owner）时 cancel，跳过方块破坏、保留伤害/击退/音效

### 村民睡床爆炸

- `villagerBedExplosion`：村民在下界/末地使用床时，床会像玩家使用一样爆炸（移除床 + 半径 5 爆炸 + 引火）
- 机制：注入 `Villager.startSleeping`（村民入睡专属入口，由 `SleepInBed` 行为调用），维度 `!dimensionType().bedWorks()`（床不起作用的维度）时复刻原版 `BedBlock.useWithoutItem` 的爆炸逻辑并阻止入睡

### 末地水晶放置限制

- `endCrystalPlacementRestriction`：**默认开启**（原版行为）——末地水晶只能放在黑曜石/基岩上；关闭后可在任意方块上放置
- 机制：原版 `EndCrystalItem.useOn` 检查 `!is(OBSIDIAN) && !is(BEDROCK)` 时 FAIL；规则关闭时 `@Redirect` 让 `is(OBSIDIAN)` 恒 true，使条件不成立、允许放置
- `stopEndCrystalGriefing`：末地水晶爆炸不再破坏方块，但仍造成伤害；末地水晶爆炸直接源是 EndCrystal 自身（非 LivingEntity，间接源为 null），用 `getDirectSourceEntity() instanceof EndCrystal` 判断

### 命令 /mods

- `commandMods`：启用 `/mods` 命令，列出服务器安装的所有 Mod（名称 + mod id）；规则变化立即生效
- `commandAddEnchantment`：启用 `/addEnchantment <附魔> <等级>`，给主手物品添加对应附魔
- 机制：`CarpetTNGExtension` override `registerCommands(CommandDispatcher, CommandBuildContext)`，无条件注册命令、用 `requires(source -> 规则)` 谓词实时判权（`command` 分类挂 Carpet 的 `Validator$_COMMAND`，规则变化时刷新客户端命令树，命令立即出现）；`/mods` 的 Mod 列表来自 `FabricLoader.getInstance().getAllMods()`（复刻 Fabric 启动日志层级树）；`/addEnchantment` 用 `StringArgumentType.word` + `IntegerArgumentType.integer` 参数，`source.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).get(id)` 解析附魔，`EnchantmentHelper.updateEnchantments(stack, m -> m.upgrade(holder, level))` 添加

### 雪傀儡不融化

- `snowGolemNoMelt`：雪傀儡在炎热生物群系（沙漠、下界、恶地等，`SNOW_GOLEM_MELTS` tag）不再受到融化伤害
- 机制：注入 `SnowGolem.aiStep`，`@Redirect` 掉 `hurtServer` 调用（原版在炎热生物群系每 tick 受到 1 点火焰伤害）；注意原版雪傀儡**下雨本身不受伤害**，伤害只来自炎热生物群系

### 保护魔咒可叠加

- `stackableProtection`：不同类型的保护魔咒（保护、爆炸保护、火焰保护、弹射物保护）可以附在同一件装备上并叠加效果
- 机制：原版 4 种保护共享 `exclusive_set/armor` tag 互斥（`Enchantment.areCompatible`）；规则开启时注入 `areCompatible` RETURN，两附魔都属该 tag 则视为兼容；伤害减免原版 `getDamageProtection` 已累加不同来源，无需改
- `grindstoneRemovesCurses`：诅咒附魔（绑定诅咒、消失诅咒）可以通过砂轮移除
- 机制：原版 `GrindstoneMenu.removeNonCursesFrom` 用 `removeIf(!is(EnchantmentTags.CURSE))` 保留诅咒；规则开启时 `@Redirect` 让 `is(CURSE)` 返回 false，使诅咒也被移除

### 村民雷击不变女巫

- `villagerLightningNoWitch`：村民被闪电击中后不再变成女巫（仍受雷击，保持村民）
- 机制：注入 `Villager.thunderHit`，`@Redirect` 掉 `convertTo(EntityType.WITCH, ...)` 调用返回 null——原版转化失败时走 `super.thunderHit`（被击中但不变身）

### 唤魔者死亡恼鬼死亡

- `evokerDeathKillsVexes`：唤魔者死亡后，其召唤的恼鬼立即死亡（而非等到生命倒计时结束）
- 机制：恼鬼由 `Evoker` 召唤时 `setOwner` + `setLimitedLife`（20×(30~120) tick）；注入 `Vex.tick` HEAD，owner（唤魔者）已死时 `discard()`

### 锹铲雪层

- `shovelSnowLayer`：手持锹右键雪层方块可以铲掉一层雪（层数 > 1 减一层，=1 移除方块）
- `shovelSnowLayerDropSnowball`：锹铲雪时每铲掉一层额外掉落一个雪球（依赖 `shovelSnowLayer`，未开启会有橙色警告提示）
- 机制：`UseBlockCallback` 事件（Fabric，项目 handler 同模式），规则开启 + 手持锹 + 目标是 `Blocks.SNOW` 时执行并返回 SUCCESS；掉落用 `Block.popResource`

### 蝌蚪喂染料定色

- `tadpoleDyeColor`：手持染料右键蝌蚪会标记它长大后青蛙的颜色；仅接受橙/白/绿三种染料（对应温带/暖/冷三种青蛙变体），每只蝌蚪只能喂一次；喂染料不加快生长，粒子用 `ServerLevel.sendParticles` 发送**该染料颜色**的 DustParticleOptions（数量 10）
- 机制：注入 `Tadpole`——`mobInteract` HEAD 拦截染料并记录颜色（NBT 持久化），`ageUp` 的 `convertTo` 用 `@ModifyArg` 包裹 AfterConversion lambda 设置 `FrogVariant`

### 抢夺史莱姆分裂

- `lootingSlimeSplit`：用附有抢夺的武器击杀大史莱姆/岩浆怪时，分裂成更多小史莱姆，每 1 级抢夺多分裂 1 个
- 机制：原版 `Slime.remove` 分裂数 `k = 2 + random.nextInt(3)`；注入 `Slime.remove`，`@Redirect` 拦截 `random.nextInt(3)` 返回 `nextInt(3) + lootingLevel`；击杀者 `getKillCredit()` 主手武器 Looting 等级；岩浆怪继承 Slime 天然适用

### 骨粉产瓜

- `bonemealGourdFruit`：对成熟的西瓜/南瓜瓜苗（AGE 7）使用骨粉时按概率在相邻格结瓜——原版骨粉对成熟瓜苗无效（只能靠随机刻约 1/26 概率/tick 结瓜）
- 概率选项：0（禁用）、0.1、0.15、0.2、0.25、0.5、0.75、1（必定产瓜）；概率判定通过必结，四周全被占不消耗骨粉，未通过消耗 1 骨粉
- `dispenserGourdFruit`：发射器正对成熟瓜苗喷射骨粉，复用 `bonemealGourdFruit` 的概率；需先开启 `bonemealGourdFruit` 才生效，目标不匹配时回退原版弹出骨粉

### 合成配方

- 马鞍：3 皮革 + 1 铁锭（无序）
- 命名牌：1 铁粒 + 1 纸（2×2 对角）
- 钟：3 金锭 + 2 木棍 + 3 平滑石台阶
- 线：1 任意颜色羊毛 = 4 线（无序）
- 蜘蛛网：9 线（3×3）→ 1 蜘蛛网，基岩版旧版'蜘蛛网拆线'配方的反向
- 马铠：铁/金/钻石各一条，1.6 时代图案（6 锭/宝石 + 1 任意羊毛，`_ _ X / X W X / X X X`）；皮革马铠原版已有配方不重复
- 蓝冰：8 冰 + 1 蓝色染料（染色图案 `III / IDI / III`）→ 1 蓝冰，原版需 9 浮冰（=81 冰）合成
- 骨块：3 骨头（无序）→ 1 骨块，原版需 9 骨粉（先烧 3 骨头）合成
- 箱子：2 任意原木（无序）→ 1 箱子，原版需 8 木板合成
- 发射器：1 投掷器 + 1 弓（无序）→ 1 发射器，原版需 7 圆石 + 1 弓 + 1 红石粉
- 面包：3 小麦任意排列 → 1 面包（原版需横排）
- 纸：3 甘蔗任意排列 → 3 纸（原版需竖排）
- 潜影盒：1 箱子 + 2 潜影壳任意排列 → 1 潜影盒（原版需竖排）
- 石英块：1 石英块（无序）= 4 下界石英，原版 2×2 合成石英块的逆向

配方通过 Fabric Datagen 生成，且带条件注册：**规则关闭时配方完全不存在**（REI/JEI、配方书、合成台全部不可见），开启时自动加载并同步客户端。

### 高炉烧沙成玻璃

- `blastFurnaceGlass`：高炉可以将沙子和红沙烧炼为玻璃（原版沙子/红沙只能由熔炉烧成玻璃）
- 实现方式：添加带条件的高炉烧炼配方（`blasting`，100 tick，经验 0.1，与原版熔炉烧沙一致），无需 mixin
- 规则关闭时高炉烧沙配方不存在，高炉恢复原版行为（不能烧沙）

### 高炉烧制带釉陶瓦

- `blastFurnaceGlazedTerracotta`：高炉可以将全部 16 种染色陶瓦烧炼为对应颜色的带釉陶瓦（原版染色陶瓦只能由熔炉烧成带釉陶瓦）
- 实现方式：16 条带条件的高炉烧炼配方（`blasting`，100 tick，经验 0.1，与原版熔炉烧陶瓦一致），每条对应 `X_TERRACOTTA → X_GLAZED_TERRACOTTA`
- 规则关闭时这些配方不存在，高炉恢复原版行为（不能烧陶瓦）

### 其他高炉烧炼

- `blastFurnaceNetherBrick`：高炉可以将下界岩烧炼为下界砖（原版下界岩只能由熔炉烧成下界砖）
- `blastFurnaceSmoothQuartz`：高炉可以将石英块烧炼为平滑石英块（原版石英块只能由熔炉烧成平滑石英块）
- `blastFurnaceStone`：高炉可以将圆石烧炼为石头（原版圆石只能由熔炉烧成石头）
- `blastFurnaceSmoothStone`：高炉可以将石头烧炼为平滑石头（原版石头只能由熔炉烧成平滑石头）
- 实现方式：四条带条件的高炉烧炼配方（`blasting`，100 tick，经验 0.1，与原版熔炉对应配方一致），无需 mixin；规则关闭时这些配方不存在，高炉恢复原版行为

### 烟熏炉烧制绿色染料

- `smokerGreenDye`：烟熏炉可以将仙人掌烟熏为绿色染料（原版仙人掌只能由熔炉烧成绿色染料）
- 实现方式：带条件的 smoking 配方（`smoking`，100 tick，经验 1.0，与原版熔炉一致），配方 ID 用自定义 `green_dye_from_smoking`（避免与原版熔炉 `green_dye_from_smelting` 冲突），无需 mixin

## 环境要求

- Minecraft 1.21.4
- Fabric Loader ≥ 0.19.3
- Fabric API 0.119.4+
- Carpet 1.4.161+
- Java 21+

## 构建与开发

```bash
./gradlew runDatagen   # 生成资源（配方、翻译）到 src/main/generated/
./gradlew build         # 编译打包
./gradlew runClient     # 启动客户端
./gradlew runServer     # 启动服务端（首次需在 run/ 下创建 eula.txt）
```

> 修改了 `src/main/java/com/namelessgod2008/datagen/` 下的任何 Provider 后，必须先 `runDatagen` 再 `build`。

## 包结构

```
com.namelessgod2008
├── CarpetTNGAddtion.java          # 主类（ModInitializer）
├── CarpetTNGExtension.java        # Carpet 扩展（规则注册 + 翻译）
├── CarpetTNGAddtionDataGenerator  # Datagen 入口
├── setting/                       # @Rule 定义 + @RecipeRule 注解 + 配方条件/规则注册表
├── feature/                       # 每功能子包：交互逻辑 + mixin + 独立 mixins.json
│   ├── cauldron/  dispenser/  recipe/  apple/  amethyst/  suspicious/  trialspawner/  potion/
│   ├── wart/  gourd/  obsidian/  basalt/  piglin/  enderdragon/  anvil/  blazestick/
├── compact/                       # 其他 mod 兼容修复（REI 高炉配方显示 bug）
├── datagen/                       # 配方、翻译生成器
└── modmenu/                       # ModMenu 集成
```

## License

[MIT](LICENSE)
