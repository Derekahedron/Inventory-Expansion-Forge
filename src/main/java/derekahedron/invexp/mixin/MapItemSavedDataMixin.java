package derekahedron.invexp.mixin;

import derekahedron.invexp.sack.SackContents;
import derekahedron.invexp.sack.SackContentsReader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(MapItemSavedData.class)
public class MapItemSavedDataMixin {

    @Unique
    @Nullable
    Player invexp_$trackedPlayer;

    @ModifyArg(
            method = "tickCarriedBy",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Inventory;contains(Lnet/minecraft/world/item/ItemStack;)Z"
            )
    )
    private ItemStack containsSackMap(ItemStack mapStack) {
        if (invexp_$trackedPlayer == null) {
            return mapStack;
        }

        for (List<ItemStack> stacks : invexp_$trackedPlayer.getInventory().compartments) {
            for (ItemStack stack : stacks) {
                SackContentsReader contents = SackContents.of(stack);
                if (contents != null && !contents.isEmpty()) {
                    for (ItemStack nestedStack : contents.getStacks()) {
                        if (!nestedStack.isEmpty() && ItemStack.isSameItemSameTags(nestedStack, mapStack)) {
                            return stack;
                        }
                    }
                }
            }
        }

        return mapStack;
    }

    @Inject(
            method = "tickCarriedBy",
            at = @At("HEAD")
    )
    private void setTrackedPlayer(Player player, ItemStack stack, CallbackInfo ci) {
        invexp_$trackedPlayer = player;
    }

    @ModifyVariable(
            method = "tickCarriedBy",
            at = @At("STORE")
    )
    private MapItemSavedData.HoldingPlayer setTrackedPlayer(MapItemSavedData.HoldingPlayer player) {
        invexp_$trackedPlayer = player.player;
        return player;
    }
}
