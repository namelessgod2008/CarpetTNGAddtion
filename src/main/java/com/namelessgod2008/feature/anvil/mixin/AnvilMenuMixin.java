package com.namelessgod2008.feature.anvil.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 铁砧相关规则（feature/anvil）：
 * <ul>
 *   <li>removeAnvilTooExpensive：费用达到 40 级时原版会清空结果显示"过于昂贵"，
 *       规则开启时让该判定不成立（返回 min(cost, 39)），高费用操作仍可进行</li>
 *   <li>cheapAnvilRename：纯重命名（{@code j == i && j > 0}）时费用恒为 1 级，
 *       且天然不会被"过于昂贵"拦截（1 &lt; 40）</li>
 *   <li>durableAnvil：使用铁砧（修复/附魔/改名）不再使其损坏（12% 概率下降一级）；
 *       铁砧仍可被物理破坏</li>
 * </ul>
 * createResult 里 {@code DataSlot.get()} 有两处：ordinal 0 = 仅重命名费用 cap
 * （保持原版，不拦截）、ordinal 1 = 过于昂贵判定（removeAnvilTooExpensive 拦截）。
 */
@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {

    @Shadow
    @Final
    private DataSlot cost;

    @Redirect(method = "createResult",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/DataSlot;get()I", ordinal = 1))
    private int removeTooExpensive(@NotNull DataSlot slot) {
        int cost = slot.get();
        // 规则开启：让"费用 >= 40"判定不成立（结果保留，费用按真实值收取）；否则原样
        return CarpetTNGSetting.removeAnvilTooExpensive ? Math.min(cost, 39) : cost;
    }

    // ordinal 1 = line 242 纯改名分支的 onlyRenaming = true（ordinal 0 是 createResult 开头的重置 = false，注入在那会被后续真实费用覆盖）
    @Inject(method = "createResult",
            at = @At(value = "FIELD", target = "Lnet/minecraft/world/inventory/AnvilMenu;onlyRenaming:Z", ordinal = 1))
    private void cheapRename(CallbackInfo ci) {
        if (CarpetTNGSetting.cheapAnvilRename) {
            this.cost.set(1); // 纯改名分支内：费用恒 1 级，不受物品累计惩罚影响
        }
    }

    @Redirect(method = "method_24922", // onTake 里 access.execute 的 lambda 方法（static，混淆名开发/生产一致；损坏判定在其中）
              at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;nextFloat()F"))
    private static float durableAnvil(RandomSource random) {
        // 规则开启：让 12% 铁砧损坏判定（nextFloat() < 0.12F）不成立（走 else 音效分支）；否则原样
        return CarpetTNGSetting.durableAnvil ? 1.0f : random.nextFloat();
    }
}
