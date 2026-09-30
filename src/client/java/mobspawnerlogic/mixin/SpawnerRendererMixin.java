package mobspawnerlogic.mixin;

import mobspawnerlogic.network.SpawnerConfigSync;

import net.minecraft.client.renderer.blockentity.SpawnerRenderer;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(SpawnerRenderer.class)
public abstract class SpawnerRendererMixin {
	private static final int VANILLA_SPAWNER_RENDER_DISTANCE = 64;
	private static final int MAX_SPAWNER_RENDER_DISTANCE = 128;

	public int getViewDistance() {
		int configuredRange = Math.min(SpawnerConfigSync.clientPlayerRange(), MAX_SPAWNER_RENDER_DISTANCE);
		return Math.max(VANILLA_SPAWNER_RENDER_DISTANCE, configuredRange);
	}
}
