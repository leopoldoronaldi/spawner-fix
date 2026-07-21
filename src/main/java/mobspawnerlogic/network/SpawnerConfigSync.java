package mobspawnerlogic.network;

import mobspawnerlogic.gamerule.SpawnerFixGameRules;
import mobspawnerlogic.spawner.SpawnerRangeConstants;
import mobspawnerlogic.spawner.SpawnerRangeResolver;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public final class SpawnerConfigSync {
	private static volatile int clientPlayerRange = SpawnerRangeConstants.DEFAULT_PLAYER_RANGE;
	private static volatile int clientSpawnRadius = SpawnerRangeConstants.DEFAULT_SPAWN_RADIUS;

	private SpawnerConfigSync() {
	}

	public static void registerCommon() {
		PayloadTypeRegistry.clientboundPlay().register(SpawnerConfigPayload.TYPE, SpawnerConfigPayload.CODEC);

		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			sender.sendPacket(createPayload(server));
		});

		GameRuleEvents.changeCallback(SpawnerFixGameRules.SPAWNER_PLAYER_RANGE).register((value, server) -> {
			broadcast(server);
		});

		GameRuleEvents.changeCallback(SpawnerFixGameRules.SPAWNER_SPAWN_RADIUS).register((value, server) -> {
			broadcast(server);
		});
	}

	public static void applyClient(SpawnerConfigPayload payload) {
		clientPlayerRange = SpawnerRangeResolver.clampPlayerRange(payload.playerRange());
		clientSpawnRadius = SpawnerRangeResolver.clampSpawnRadius(payload.spawnRadius());
	}

	public static int clientPlayerRange() {
		return clientPlayerRange;
	}

	public static int clientSpawnRadius() {
		return clientSpawnRadius;
	}

	private static void broadcast(MinecraftServer server) {
		SpawnerConfigPayload payload = createPayload(server);

		for (ServerPlayer player : PlayerLookup.all(server)) {
			ServerPlayNetworking.send(player, payload);
		}
	}

	private static SpawnerConfigPayload createPayload(MinecraftServer server) {
		int playerRange = SpawnerRangeResolver.clampPlayerRange(
				server.getGameRules().get(SpawnerFixGameRules.SPAWNER_PLAYER_RANGE)
		);
		int spawnRadius = SpawnerRangeResolver.clampSpawnRadius(
				server.getGameRules().get(SpawnerFixGameRules.SPAWNER_SPAWN_RADIUS)
		);
		return new SpawnerConfigPayload(playerRange, spawnRadius);
	}
}
