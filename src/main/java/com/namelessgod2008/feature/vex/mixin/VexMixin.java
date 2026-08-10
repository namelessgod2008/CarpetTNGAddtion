package com.namelessgod2008.feature.vex.mixin;

import com.namelessgod2008.setting.CarpetTNGSetting;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Vex;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 唤魔者死亡后，其召唤的恼鬼立即死亡：
 * 原版恼鬼有 {@code LimitedLifespan} 倒计时（唤魔者召唤时 setLimitedLife），
 * 唤魔者死亡后恼鬼仍存活到时间结束。规则开启时，恼鬼每 tick 检查 owner
 * （唤魔者）是否存活，owner 已死则立即 discard。
 */
@Mixin(Vex.class)
public class VexMixin {

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void dieWithOwner(CallbackInfo ci) {
        if (!CarpetTNGSetting.evokerDeathKillsVexes) return;
        Vex vex = (Vex) (Object) this;
        Mob owner = vex.getOwner();
        if (owner != null && !owner.isAlive()) {
            vex.discard();
            ci.cancel();
        }
    }
}
