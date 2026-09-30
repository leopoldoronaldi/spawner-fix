package mobspawnerlogic;

import mobspawnerlogic.command.ParticleCommand;
import mobspawnerlogic.gamerule.SpawnerFixGameRules;
import mobspawnerlogic.network.ParticleSettingsSync;
import mobspawnerlogic.network.SpawnerConfigSync;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpawnerFix implements ModInitializer {
	public static final String MOD_ID = "spawner-fix";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		SpawnerFixGameRules.register();
		SpawnerConfigSync.registerCommon();
		ParticleSettingsSync.registerCommon();
		ParticleCommand.register();
		LOGGER.info("Spawner Fix initialized");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
