package mobspawnerlogic.mixin;

import mobspawnerlogic.gamerule.SpawnerFixGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpawnerBlock.class)
public abstract class SpawnerBlockMixin {

    @Inject(method = "spawnAfterBreak", at = @At("HEAD"), cancellable = true)
    private void spawnerfix$cancelExperienceDrop(BlockState state, ServerLevel level, BlockPos pos, ItemStack tool, boolean dropExperience, CallbackInfo ci) {
        if (level.getGameRules().get(SpawnerFixGameRules.SPAWNER_DROP_WITH_SILK_TOUCH)) {
            var enchantmentRegistry = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            var silkTouchHolder = enchantmentRegistry.getOrThrow(Enchantments.SILK_TOUCH);

            if (EnchantmentHelper.getItemEnchantmentLevel(silkTouchHolder, tool) > 0) {
                ci.cancel();
            }
        }
    }
}
