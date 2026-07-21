package mobspawnerlogic.command;

import java.util.Collection;
import java.util.List;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;

import net.minecraft.network.chat.Component;

/**
 * Integer argument with Spawner-Fix-specific out-of-range messages.
 */
public final class SpawnerFixIntArgument implements ArgumentType<Integer> {
	private static final Collection<String> EXAMPLES = List.of("0", "4", "128", "10000");

	public enum Kind {
		PLAYER_RANGE(
				"argument.spawner_player_range.too_low",
				"argument.spawner_player_range.too_high"
		),
		SPAWN_RADIUS(
				"argument.spawner_spawn_radius.too_low",
				"argument.spawner_spawn_radius.too_high"
		);

		private final String tooLowKey;
		private final String tooHighKey;

		Kind(String tooLowKey, String tooHighKey) {
			this.tooLowKey = tooLowKey;
			this.tooHighKey = tooHighKey;
		}
	}

	private final int minimum;
	private final int maximum;
	private final Kind kind;
	private final Dynamic2CommandExceptionType tooLow;
	private final Dynamic2CommandExceptionType tooHigh;

	public SpawnerFixIntArgument(int minimum, int maximum, Kind kind) {
		this.minimum = minimum;
		this.maximum = maximum;
		this.kind = kind;
		this.tooLow = new Dynamic2CommandExceptionType(
				(value, limit) -> Component.translatableEscape(kind.tooLowKey, limit, value)
		);
		this.tooHigh = new Dynamic2CommandExceptionType(
				(value, limit) -> Component.translatableEscape(kind.tooHighKey, limit, value)
		);
	}

	public static SpawnerFixIntArgument playerRange(int minimum, int maximum) {
		return new SpawnerFixIntArgument(minimum, maximum, Kind.PLAYER_RANGE);
	}

	public static SpawnerFixIntArgument spawnRadius(int minimum, int maximum) {
		return new SpawnerFixIntArgument(minimum, maximum, Kind.SPAWN_RADIUS);
	}

	public int getMinimum() {
		return this.minimum;
	}

	public int getMaximum() {
		return this.maximum;
	}

	public Kind getKind() {
		return this.kind;
	}

	@Override
	public Integer parse(StringReader reader) throws CommandSyntaxException {
		int start = reader.getCursor();
		int value = reader.readInt();

		if (value < this.minimum) {
			reader.setCursor(start);
			throw this.tooLow.createWithContext(reader, value, this.minimum);
		}

		if (value > this.maximum) {
			reader.setCursor(start);
			throw this.tooHigh.createWithContext(reader, value, this.maximum);
		}

		return value;
	}

	@Override
	public Collection<String> getExamples() {
		return EXAMPLES;
	}
}
