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
| `dispenserPlanting` | 发射器直线喷射种子种田（小麦/甜菜/胡萝卜/马铃薯） | feature, TNG, dispenser |
| `dispenserPlantingGourds` | 发射器种植西瓜南瓜（种子→瓜藤） | feature, TNG, dispenser |
| `dispenserPlantingNetherWart` | 发射器种植地狱疣（命中灵魂沙种下） | feature, TNG, dispenser |
| `bonemealGourdFruit` | 骨粉产瓜（成熟瓜苗按概率结瓜，0 禁用~1 必结） | feature, TNG, survival |
| `dispenserGourdFruit` | 发射器骨粉催瓜产果（复用骨粉产瓜概率） | feature, TNG, dispenser |
| `reinforcedObsidian` | 坚固黑曜石（免疫凋零的方块破坏） | feature, TNG |
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

### 试炼刷怪笼冷却

- `shortenedTrialSpawnerCooldown`：试炼刷怪笼冷却从原版 30 分钟缩短为 5 分钟
- 关闭规则不影响已在进行中的冷却

### 灾厄药水酿造

- `brewableOminousPotion`：粗制药水 + 不死图腾 → 灾厄药水 I 级；+ 萤石粉继续酿造可升至 V 级
- 效果为不祥之兆，持续时间 1 小时 40 分钟，与原版一致

### 烈焰粉催熟地狱疣

- `blazePowderNetherWartGrowth`：手持烈焰粉右键地狱疣推进一个生长阶段（原版骨粉对地狱疣无效）
- `dispenserNetherWartGrowth`：发射器正对地狱疣喷射烈焰粉推进一个生长阶段，可与发射器种植联动做自动化农场；需先开启 `blazePowderNetherWartGrowth` 才生效

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

### 骨粉产瓜

- `bonemealGourdFruit`：对成熟的西瓜/南瓜瓜苗（AGE 7）使用骨粉时按概率在相邻格结瓜——原版骨粉对成熟瓜苗无效（只能靠随机刻约 1/26 概率/tick 结瓜）
- 概率选项：0（禁用）、0.1、0.15、0.2、0.25、0.5、0.75、1（必定产瓜）；概率判定通过必结，四周全被占不消耗骨粉，未通过消耗 1 骨粉
- `dispenserGourdFruit`：发射器正对成熟瓜苗喷射骨粉，复用 `bonemealGourdFruit` 的概率；需先开启 `bonemealGourdFruit` 才生效，目标不匹配时回退原版弹出骨粉

### 合成配方

- 马鞍：3 皮革 + 1 铁锭（无序）
- 命名牌：1 铁粒 + 1 纸（2×2 对角）
- 钟：3 金锭 + 2 木棍 + 3 平滑石台阶
- 线：1 任意颜色羊毛 = 4 线（无序）

配方通过 Fabric Datagen 生成，且带条件注册：**规则关闭时配方完全不存在**（REI/JEI、配方书、合成台全部不可见），开启时自动加载并同步客户端。

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
├── setting/                       # @Rule 定义 + 配方条件
├── feature/                       # 每功能子包：交互逻辑 + mixin + 独立 mixins.json
│   ├── cauldron/  dispenser/  recipe/  apple/  amethyst/  suspicious/  trialspawner/  potion/
├── datagen/                       # 配方、翻译生成器
└── modmenu/                       # ModMenu 集成
```

## License

[MIT](LICENSE)
