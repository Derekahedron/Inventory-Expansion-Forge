package derekahedron.invexp.mixin.client;

import derekahedron.invexp.sack.SackDefaultManager;
import derekahedron.invexp.util.ContainerItemContents;
import derekahedron.invexp.util.ContainerItemContentsReader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.ClientRegistryLayer;
import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.network.protocol.game.ClientboundLoginPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateTagsPacket;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {

    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    @Final
    private Connection connection;

    @Shadow
    private LayeredRegistryAccess<ClientRegistryLayer> registryAccess;

    @Inject(
            method = "handleContainerSetSlot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z",
                    ordinal = 1
            )
    )
    private void changeCountBeforeComparison (
            ClientboundContainerSetSlotPacket packet,
            CallbackInfo ci
    ) {
        if (minecraft.player == null) {
            return;
        }
        ItemStack newStack = packet.getItem();
        ItemStack oldStack = minecraft.player.containerMenu.getSlot(packet.getSlot()).getItem();

        ContainerItemContentsReader oldContents = ContainerItemContents.of(oldStack);
        ContainerItemContentsReader newContents = ContainerItemContents.of(newStack);
        if (oldContents == null || newContents == null) {
            return;
        }
        int oldCount = 0;
        int newCount = 0;
        for (ItemStack stack : oldContents.getStacks()) {
            oldCount += stack.getCount();
        }
        for (ItemStack stack : newContents.getStacks()) {
            newCount += stack.getCount();
        }
        if (newCount > oldCount) {
            newStack.setPopTime(5);
        }
    }

    /**
     * After receiving new tags from the server, if the connection is not local,
     * signal data pack change.
     */
    @Inject(
            method = "handleUpdateTags",
            at = @At("RETURN")
    )
    private void afterSynchronizeTags(ClientboundUpdateTagsPacket p_105134_, CallbackInfo ci) {
        if (!connection.isMemoryConnection()) {
            SackDefaultManager.updateInstanceSackDefaults();
        }
    }

    @Inject(
            method = "handleLogin",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Collections;shuffle(Ljava/util/List;)V"
            )
    )
    private void afterSynchronizeTags(ClientboundLoginPacket p_105030_, CallbackInfo ci) {
        if (!connection.isMemoryConnection()) {
            SackDefaultManager.createNewInstance(registryAccess.compositeAccess());
        }
    }
}
