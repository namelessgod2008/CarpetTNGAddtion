package com.namelessgod2008.setting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.namelessgod2008.CarpetTNGAddtion;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;

/**
 * Fabric resource condition that gates a recipe on a Carpet rule being enabled.
 * <p>
 * Recipe JSON:
 * <pre>
 * "fabric:conditions": [
 *   { "condition": "carpettngaddtion:rule_enabled", "rule": "craftableSaddle" }
 * ]
 * </pre>
 * When the rule is off, the recipe is not registered, so it disappears from
 * the recipe book, REI/JEI, and crafting — not just the crafting result.
 */
public record RuleEnabledCondition(String rule) implements ResourceCondition {

    public static final MapCodec<RuleEnabledCondition> CODEC = RecordCodecBuilder.mapCodec(i -> i
            .group(Codec.STRING.fieldOf("rule").forGetter(RuleEnabledCondition::rule))
            .apply(i, RuleEnabledCondition::new));

    public static final ResourceConditionType<RuleEnabledCondition> TYPE = ResourceConditionType.create(
            ResourceLocation.fromNamespaceAndPath(CarpetTNGAddtion.MOD_ID, "rule_enabled"), CODEC);

    public static void register() {
        ResourceConditions.register(TYPE);
    }

    @Override
    public ResourceConditionType<?> getType() {
        return TYPE;
    }

    @Override
    public boolean test(RegistryOps.RegistryInfoLookup registryInfo) {
        // 通过 @RecipeRule 注解 + 反射读取规则值，替代硬编码 switch：
        // 新增配方规则无需再改本类
        return RecipeRuleRegistry.isRuleEnabled(rule);
    }
}
