package com.namelessgod2008.setting;

import carpet.api.settings.Rule;

public class CarpetTNGSetting {
    @Rule(categories = {"porting"}, options = {"true", "false"})
    public static boolean bedrockCauldronTippedArrows = false;

    @Rule(categories = {"porting"}, options = {"true", "false"})
    public static boolean craftableSaddle = false;

    @Rule(categories = {"porting"}, options = {"true", "false"})
    public static boolean craftableNameTag = false;

    @Rule(categories = {"porting"}, options = {"true", "false"})
    public static boolean craftableBell = false;
}
