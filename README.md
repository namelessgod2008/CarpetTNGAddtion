# CarpetTNGAddtion

Minecraft 1.21.4 (Fabric) 的 [Carpet](https://github.com/gnembon/fabric-carpet) 附属模组，将基岩版与后续版本的游戏机制移植到当前版本（"porting" 类规则）。

## 功能

所有规则默认关闭，通过 `/carpet` 指令统一开关，分类为 `porting`：

| 规则 | 功能 | 开启指令 |
|------|------|----------|
| `bedrockCauldronTippedArrows` | 基岩版炼药锅制箭 | `/carpet bedrockCauldronTippedArrows true` |
| `craftableSaddle` | 马鞍合成（3 皮革 + 1 铁锭，无序） | `/carpet craftableSaddle true` |
| `craftableNameTag` | 命名牌合成（铁粒 + 纸，2×2 对角） | `/carpet craftableNameTag true` |
| `craftableBell` | 钟合成（3 金锭 + 2 木棍 + 3 平滑石头台阶） | `/carpet craftableBell true` |

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

### 合成配方

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
├── feature/                       # 炼药锅交互逻辑、存档持久化
├── datagen/                       # 配方、翻译生成器
├── mixin/                         # 合成台规则开关
└── modmenu/                       # ModMenu 集成
```

## License

[MIT](LICENSE)
