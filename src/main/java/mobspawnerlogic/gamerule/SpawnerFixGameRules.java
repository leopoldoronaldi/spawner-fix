package mobspawnerlogic.gamerule;

import com.mojang.serialization.Codec;

import mobspawnerlogic.command.SpawnerFixIntArgument;
import mobspawnerlogic.spawner.SpawnerRangeConstants;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

public final class SpawnerFixGameRules {
	public static final GameRule<Integer> SPAWNER_PLAYER_RANGE = GameRuleBuilder
			.forInteger(SpawnerRangeConstants.DEFAULT_PLAYER_RANGE)
			.argumentType(SpawnerFixIntArgument.playerRange(0, SpawnerRangeConstants.MAX_PLAYER_RANGE))
			.codec(Codec.intRange(0, SpawnerRangeConstants.MAX_PLAYER_RANGE))
			.category(GameRuleCategory.SPAWNING)
			.buildAndRegister(Identifier.withDefaultNamespace("spawner_player_range"));

	public static final GameRule<Integer> SPAWNER_SPAWN_RADIUS = GameRuleBuilder
			.forInteger(SpawnerRangeConstants.DEFAULT_SPAWN_RADIUS)
			.argumentType(SpawnerFixIntArgument.spawnRadius(0, SpawnerRangeConstants.MAX_SPAWN_RADIUS))
			.codec(Codec.intRange(0, SpawnerRangeConstants.MAX_SPAWN_RADIUS))
			.category(GameRuleCategory.SPAWNING)
			.buildAndRegister(Identifier.withDefaultNamespace("spawner_spawn_radius"));

	private SpawnerFixGameRules() {
	}

	public static void register() {
	}
}
