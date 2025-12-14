package derekahedron.invexp.mixin.client;

import derekahedron.invexp.entity.player.PlayerEntityDuck;
import derekahedron.invexp.network.InvExpPacketHandler;
import derekahedron.invexp.network.SetSelectedIndexPacket;
import derekahedron.invexp.sack.SackContents;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    /**
     * Start player using sack before they use an item.
     */
    @Inject(
            method = "startUseItem",
            at = @At("HEAD")
    )
    private void beforeItemUse(CallbackInfo ci) {
        Minecraft self = (Minecraft) (Object) this;
        if (self.player != null) {
            ((PlayerEntityDuck) self.player).invexp_$startUsingSack();
        }
    }

    /**
     * Stop player using sack after they use an item.
     */
    @Inject(
            method = "startUseItem",
            at = @At("RETURN")
    )
    private void afterItemUse(CallbackInfo ci) {
        Minecraft self = (Minecraft) (Object) this;
        if (self.player != null) {
            ((PlayerEntityDuck) self.player).invexp_$stopUsingSack();
        }
    }

    @Inject(
            method = "pickBlock",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Inventory;findSlotMatchingItem(Lnet/minecraft/world/item/ItemStack;)I",
                    by = 2),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            cancellable = true
    )
    private void setSelectedIndexToPickBlock(
            CallbackInfo ci,
            boolean flag,
            BlockEntity blockentity,
            HitResult.Type hitresult$type,
            ItemStack itemstack,
            Inventory inventory) {
        if (inventory.findSlotMatchingItem(itemstack) != -1) {
            return;
        }

        for (int slot = 0; slot < inventory.items.size(); slot++) {
            SackContents contents = SackContents.of(inventory.items.get(slot), inventory.player.level());
            if (contents != null && !contents.isEmpty()) {
                int newSelectedIndex = contents.indexOf(itemstack, contents.getSelectedIndex());
                if (newSelectedIndex != -1) {
                    contents.setSelectedIndex(newSelectedIndex);
                    InvExpPacketHandler.INSTANCE.sendToServer(new SetSelectedIndexPacket(
                            Inventory.isHotbarSlot(inventory.selected) ? inventory.selected + 36 : inventory.selected,
                            newSelectedIndex
                    ));
                    ci.cancel();
                    return;
                }
            }
        }
    }
}
