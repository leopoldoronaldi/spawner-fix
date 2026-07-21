package mobspawnerlogic.network;

import mobspawnerlogic.SpawnerFix;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record SpawnerConfigPayload(int playerRange, int spawnRadius) implements CustomPacketPayload {
	public static final Identifier ID = SpawnerFix.id("config");
	public static final CustomPacketPayload.Type<SpawnerConfigPayload> TYPE = new CustomPacketPayload.Type<>(ID);

	public static final StreamCodec<RegistryFriendlyByteBuf, SpawnerConfigPayload> CODEC = StreamCodec.composite(
			ByteBufCodecs.VAR_INT,
			SpawnerConfigPayload::playerRange,
			ByteBufCodecs.VAR_INT,
			SpawnerConfigPayload::spawnRadius,
			SpawnerConfigPayload::new
	);

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
