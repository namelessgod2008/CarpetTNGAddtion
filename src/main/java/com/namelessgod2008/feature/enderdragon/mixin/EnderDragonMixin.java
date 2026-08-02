package com.namelessgod2008.feature.enderdragon.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * 持续高经验打龙：末影龙死亡时（{@code EnderDragon.tickDeath}），
 * 经验量判定为 {@code 500}，仅当 {@code !dragonFight.hasPreviouslyKilledDragon()}
 * （首次击杀）才为 {@code 12000}。规则开启时令该判定恒为"首次击杀"，
 * 使重复击杀也掉落 12000 经验。
 */
@Mixin(EnderDragon.class)
public abstract class EnderDragonMixin {

    @Redirect(method = "tickDeath",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/world/level/dimension/end/EndDragonFight;hasPreviouslyKilledDragon()Z"))
    private boolean constantHighXp(EndDragonFight fight) {
        // 规则开启：视为首次击杀（12000 经验）；否则返回原值
        return CarpetTNGSetting.constantHighEnderDragonXp ? false : fight.hasPreviouslyKilledDragon();
    }
}
