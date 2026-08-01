package com.namelessgod2008.setting;

import carpet.api.settings.Rule;

public class CarpetTNGSetting {
    @Rule(categories = {"porting", "TNG"}, options = {"true", "false"})
    public static boolean bedrockCauldronTippedArrows = false;

    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean craftableSaddle = false;

    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean craftableNameTag = false;

    @Rule(categories = {"survival", "TNG"}, options = {"true", "false"})
    public static boolean craftableBell = false;

    @Rule(categories = {"feature", "TNG", "dispenser"}, options = {"true", "false"})
    public static boolean dispenserPlanting = false;
}
