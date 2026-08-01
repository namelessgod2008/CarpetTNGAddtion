# CarpetTNGAddtion — Carpet 附属模组

非代码的文档，会话，思维链全部用中文输出

Minecraft 1.21.4 (Fabric) 的 Carpet 附属模组，将基岩版/后续版本机制移植到当前版本（"porting" 类规则）。

---

# 行为准则（通用）

减少常见 LLM 编码错误的行为准则。可根据项目特定需求进行合并。

**权衡取舍：** 这些准则偏向于谨慎而非速度。对于琐碎任务，自行判断即可。

## 1. 先思考再编码

**不要臆测。不要隐藏困惑。主动暴露权衡。**

在实现之前：
- 明确陈述你的假设。如果不确定，就问。
- 如果存在多种解释，把它们都列出来——不要默默自行选择。
- 如果存在更简单的方案，直接说出来。必要时据理力争。
- 如果有不清楚的地方，停下来。指出哪里令人困惑。提问。

## 2. 简单优先

**以解决问题所需的最少代码为准。不写投机性代码。**

- 不添加超出需求的功能。
- 不为一次性代码做抽象。
- 不添加未经要求的"灵活性"或"可配置性"。
- 不为不可能出现的场景写错误处理。
- 如果写了 200 行而 50 行就能解决，重写它。

问问自己："资深工程师会觉得这过度复杂吗？"如果是，就简化。

## 3. 外科手术式修改

**只动必须动的。只清理自己造成的混乱。**

编辑现有代码时：
- 不要"改进"相邻的代码、注释或格式。
- 不要重构没有问题的东西。
- 匹配现有风格，即使你会用不同的方式写。
- 如果发现无关的死代码，提出来——但不要删除它。

当你的修改产生孤儿代码时：
- 删除因你的改动而不再使用的导入/变量/函数。
- 未经要求，不要删除先前存在的死代码。

检验标准：每一行改动都应能直接追溯到用户的需求。

## 4. 目标驱动执行

**定义成功标准。循环直到验证通过。**

将任务转化为可验证的目标：
- "添加验证" → "为无效输入编写测试，然后让它们通过"
- "修复这个 bug" → "编写能复现它的测试，然后让它通过"
- "重构 X" → "确保重构前后测试都通过"

对于多步骤任务，陈述简要计划：
```
1. [步骤] → 验证：[检查项]
2. [步骤] → 验证：[检查项]
3. [步骤] → 验证：[检查项]
```

明确成功标准可以让你独立循环推进。模糊的标准（"把它搞定"）则需要不断澄清。

---

**这些准则有效体现在：** diff 中不必要的改动更少，因过度复杂化而重写的次数更少，而且澄清问题出现在实现之前，而不是犯错之后。

---

## 构建与运行

```bash
./gradlew runDatagen   # 生成资源（配方、翻译）到 src/main/generated/
./gradlew build         # 编译打包
./gradlew runClient     # 启动客户端
./gradlew runServer     # 启动服务端（首次需在 run/ 下创建 eula.txt）
```

**注意**：
- `processResources` 会从 `src/main/generated/` 合并资源并排除 `.cache`
- datagen 模式下 `onInitialize` 会跳过 Carpet 扩展注册（避免翻译缺失崩溃）
- 修改了 `datagen/` 下任何 Provider 后必须先 `runDatagen` 再 `build`

## 规则（统一在 /carpet 指令下）

所有规则注册到 `CarpetServer.settingsManager`（不是独立 SettingsManager）。所有规则都带 `TNG` 分类；合成类规则另带 `survival`，炼药锅制箭另带 `porting`：

| 规则 | 功能 | 分类 | 开关 |
|------|------|------|------|
| `bedrockCauldronTippedArrows` | 基岩版炼药锅制箭 | porting, TNG | `/carpet bedrockCauldronTippedArrows true/false` |
| `craftableSaddle` | 马鞍合成（3皮+1铁锭，无序） | survival, TNG | `/carpet craftableSaddle true/false` |
| `craftableNameTag` | 命名牌合成（铁粒+纸，2×2对角） | survival, TNG | `/carpet craftableNameTag true/false` |
| `craftableBell` | 钟合成（3金锭+2木棍+3平滑石头台阶） | survival, TNG | `/carpet craftableBell true/false` |

## 翻译机制

Carpet 附属的翻译**必须**通过 `CarpetExtension.canHasTranslations(String lang)` 返回 Map 提供（`CarpetTNGExtension.java`），**不会**从 assets 的 lang json 自动读取。同时用 datagen（`TranslationGenerator`）生成 lang json 供 Fabric 资源系统使用。翻译 key 格式：`carpet.rule.<ruleName>.name` / `.desc`。

## 包结构

- `com.namelessgod2008` — 主类 + CarpetExtension + DataGenerator 入口
- `setting/` — `CarpetTNGSetting.java`：@Rule 字段定义
- `feature/` — 功能实现：`CauldronArrowHandler`（交互逻辑）、`CauldronSavedData`（存档持久化）
- `datagen/` — `RecipeGenerator`、`TranslationGenerator`
- `mixin/` — `CraftingMenuMixin`（合成台规则开关）
- `modmenu/` — ModMenu 集成

## 关键实现细节

### 炼药锅制箭（CauldronArrowHandler）
- 药水状态记录在 `POTION_CAULDRONS` Map（内存）：
  - `CauldronData(PotionContents potion, BottleType bottleType, int sixths)` — sixths 为 0-6 水位
  - 一瓶药水 = 2/6，满锅 = 6/6
- 方块状态：有药水 → `WATER_CAULDRON`（level 1-3），无药水 → 空 `CAULDRON`
- 不相容药水（效果不同）倒入 → 清空炼药锅 + 消耗手持药水
- 药水效果匹配：`effectsMatch()` 比较 potion 类型 + 自定义效果类型/等级
- 蘸箭消耗表：满锅 1-16→1/6, 17-32→1/3, 33-48→1/2, 49-64→清空；非满锅按比例
- 持久化：`CauldronSavedData`（每维度 SavedData，NBT 存储 PotionContents Codec），世界加载时恢复
- 粒子：`DustParticleOptions(color, size)` 生成于水面高度 `waterSurfaceY(sixths)` 上方 +0.05，操作触发 8 个 + 每 10 tick 循环 2 个
- 药水炼药锅上禁止原版交互（空桶等）：`onUseBlock` 兜底返回 SUCCESS

### 合成配方
- `RecipeGenerator`：1.21.4 中 `FabricRecipeProvider.createRecipeProvider(registries, output)` 返回匿名 `RecipeProvider`，在 `buildRecipes()` 内用 `this.registries.lookupOrThrow(Registries.ITEM)` 获取物品注册表
- `CraftingMenuMixin`：注入 `slotChangedCraftingGrid`（静态方法，参数 `AbstractContainerMenu, ServerLevel, Player, CraftingContainer, ResultContainer, RecipeHolder<?>, CallbackInfo`）TAIL，规则关闭时清空结果槽

### 依赖
- Carpet `1.4.161`（Modrinth Maven，`maven.modrinth:carpet`，版本号不带 `+` 后缀）
- Fabric API `0.119.4+1.21.4`、ModMenu `13.0.4`
- 1.21.4 命名：`CustomPacketPayload`（非 CustomPayload）、`DustParticleOptions(int color, float size)`
