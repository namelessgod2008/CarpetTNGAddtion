package com.namelessgod2008.compact.mixin;

import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.plugin.common.displays.cooking.DefaultBlastingDisplay;
import me.shedaniel.rei.plugin.common.displays.cooking.DefaultCookingDisplay;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;

/**
 * 修复 REI 18.0.815 的 {@link DefaultBlastingDisplay#SERIALIZER} bug：
 * <p>
 * REI 源码里 {@code DefaultBlastingDisplay.SERIALIZER = serializer(DefaultSmokingDisplay::new)}
 * —— 泛型和构造器都错用了 {@code DefaultSmokingDisplay}。这导致 blasting 配方经服务端→
 * 客户端网络同步反序列化时被重建为 DefaultSmokingDisplay（category=烟熏炉），REI 里
 * 高炉配方（含本模组的高炉烧沙/带釉陶瓦）被错误显示成烟熏炉。
 * <p>
 * 正确值应为 {@code serializer(DefaultBlastingDisplay::new)}。由于字段是 {@code public static final}
 * 且 {@code serializer} 是 protected（Mixin 编译期无法引用其嵌套 {@code Constructor} 参数类型），
 * 在 {@code <clinit>} TAIL 用反射 + Proxy 构造正确序列化器后重写字段：
 * <ul>
 *   <li>用 {@link Proxy} 实现 {@code DefaultCookingDisplay.Constructor} 接口（其 {@code create}
 *       签名对所有 cooking display 一致），{@code create} 里 new {@code DefaultBlastingDisplay}；</li>
 *   <li>反射调用 {@code serializer(Constructor)} 得到正确的 {@code DisplaySerializer}；</li>
 *   <li>反射替换 {@code SERIALIZER} final 字段（运行时对象引用可替换，非编译期常量内联）。</li>
 * </ul>
 * <p>
 * {@code @Pseudo}：REI 是 modCompileOnly（运行时可能不存在），加上后 mixin 在 target 类
 * 缺失时静默跳过、不报错不注入，避免 REI 未安装时加载失败。
 */
@Pseudo
@Mixin(DefaultBlastingDisplay.class)
public abstract class DefaultBlastingDisplayMixin {

    private static final Logger LOGGER = LoggerFactory.getLogger("carpettngaddtion-compact");

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void carpettng$fixBlastingSerializer(CallbackInfo ci) {
        try {
            // 1. 反射获取 DefaultCookingDisplay.Constructor 嵌套接口
            Class<?> constructorIface = Class.forName(
                    "me.shedaniel.rei.plugin.common.displays.cooking.DefaultCookingDisplay$Constructor");

            // 2. 用 Proxy 实现该接口：create() 里构造 DefaultBlastingDisplay
            InvocationHandler handler = (proxy, method, args) -> {
                if (method.getName().equals("create")) {
                    @SuppressWarnings("unchecked")
                    List<EntryIngredient> inputs = (List<EntryIngredient>) args[0];
                    @SuppressWarnings("unchecked")
                    List<EntryIngredient> outputs = (List<EntryIngredient>) args[1];
                    @SuppressWarnings("unchecked")
                    Optional<ResourceLocation> location = (Optional<ResourceLocation>) args[2];
                    float xp = ((Number) args[3]).floatValue();
                    double cookTime = ((Number) args[4]).doubleValue();
                    return new DefaultBlastingDisplay(inputs, outputs, location, xp, cookTime);
                }
                return null;
            };
            Object constructor = Proxy.newProxyInstance(
                    DefaultBlastingDisplay.class.getClassLoader(),
                    new Class<?>[]{constructorIface},
                    handler);

            // 3. 反射调用 DefaultCookingDisplay.serializer(Constructor)
            Method serializer = DefaultCookingDisplay.class.getDeclaredMethod("serializer", constructorIface);
            serializer.setAccessible(true);
            @SuppressWarnings("unchecked")
            DisplaySerializer<DefaultBlastingDisplay> fixed =
                    (DisplaySerializer<DefaultBlastingDisplay>) serializer.invoke(null, constructor);

            // 4. 反射替换 SERIALIZER final 字段
            Field field = DefaultBlastingDisplay.class.getDeclaredField("SERIALIZER");
            field.setAccessible(true);
            field.set(null, fixed);

            LOGGER.info("REI DefaultBlastingDisplay.SERIALIZER patched: blasting recipes now show in the blast furnace category");
        } catch (Throwable t) {
            LOGGER.warn("Failed to patch REI DefaultBlastingDisplay.SERIALIZER; blasting recipes may show as smoker", t);
        }
    }
}
