package com.namelessgod2008.feature.zombifiedpiglin.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;

/**
 * 禁止僵尸猪灵掉落金剑：
 * 原版僵尸猪灵生成时在 {@code populateDefaultEquipmentSlots} 装备金剑到主手
 * （ZombifiedPiglin.java:227），死亡时按 {@code handDropChances[MAINHAND]}
 * 默认 8.5%（{@code Mob.DEFAULT_EQUIPMENT_DROP_CHANCE}）概率掉落。
 * <p>
 * 包裹 {@link Mob#getEquipmentDropChance(EquipmentSlot)}：规则开启且实体是僵尸猪灵、
 * 槽位为主手、手持金剑时返回 0.0F（{@code Mob.dropCustomDeathLoot} 中
 * {@code f != 0.0F} 判定不成立，完全跳过该掉落）。对已存在的僵尸猪灵同样即时生效。
 * <p>
 * 注：{@code getEquipmentDropChance} 声明在 {@code Mob}，僵尸猪灵未覆盖，
 * 故注入 {@code Mob} 并按实体类型过滤。
 */
@Mixin(Mob.class)
public class ZombifiedPiglinMixin {

    @WrapMethod(method = "getEquipmentDropChance")
    private float noGoldenSwordDrop(EquipmentSlot slot, Operation<Float> original) {
        float chance = original.call(slot);
        if (CarpetTNGSetting.zombifiedPiglinNoGoldenSwordDrop
                && (Object) this instanceof ZombifiedPiglin zombifiedPiglin
                && slot == EquipmentSlot.MAINHAND
                && zombifiedPiglin.getItemBySlot(slot).is(Items.GOLDEN_SWORD)) {
            return 0.0F; // 不掉落金剑
        }
        return chance;
    }
}
