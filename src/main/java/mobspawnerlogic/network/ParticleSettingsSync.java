package mobspawnerlogic.network;

import mobspawnerlogic.particles.ParticleSettings;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public final class ParticleSettingsSync {

    private ParticleSettingsSync() {
    }

    public static void registerCommon() {
        PayloadTypeRegistry.clientboundPlay().register(
                ParticleSettingsPayload.TYPE, 
                ParticleSettingsPayload.CODEC
        );
    }

    public static void sendToPlayer(ServerPlayer player) {
        if (!ServerPlayNetworking.canSend(player, ParticleSettingsPayload.TYPE)) {
            return;
        }

        boolean enabled = ParticleSettings.getParticles(player);
        ParticleSettingsPayload payload = new ParticleSettingsPayload(player.getUUID(), enabled);
        ServerPlayNetworking.send(player, payload);
    }

    public static void broadcastChange(ServerPlayer player) {
        sendToPlayer(player);
    }
    
    public static void write(ParticleSettingsPayload payload, FriendlyByteBuf buf) {
        buf.writeUUID(payload.playerUuid());
        buf.writeBoolean(payload.particlesEnabled());
    }
}
