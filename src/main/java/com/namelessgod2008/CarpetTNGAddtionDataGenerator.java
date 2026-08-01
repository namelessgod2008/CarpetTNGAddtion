package com.namelessgod2008;

import com.namelessgod2008.datagen.RecipeGenerator;
import com.namelessgod2008.datagen.TranslationGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class CarpetTNGAddtionDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
		pack.addProvider(TranslationGenerator.English::new);
		pack.addProvider(TranslationGenerator.Chinese::new);
		pack.addProvider(RecipeGenerator::new);
	}
}
