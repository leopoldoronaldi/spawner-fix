package mobspawnerlogic.mixin;

import mobspawnerlogic.gamerule.SpawnerFixGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.resources.Identifier;

@Mixin(BlockItem.class)
public class BlockItemMixin {

    @Inject(method = "updateCustomBlockEntityTag(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private static void spawnerfix$allowSpawnerDataLoading(Level level, Player player, BlockPos pos, ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        if (serverLevel.getGameRules().get(SpawnerFixGameRules.SPAWNER_DROP_WITH_SILK_TOUCH)) {
            var customData = itemStack.get(DataComponents.BLOCK_ENTITY_DATA);
            if (customData != null && customData.type() == BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(Identifier.withDefaultNamespace("mob_spawner"))) {
                BlockEntity blockEntity = serverLevel.getBlockEntity(pos);
                if (blockEntity instanceof SpawnerBlockEntity) {
                    // Force load data even for non-OP players
                    cir.setReturnValue(customData.loadInto(blockEntity, serverLevel.registryAccess()));
                }
            }
        }
    }
}
