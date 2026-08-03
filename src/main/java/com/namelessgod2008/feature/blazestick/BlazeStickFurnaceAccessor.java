package com.namelessgod2008.feature.blazestick;

/**
 * 由 {@code AbstractFurnaceBlockEntityMixin} 实现：清空熔炉经验计数。
 * 原版 {@code getRecipesToAwardAndPopExperience} 只掉经验不清空计数
 * （clear 在原版玩家取物方法里），掏炉渣后必须手动清空，否则每次掏
 * 都会按完整计数重复掉经验。
 */
public interface BlazeStickFurnaceAccessor {

    void carpettng$clearRecipesUsed();
}
