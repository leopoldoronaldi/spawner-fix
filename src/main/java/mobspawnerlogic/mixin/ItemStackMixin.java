package mobspawnerlogic.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "getTooltipLines", at = @At("RETURN"), cancellable = true)
    private void spawnerfix$removeCommandWarning(Item.TooltipContext context, Player player, TooltipFlag type, CallbackInfoReturnable<List<Component>> cir) {
        ItemStack stack = (ItemStack)(Object)this;
        if (stack.getItem() == Blocks.SPAWNER.asItem()) {
            List<Component> tooltip = cir.getReturnValue();
            if (tooltip == null || tooltip.isEmpty()) return;
            
            boolean modified = false;
            List<Component> result = tooltip;
            
            for (int i = 0; i < tooltip.size(); i++) {
                Component component = tooltip.get(i);
                if (component.getContents() instanceof TranslatableContents translatable) {
                    String key = translatable.getKey();
                    if (key.equals("item.op_block_warning.line1") || 
                        key.equals("item.op_block_warning.line2") || 
                        key.equals("item.op_block_warning.line3")) {
                        
                        if (!modified) {
                            try {
                                tooltip.remove(i);
                                i--;
                                modified = true;
                            } catch (UnsupportedOperationException e) {
                                result = new ArrayList<>(tooltip);
                                result.remove(i);
                                i--;
                                modified = true;
                            }
                        } else {
                            result.remove(i);
                            i--;
                        }
                    }
                }
            }
            
            if (modified && result != tooltip) {
                cir.setReturnValue(result);
            }
        }
    }
}
