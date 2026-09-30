package mobspawnerlogic.mixin;

import mobspawnerlogic.gamerule.SpawnerFixGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin {

    @Inject(method = "playerDestroy", at = @At("HEAD"))
    private void spawnerfix$dropSpawnerWithSilkTouch(ServerLevel level, ServerPlayer player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack tool, CallbackInfo ci) {
        if (blockEntity == null) {
            return;
        }

        if (level.getGameRules().get(SpawnerFixGameRules.SPAWNER_DROP_WITH_SILK_TOUCH)) {
            var enchantmentRegistry = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            var silkTouchHolder = enchantmentRegistry.getOrThrow(Enchantments.SILK_TOUCH);

            if (EnchantmentHelper.getItemEnchantmentLevel(silkTouchHolder, tool) > 0) {
                CompoundTag nbt = blockEntity.saveCustomOnly(level.registryAccess());

                boolean isSpawner = blockEntity instanceof SpawnerBlockEntity || nbt.contains("SpawnData") || nbt.contains("SpawnPotentials");

                if (isSpawner) {
                    ItemStack itemStack = new ItemStack(state.getBlock());
                    nbt.remove("Delay");
                    itemStack.set(DataComponents.BLOCK_ENTITY_DATA, TypedEntityData.of(blockEntity.getType(), nbt));

                    nbt.getCompound("SpawnData").ifPresent(spawnData -> {
                        spawnData.getCompound("entity").ifPresent(entity -> {
                            entity.getString("id").ifPresent(idStr -> {
                                try {
                                    Identifier id = Identifier.parse(idStr);
                                    itemStack.set(DataComponents.ITEM_NAME, Component.translatable("template.spawner-fix.mob_spawner", Component.translatable("entity." + id.getNamespace() + "." + id.getPath())));
                                } catch (Exception ignored) {}
                            });
                        });
                    });

                    Block.popResource(level, pos, itemStack);
                }
            }
        }
    }
}
