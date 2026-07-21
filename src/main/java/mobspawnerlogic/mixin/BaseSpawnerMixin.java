package mobspawnerlogic.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;

import mobspawnerlogic.spawner.SpawnerRangeResolver;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BaseSpawner.class)
public abstract class BaseSpawnerMixin {
	@Shadow
	private int spawnRange;

	@ModifyExpressionValue(
			method = "isNearPlayer",
			at = @At(
					value = "FIELD",
					target = "Lnet/minecraft/world/level/BaseSpawner;requiredPlayerRange:I"
			)
	)
	private int spawnerfix$requiredPlayerRange(int original, Level level, BlockPos pos) {
		return SpawnerRangeResolver.resolvePlayerRange(level);
	}

	@WrapMethod(method = "serverTick")
	private void spawnerfix$serverTickWithSpawnRadius(ServerLevel level, BlockPos pos, Operation<Void> original) {
		int previous = this.spawnRange;
		this.spawnRange = SpawnerRangeResolver.resolveSpawnRadius(level);

		try {
			original.call(level, pos);
		} finally {
			this.spawnRange = previous;
		}
	}
}
