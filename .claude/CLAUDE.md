# CarpetTNGAddtion — Carpet 附属模组

非代码的文档，会话，思维链全部用中文输出

Minecraft 1.21.4 (Fabric) 的 Carpet 附属模组，将基岩版/后续版本机制移植到当前版本（"porting" 类规则）。

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

所有规则注册到 `CarpetServer.settingsManager`（不是独立 SettingsManager），分类为 `porting`：

| 规则 | 功能 | 开关 |
|------|------|------|
| `bedrockCauldronTippedArrows` | 基岩版炼药锅制箭 | `/carpet bedrockCauldronTippedArrows true/false` |
| `craftableSaddle` | 马鞍合成（3皮+1铁锭，无序） | `/carpet craftableSaddle true/false` |
| `craftableNameTag` | 命名牌合成（铁粒+纸，2×2对角） | `/carpet craftableNameTag true/false` |

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
