package mobspawnerlogic.client;

import mobspawnerlogic.network.ParticleSettingsPayload;
import mobspawnerlogic.network.SpawnerConfigPayload;
import mobspawnerlogic.network.SpawnerConfigSync;
import mobspawnerlogic.particles.ParticleSettings;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class SpawnerFixClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientPlayNetworking.registerGlobalReceiver(SpawnerConfigPayload.TYPE, (payload, context) -> {
			SpawnerConfigSync.applyClient(payload);
		});
		
		ClientPlayNetworking.registerGlobalReceiver(ParticleSettingsPayload.TYPE, (payload, context) -> {
			ParticleSettings.updateFromServer(payload.playerUuid(), payload.particlesEnabled());
		});
	}
}
