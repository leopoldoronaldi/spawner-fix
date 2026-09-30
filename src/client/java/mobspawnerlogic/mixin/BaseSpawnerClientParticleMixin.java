package mobspawnerlogic.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import mobspawnerlogic.network.SpawnerConfigSync;
import mobspawnerlogic.particles.ParticleSettings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BaseSpawner.class)
public abstract class BaseSpawnerClientParticleMixin {
	private static final int MAX_PARTICLE_RANGE = 128;

	@WrapOperation(
			method = "clientTick",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"
			)
	)
	private void spawnerfix$addSpawnerParticleInConfiguredRange(
			Level level,
			ParticleOptions particle,
			double x,
			double y,
			double z,
			double xd,
			double yd,
			double zd,
			Operation<Void> original
	) {
		Minecraft minecraft = Minecraft.getInstance();
		if (minecraft.player == null) {
			original.call(level, particle, x, y, z, xd, yd, zd);
			return;
		}

		if (!SpawnerConfigSync.clientParticlesEnabled() || !ParticleSettings.shouldShowParticles(minecraft.player.getUUID())) {
			return;
		}

		int range = Math.min(SpawnerConfigSync.clientPlayerRange(), MAX_PARTICLE_RANGE);
		if (range <= 0 || minecraft.player.distanceToSqr(x, y, z) > (double)range * range) {
			return;
		}

		if (level instanceof ClientLevel clientLevel) {
			clientLevel.addParticle(particle, true, true, x, y, z, xd, yd, zd);
		} else {
			original.call(level, particle, x, y, z, xd, yd, zd);
		}
	}
}
