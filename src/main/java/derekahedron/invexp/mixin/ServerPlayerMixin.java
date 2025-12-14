package derekahedron.invexp.mixin;

import derekahedron.invexp.sack.SackContents;
import derekahedron.invexp.sack.SackContentsReader;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ComplexItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {

    @Inject(
            method = "doTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Inventory;getContainerSize()I")
    )
    private void sendSackPackets(CallbackInfo ci) {
        ServerPlayer self = (ServerPlayer) (Object) this;
        for (int i = 0; i < self.getInventory().getContainerSize(); i++) {
            SackContentsReader contents = SackContents.of(self.getInventory().getItem(i));
            if (contents != null && !contents.isEmpty()) {
                for (ItemStack nestedStack : contents.getStacks()) {
                    if (nestedStack.getItem().isComplex()) {
                        Packet<?> packet = ((ComplexItem)nestedStack.getItem()).getUpdatePacket(nestedStack, self.level(), self);
                        if (packet != null) {
                            self.connection.send(packet);
                        }
                    }
                }
            }
        }
    }
}
