package com.namelessgod2008.setting;

import carpet.CarpetServer;
import carpet.api.settings.CarpetRule;
import carpet.api.settings.Validator;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

/**
 * ORG 附属兼容警告：启用与 ORG 功能类似/相反的规则时，若检测到 ORG 附属已加载，
 * 向执行命令的玩家发送橙色警告，并告知 ORG 对应功能是否启用。只发警告不拦截值。
 * <p>
 * 覆盖：removeAnvilTooExpensive/cheapAnvilRename（ORG 类似功能 setAnvilExperienceConsumptionLimit）、
 * blazeStickFurnaceXp（ORG 相反功能 disableFurnaceDropExperience）。
 */
public class OrgCompatWarningValidator extends Validator<Boolean> {

    private static final String ORG_MOD_ID = "carpet-org-addition";

    @Override
    public Boolean validate(CommandSourceStack source, CarpetRule<Boolean> currentRule, Boolean newValue, String userInput) {
        if (newValue == null || !newValue || source == null || !(source.getEntity() instanceof Player player)) {
            return newValue;
        }
        if (!FabricLoader.getInstance().isModLoaded(ORG_MOD_ID)) {
            return newValue;
        }
        String warning = getOrgWarning(currentRule.name());
        if (warning != null) {
            player.displayClientMessage(Component.literal("警告：" + warning).withStyle(ChatFormatting.GOLD), false);
        }
        return newValue;
    }

    private static String getOrgWarning(String ruleName) {
        return switch (ruleName) {
            case "removeAnvilTooExpensive", "cheapAnvilRename" -> {
                boolean orgEnabled = isOrgRuleEnabled("setAnvilExperienceConsumptionLimit");
                yield "检测到 ORG 附属有类似功能（setAnvilExperienceConsumptionLimit，铁砧单次操作经验上限），该功能当前" + (orgEnabled ? "已启用" : "未启用");
            }
            case "blazeStickFurnaceXp" -> {
                boolean orgEnabled = isOrgRuleEnabled("disableFurnaceDropExperience");
                yield "检测到 ORG 附属有相反功能（disableFurnaceDropExperience，阻止熔炉掉落经验），该功能当前" + (orgEnabled ? "已启用" : "未启用");
            }
            default -> null;
        };
    }

    /** 读取 ORG 规则是否启用（布尔 true / 数值非 0 视为启用；读不到视为未启用）。 */
    private static boolean isOrgRuleEnabled(String ruleName) {
        try {
            for (CarpetRule<?> rule : CarpetServer.settingsManager.getCarpetRules()) {
                if (rule.name().equals(ruleName)) {
                    Object value = rule.value();
                    if (value instanceof Boolean b) return b;
                    if (value instanceof Number n) return n.longValue() != 0;
                    return value != null;
                }
            }
        } catch (Exception ignored) {
        }
        return false;
    }
}
