package mobspawnerlogic.network;

import mobspawnerlogic.SpawnerFix;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import java.util.UUID;

public record ParticleSettingsPayload(UUID playerUuid, boolean particlesEnabled) implements CustomPacketPayload {
    public static final Identifier ID = SpawnerFix.id("particle_settings");
    public static final CustomPacketPayload.Type<ParticleSettingsPayload> TYPE = new CustomPacketPayload.Type<>(ID);

    public static final StreamCodec<FriendlyByteBuf, ParticleSettingsPayload> CODEC = new StreamCodec<>() {
        @Override
        public ParticleSettingsPayload decode(FriendlyByteBuf buf) {
            return new ParticleSettingsPayload(buf.readUUID(), buf.readBoolean());
        }

        @Override
        public void encode(FriendlyByteBuf buf, ParticleSettingsPayload payload) {
            buf.writeUUID(payload.playerUuid());
            buf.writeBoolean(payload.particlesEnabled());
        }
    };

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
