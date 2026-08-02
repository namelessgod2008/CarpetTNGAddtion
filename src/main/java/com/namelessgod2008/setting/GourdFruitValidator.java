package com.namelessgod2008.setting;

import carpet.api.settings.CarpetRule;
import carpet.api.settings.Validator;
import net.minecraft.commands.CommandSourceStack;

import java.util.Set;

/**
 * 骨粉产瓜概率校验器：只允许 {0, 0.1, 0.15, 0.2, 0.25, 0.5, 0.75, 1} 八个选项。
 * <p>
 * 必须配 {@code @Rule(strict = false)}：{@code Rule.strict()} 默认值为 true，
 * 一旦写 options 就会附加 StrictValidator，按字符串比对 {@code toRuleString(value)}
 * ——double 默认值 0.0 序列化为 "0.0"，与选项 "0" 不匹配，服务端启动时
 * resetToDefault 抛 InvalidRuleValueException 崩溃。strict=false 后 options 仅用于
 * 命令补全/分类页显示，由本校验器在数值层面强制选项。
 */
public class GourdFruitValidator extends Validator<Double> {

    private static final Set<Double> OPTIONS = Set.of(0.0, 0.1, 0.15, 0.2, 0.25, 0.5, 0.75, 1.0);

    @Override
    public Double validate(CommandSourceStack source, CarpetRule<Double> currentRule, Double newValue, String userInput) {
        if (newValue != null && OPTIONS.contains(newValue)) {
            return newValue;
        }
        return null; // 拒绝：返回 null 保持原值
    }

    @Override
    public String description() {
        return "Only 0, 0.1, 0.15, 0.2, 0.25, 0.5, 0.75, 1 are allowed";
    }
}
