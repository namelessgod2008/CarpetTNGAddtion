package com.namelessgod2008.setting;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 配方规则注册表：通过 {@link RecipeRule} 注解反射收集所有配方规则。
 * <p>
 * 替代旧的"双处手动登记"（RuleEnabledCondition 硬编码 switch + 主类 RECIPE_RULES Set）：
 * 新增配方规则只需在 {@link CarpetTNGSetting} 字段上加 {@link RecipeRule} 注解，
 * 本类会自动收集规则名（供 observer 判断是否 reload）和读取规则值（供配方条件判断）。
 */
public final class RecipeRuleRegistry {

    /** 所有带 @RecipeRule 注解的规则名（字段名即规则名）。 */
    private static final Set<String> RULE_NAMES = collectRuleNames();

    /** 规则名 → 字段 的缓存，避免每次条件测试都反射查找。 */
    private static final Map<String, Field> FIELD_CACHE = new HashMap<>();

    private RecipeRuleRegistry() {}

    private static Set<String> collectRuleNames() {
        Set<String> names = new HashSet<>();
        for (Field field : CarpetTNGSetting.class.getFields()) {
            if (field.isAnnotationPresent(RecipeRule.class)) {
                names.add(field.getName());
            }
        }
        return names;
    }

    /** 指定规则名是否为配方规则。 */
    public static boolean isRecipeRule(String ruleName) {
        return RULE_NAMES.contains(ruleName);
    }

    /** 读取配方规则的当前布尔值（规则名即字段名）。 */
    public static boolean isRuleEnabled(String ruleName) {
        Field field = FIELD_CACHE.computeIfAbsent(ruleName, name -> {
            try {
                return CarpetTNGSetting.class.getField(name);
            } catch (NoSuchFieldException e) {
                return null;
            }
        });
        if (field == null) return false;
        try {
            return field.getBoolean(null);
        } catch (IllegalAccessException e) {
            return false;
        }
    }
}
