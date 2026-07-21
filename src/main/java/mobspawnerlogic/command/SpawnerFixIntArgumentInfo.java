package mobspawnerlogic.command;

import com.google.gson.JsonObject;

import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.network.FriendlyByteBuf;

/**
 * Network / JSON serializer for {@link SpawnerFixIntArgument}.
 */
public final class SpawnerFixIntArgumentInfo
		implements ArgumentTypeInfo<SpawnerFixIntArgument, SpawnerFixIntArgumentInfo.Template> {
	@Override
	public void serializeToNetwork(Template template, FriendlyByteBuf out) {
		out.writeVarInt(template.minimum);
		out.writeVarInt(template.maximum);
		out.writeEnum(template.kind);
	}

	@Override
	public Template deserializeFromNetwork(FriendlyByteBuf in) {
		return new Template(
				in.readVarInt(),
				in.readVarInt(),
				in.readEnum(SpawnerFixIntArgument.Kind.class)
		);
	}

	@Override
	public void serializeToJson(Template template, JsonObject out) {
		out.addProperty("min", template.minimum);
		out.addProperty("max", template.maximum);
		out.addProperty("kind", template.kind.name());
	}

	@Override
	public Template unpack(SpawnerFixIntArgument argument) {
		return new Template(argument.getMinimum(), argument.getMaximum(), argument.getKind());
	}

	public final class Template implements ArgumentTypeInfo.Template<SpawnerFixIntArgument> {
		private final int minimum;
		private final int maximum;
		private final SpawnerFixIntArgument.Kind kind;

		public Template(int minimum, int maximum, SpawnerFixIntArgument.Kind kind) {
			this.minimum = minimum;
			this.maximum = maximum;
			this.kind = kind;
		}

		@Override
		public SpawnerFixIntArgument instantiate(CommandBuildContext context) {
			return new SpawnerFixIntArgument(this.minimum, this.maximum, this.kind);
		}

		@Override
		public ArgumentTypeInfo<SpawnerFixIntArgument, ?> type() {
			return SpawnerFixIntArgumentInfo.this;
		}
	}
}
