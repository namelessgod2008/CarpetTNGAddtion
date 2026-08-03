package com.namelessgod2008;

import carpet.CarpetServer;
import carpet.api.settings.SettingsManager;
import com.namelessgod2008.feature.blazestick.BlazeStickFurnaceXpHandler;
import com.namelessgod2008.feature.cauldron.CauldronArrowHandler;
import com.namelessgod2008.feature.dispenser.DispenserPlantingHandler;
import com.namelessgod2008.feature.gourd.BonemealGourdHandler;
import com.namelessgod2008.feature.wart.NetherWartBlazeHandler;
import com.namelessgod2008.setting.RuleEnabledCondition;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarpetTNGAddtion implements ModInitializer {
	public static final String MOD_ID = "carpettngaddtion";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Carpet TNG Addition loaded!");

		// Recipe conditions must be registered before data packs load
		RuleEnabledCondition.register();

		// Skip Carpet extension registration during data generation —
		// translations haven't been generated yet and Carpet would crash.
		if (System.getProperty("fabric-api.datagen") != null) {
			LOGGER.info("Data generation mode detected, skipping extension registration");
			return;
		}

		// When a recipe rule changes, reload data packs so the conditional
		// recipes are (de)registered and synced to clients (REI included).
		// Skip during server startup: config-driven rule changes fire while
		// data packs / Carpet's script server are not ready yet (reloadResources
		// would NPE and is pointless anyway — recipes read rule states when loaded).
		SettingsManager.registerGlobalRuleObserver((source, rule, userInput) -> {
			String name = rule.name();
			if (name.equals("craftableSaddle") || name.equals("craftableNameTag") || name.equals("craftableBell")) {
				MinecraftServer server = source != null ? source.getServer() : null;
				if (server != null && server.getTickCount() > 0) {
					server.reloadResources(server.getPackRepository().getSelectedIds())
							.exceptionally(e -> {
								LOGGER.error("Failed to reload resources after rule change: {}", name, e);
								return null;
							});
				}
			}
		});

		CarpetServer.manageExtension(new CarpetTNGExtension());
		CauldronArrowHandler.register();
		DispenserPlantingHandler.register();
		NetherWartBlazeHandler.register();
		BonemealGourdHandler.register();
		BlazeStickFurnaceXpHandler.register();
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
