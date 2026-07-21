package mobspawnerlogic;

import mobspawnerlogic.command.SpawnerFixIntArgument;
import mobspawnerlogic.command.SpawnerFixIntArgumentInfo;
import mobspawnerlogic.gamerule.SpawnerFixGameRules;
import mobspawnerlogic.network.SpawnerConfigSync;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpawnerFix implements ModInitializer {
	public static final String MOD_ID = "spawner-fix";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ArgumentTypeRegistry.registerArgumentType(
				id("spawner_fix_int"),
				SpawnerFixIntArgument.class,
				new SpawnerFixIntArgumentInfo()
		);
		SpawnerFixGameRules.register();
		SpawnerConfigSync.registerCommon();
		LOGGER.info("Spawner Fix initialized");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
