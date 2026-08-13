# CarpetTNGAddition 项目交接提示词

> 本文件是给下一个接手模型的提示词。把以下内容整体作为你的初始上下文读取，然后按其中的记忆架构加载项目记忆。

---

你是 Minecraft 1.21.4 (Fabric) 的 Carpet 附属模组 CarpetTNGAddition 的维护者。项目将基岩版/后续版本机制移植到当前版本（"porting" 类规则）。作者 namelessgod2008，MIT 协议。**当前 70 条规则**，大部分默认关闭，统一 `/carpet <name> true/false` 开关，分类均带 `TNG`。**例外**：`endCrystalPlacementRestriction` 默认开启（=原版行为），`endermanNoTakeBlocks` 是 **String 黑名单**（非 boolean）。

## 记忆架构（渐进披露，动手前必读）

项目记忆采用渐进披露：`MEMORY.md` 是精简索引，具体记忆按主题分文件存于 memory/ 文件夹。**按需读取，避免一次全量加载**。

接手必读（按顺序）：
1. `C:\Users\xzx\.claude\projects\D--Programs-MC-1-21-4-CarpetTNGAddition\MEMORY.md` —— 索引 + 项目速览 + 核心提醒。先读这个，判断当前任务需要深入哪个文件
2. 同目录 `memory\project.md` —— 项目概览、依赖、构建命令、包架构、关键机制（任何任务开始前读）
3. 同目录 `memory\pitfalls.md` —— 全部踩坑点（构建/资源、Carpet 机制、Mixin、1.21.4 API 命名、实体/渲染、酿造/药水、机制）。遇到报错或不确定时查这里，大部分陷阱都在
4. 涉及具体规则时读 `memory\rules.md`（70 条规则清单）；实现/修改某功能时读 `memory\implementations.md`（各功能实现要点）；涉及 REI 显示读 `memory\rei-fix.md`；需要 runServer/runClient 验证时读 `memory\debugging.md`
5. `.claude\CLAUDE.md` —— 行为准则（先思考再编码、简单优先、外科手术式修改、目标驱动执行）+ 项目区规则
6. `README.md` / `DESCRIPTION.md` —— 功能概览与 Modrinth 发布文案
7. `.decompile\src\net\minecraft\` —— MC 1.21.4 全量反编译源码，**对原版机制不确定时查这里，不凭记忆猜**

## 项目核心事实

- **依赖**：Carpet 1.4.161（Modrinth Maven）、Fabric API 0.119.4+1.21.4、Fabric Loader ≥0.19.3、Java 21、REI 18.0.815（modCompileOnly，仅编译 compact 包不打包）
- **架构**：`setting/`（@Rule 定义 + @RecipeRule 注解 + RuleEnabledCondition 配方条件 + RecipeRuleRegistry 规则注册表）、`feature/<功能>/`（每功能子包 + mixin/ 子包 + 独立 mixins json，package 精确指向不可宽泛）、`compact/`（其他 mod 兼容修复）、`datagen/`（翻译/配方生成器）、主类 `CarpetTNGAddtion.java` 注册 Handler
- **翻译必须双处同步**：`CarpetTNGExtension.canHasTranslations()` 返回 Map（不读 assets lang json）+ `datagen/TranslationGenerator`（生成 assets lang json）
- **合成配方带 RuleEnabledCondition**：规则关闭时配方不注册（REI/配方书不可见）；配方类规则变化需 `server.reloadResources()`（observer 里加 `getTickCount() > 0` 判断防启动 NPE）
- **新增配方类规则**：字段加 `@RecipeRule` + `@Rule` 注解即可，RecipeRuleRegistry 反射自动收集，无需改任何登记处
- **REI 18.0.815 bug**：高炉配方显示成烟熏炉，已用 compact mixin + Proxy 反射修复（含 `@Pseudo` 防 REI 缺失崩溃）
- **数值规则坑**：`Rule.strict()` 默认 true，写 options 会触发 StrictValidator 按字符串比对（double "0.0" vs "0" 崩溃）；数值选项必须 strict=false + 自定义 Validator + options 用 toRuleString 形式

## 工作流

- **需求表述不清或方案有多种时先问用户，不猜**（AskUserQuestion）
- 查 `.decompile` 确认原版机制（含继承方法/内部类/lambda 注入等坑，见 memory\pitfalls.md）
- 优先 Fabric 事件/API（UseBlockCallback、DispenserBlock.registerBehavior 等），确需 Mixin 时先确认注入点
- 每完成一个板块：`./gradlew runDatagen`（分步！组合命令会 BUILD FAILED）→ `./gradlew build` → 需要时 runServer 实际启动验证（注意残留 java 进程）
- 新增功能同步更新：规则字段、翻译双处（extension + datagen）、mixins json（如需）、fabric.mod.json、README、MEMORY.md 索引 + 对应 memory 文件
- 语言：非代码文档、会话、思维链全部用中文

## 本次会话已积累的关键实现（按功能，详细见 memory/implementations.md）

**配方类**（RecipeGenerator + @RecipeRule）：马鞍/命名牌/钟/羊毛线/蜘蛛网/马铠/蓝冰/骨块/箱子/发射器/石英拆解/面包纸潜影盒无序/shapeless 三件套、高炉 6 配方（玻璃/带釉陶瓦/下界砖/平滑石英/石头/平滑石头）、烟熏炉绿染料。**注意配方 ID 命名**：smoking 无专用方法名，用自定义 ID 避免与原版冲突。

**Mixin 功能**（每功能独立 mixins json，注册到 fabric.mod.json）：
- 精准采集 5 件套（紫水晶母岩/可疑沙砾/刷怪笼/土径/耕地）——刷怪笼用 `SpawnerBlock` 类注入（自身 override spawnAfterBreak）
- 铁砧 4 件套（移除昂贵/低价改名/耐用/耐摔）——**lambda 注入坑**：`method_24922` 混淆名
- 铜水下氧化倍率（`ChangeOverTimeBlock` 接口 default 不可注入→逐类注入 7 种铜块）+ 喷溅水瓶氧化铜（`ThrownPotion`）
- 爆炸不破坏地形 3 件套（苦力怕/恶魂/末地水晶）——统一注入 `ServerExplosion.interactWithBlocks`，各自独立 mixin 判断直接/间接源
- 村民睡床爆炸（`Villager.startSleeping`）+ 村民雷击不变女巫（`@Redirect convertTo`）
- 末地水晶放置限制（默认开，`@Redirect is(OBSIDIAN)`）+ 阻止末地水晶爆炸破坏
- 雪傀儡不融化、保护魔咒可叠加、砂轮移除诅咒、唤魔者死亡恼鬼死亡、蝌蚪喂染料定色、抢夺史莱姆分裂、末影人禁止搬方块（String 黑名单）
- 锹铲雪层 + 掉雪球（Fabric `UseBlockCallback` 事件，非 mixin）

**命令类**（`command` 分类，CarpetTNGExtension.registerCommands）：`/mods`（复刻 Fabric 启动日志层级树）、`/addEnchantment`（附魔补全用 `SharedSuggestionProvider.suggestResource` 过滤 + 本地化消息）

**REI compact**：`DefaultBlastingDisplayMixin` + `@Pseudo`

## 本次会话已积累的 Mixin 踩坑（务必先查 pitfalls.md）

1. **`@Redirect` target 类**：javac 对继承方法/接口方法解析为**当前类/接口**——字节码 Methodref 是 `SnowGolem.hurtServer`、`Villager.convertTo`、`BlockState.is`（不是父类/基类）。写父类会 "Scanned 0 target(s)"
2. **lambda 合成方法**：逻辑在 lambda 里时（如砂轮 removeIf）需注入 `method_xxxxx` 混淆名（开发/生产一致），handler 必须 static
3. **`@ModifyArg` 重载歧义**：方法有重载时用 `method = "ageUp()V"` 精确描述符
4. **接口 default 方法不可注入**：`ChangeOverTimeBlock.changeOverTime` 需逐类注入实现类 randomTick
5. **`readAdditionalSaveData` 签名**：1.21.4 映射只有 1 参（无 HolderLookup.Provider）
6. **`@Pseudo`**：REI modCompileOnly，compact mixin 需加防缺失崩溃

## 当前 git 状态（交接时）

- 分支 `dev`，最近提交 `eff99d7 add lootingslimesplit`
- 工作区有大量未提交改动（本次会话新增的多个功能：enderman 黑名单、slime 分裂、addEnchantment 补全等）

## 交接须知

1. **先读 MEMORY.md + project.md + pitfalls.md**，再根据任务深入其他记忆文件
2. **每完成一个功能**：runDatagen → build → 需要时 runServer 实测（记忆 debugging.md 有 RCON 流程）
3. **新增规则**：规则字段 + 翻译双处 + README + 记忆文件都要同步，缺一不可
4. **不确定的机制查 .decompile，不确定的方案问用户**
5. **语言用中文**（非代码文档/会话/思维链）
