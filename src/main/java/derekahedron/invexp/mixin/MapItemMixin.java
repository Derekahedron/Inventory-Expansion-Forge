package derekahedron.invexp.mixin;

import derekahedron.invexp.sack.SackContents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(MapItem.class)
public class MapItemMixin {

    /**
     * We want to update maps if they are in a sack in the offhand, but vanilla code doesn't check this.
     * So we check if the stack we are ticking is equal to the offhand stack and update colors if so.
     */
    @Inject(
            method = "inventoryTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;getOffhandItem()Lnet/minecraft/world/item/ItemStack;"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void tickInOffhandSack(
            ItemStack stack, Level world, Entity entity, int p_42873_, boolean selected, CallbackInfo ci, @NotNull MapItemSavedData mapState
    ) {
        // Same logic for checking maps as vanilla
        if (!mapState.locked && !selected && entity instanceof Player player) {
            SackContents contents = SackContents.of(player.getOffhandItem());
            if (contents != null && !contents.isEmpty() && ItemStack.matches(stack, contents.getSelectedStack())) {
                ((MapItem) (Object) this).update(world, entity, mapState);
            }
        }
    }
}
