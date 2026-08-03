package com.namelessgod2008.setting;

import carpet.api.settings.CarpetRule;
import carpet.api.settings.Validator;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

/**
 * 依赖规则启用警告：玩家启用某下游规则但未启用其前置规则时，
 * 向执行命令的玩家发送橙色聊天栏警告。只发警告不拦截值（返回原值）。
 * <p>
 * 通过 {@code currentRule.name()} 分发各依赖规则的前置检查；
 * config 加载等非玩家来源（source.getEntity() 非玩家）不触发，避免启动误报。
 */
public class DependencyWarningValidator extends Validator<Boolean> {

    @Override
    public Boolean validate(CommandSourceStack source, CarpetRule<Boolean> currentRule, Boolean newValue, String userInput) {
        if (newValue == null || !newValue || source == null || !(source.getEntity() instanceof Player player)) {
            return newValue;
        }
        String warning = getMissingPrerequisite(currentRule.name());
        if (warning != null) {
            player.displayClientMessage(Component.literal("警告：" + warning).withStyle(ChatFormatting.GOLD), false);
        }
        return newValue;
    }

    /** 返回未启用前置规则的警告文案；前置已启用返回 null。 */
    private static String getMissingPrerequisite(String ruleName) {
        return switch (ruleName) {
            case "dispenserNetherWartGrowth" -> CarpetTNGSetting.blazePowderNetherWartGrowth ? null
                    : "发射器催熟地狱疣需要先启用烈焰粉催熟地狱疣规则（blazePowderNetherWartGrowth）才能生效";
            case "dispenserGourdFruit" -> CarpetTNGSetting.bonemealGourdFruit > 0.0 ? null
                    : "发射器骨粉催瓜产果需要先启用骨粉产瓜规则（bonemealGourdFruit）才能生效";
            case "blazeStickFurnaceXp" -> CarpetTNGSetting.blazeStickDebug ? null
                    : "烈焰棒掏炉渣需要先启用烈焰棒调试规则（blazeStickDebug）才能生效";
            case "blazeStickSmokerXp" -> CarpetTNGSetting.blazeStickDebug ? null
                    : "烈焰棒掏烟熏炉渣需要先启用烈焰棒调试规则（blazeStickDebug）才能生效";
            case "blazeStickBlastFurnaceXp" -> CarpetTNGSetting.blazeStickDebug ? null
                    : "烈焰棒掏高炉渣需要先启用烈焰棒调试规则（blazeStickDebug）才能生效";
            default -> null;
        };
    }
}
