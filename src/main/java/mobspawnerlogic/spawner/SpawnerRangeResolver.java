package mobspawnerlogic.spawner;

import mobspawnerlogic.gamerule.SpawnerFixGameRules;
import mobspawnerlogic.network.SpawnerConfigSync;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public final class SpawnerRangeResolver {
	private SpawnerRangeResolver() {
	}

	public static int resolvePlayerRange(Level level) {
		return readPlayerRange(level);
	}

	public static int resolveSpawnRadius(Level level) {
		return readSpawnRadius(level);
	}

	public static int clampPlayerRange(int value) {
		return Mth.clamp(value, 0, SpawnerRangeConstants.MAX_PLAYER_RANGE);
	}

	public static int clampSpawnRadius(int value) {
		return Mth.clamp(value, 0, SpawnerRangeConstants.MAX_SPAWN_RADIUS);
	}

	private static int readPlayerRange(Level level) {
		if (level instanceof ServerLevel serverLevel) {
			return clampPlayerRange(serverLevel.getGameRules().get(SpawnerFixGameRules.SPAWNER_PLAYER_RANGE));
		}

		return SpawnerConfigSync.clientPlayerRange();
	}

	private static int readSpawnRadius(Level level) {
		if (level instanceof ServerLevel serverLevel) {
			return clampSpawnRadius(serverLevel.getGameRules().get(SpawnerFixGameRules.SPAWNER_SPAWN_RADIUS));
		}

		return SpawnerConfigSync.clientSpawnRadius();
	}
}
