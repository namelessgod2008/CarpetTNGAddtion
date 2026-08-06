package com.namelessgod2008.setting;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记某个 {@link net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition} 配方规则。
 * <p>
 * 被此注解标记的 Carpet 规则（{@code public static boolean} 字段）会被
 * {@link RecipeRuleRegistry} 反射收集：规则开关时自动触发数据包 reload 让带条件配方
 * (de)注册。新增配方规则只需在字段上加本注解 + 在 RecipeGenerator 生成配方，
 * 无需再手动登记到 RuleEnabledCondition 的 switch 或主类规则列表。
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RecipeRule {
}
