package com.namelessgod2008.setting;

import carpet.api.settings.CarpetRule;
import carpet.api.settings.Validator;
import net.minecraft.commands.CommandSourceStack;

import java.util.Set;

/**
 * 猪灵交易冷却校验器：只允许 {119, 89, 59, 29, 0} 五个挡位（tick）。
 * 配 {@code @Rule(strict = false)}，与 GourdFruitValidator 同理：
 * strict 默认 true 会按字符串比对 options，数值规则统一用 Validator 限定。
 */
public class PiglinBarterValidator extends Validator<Integer> {

    private static final Set<Integer> OPTIONS = Set.of(119, 89, 59, 29, 0);

    @Override
    public Integer validate(CommandSourceStack source, CarpetRule<Integer> currentRule, Integer newValue, String userInput) {
        if (newValue != null && OPTIONS.contains(newValue)) {
            return newValue;
        }
        return null; // 拒绝：返回 null 保持原值
    }

    @Override
    public String description() {
        return "Only 119, 89, 59, 29, 0 are allowed";
    }
}
