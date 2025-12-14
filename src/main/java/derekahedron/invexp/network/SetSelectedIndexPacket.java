package derekahedron.invexp.network;

import derekahedron.invexp.InventoryExpansion;
import derekahedron.invexp.bundle.BundleContents;
import derekahedron.invexp.util.ContainerItemContents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Supplier;

public class SetSelectedIndexPacket {
    public final int slotId;
    public final int selectedIndex;

    public SetSelectedIndexPacket(int slotId, int selectedIndex) {
        this.slotId = slotId;
        this.selectedIndex = selectedIndex;
    }

    @ParametersAreNonnullByDefault
    public SetSelectedIndexPacket(FriendlyByteBuf buffer) {
        this(buffer.readInt(), buffer.readInt());
    }

    @ParametersAreNonnullByDefault
    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeInt(slotId);
        buffer.writeInt(selectedIndex);
    }

    @ParametersAreNonnullByDefault
    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            if (player == null) {
                return;
            }
            if (slotId < 0 || slotId >= player.containerMenu.slots.size()) {
                InventoryExpansion.LOGGER.debug(
                        "Player {} set selected index of invalid slot id {}", player, slotId
                );
                return;
            }

            // Makes sure the container is valid
            ItemStack stack = player.containerMenu.slots.get(slotId).getItem();
            ContainerItemContents contents = ContainerItemContents.of(stack, player.level());
            if (contents == null) {
                contents = BundleContents.of(stack);
            }
            if (contents == null) {
                InventoryExpansion.LOGGER.debug(
                        "Player {} set selected index of invalid stack {}", player, stack
                );
                return;
            }

            contents.setSelectedIndex(selectedIndex);
            player.containerMenu.slotsChanged(player.getInventory());
        });
    }
}
