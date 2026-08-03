package com.namelessgod2008.feature.blazestick.mixin;

import com.namelessgod2008.feature.blazestick.BlazeStickFurnaceAccessor;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

/**
 * 烈焰棒掏炉渣：暴露清空经验计数的方法。
 * 原版 {@code getRecipesToAwardAndPopExperience} 掉经验后不清空 recipesUsed
 * （clear 在玩家取物方法 awardUsedRecipesAndPopExperience 里），掏炉渣需手动清空，
 * 使熔炉持续烧炼时每次掏都能按"当前积累"正确掉落（随时可掏，不重复）。
 */
@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin implements BlazeStickFurnaceAccessor {

    @Shadow
    @Final
    private Reference2IntOpenHashMap<ResourceKey<Recipe<?>>> recipesUsed;

    @Override
    @Unique
    public void carpettng$clearRecipesUsed() {
        this.recipesUsed.clear();
    }
}
