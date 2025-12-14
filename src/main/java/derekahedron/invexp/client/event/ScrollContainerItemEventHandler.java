package derekahedron.invexp.client.event;

import derekahedron.invexp.InventoryExpansion;
import derekahedron.invexp.bundle.BundleContents;
import derekahedron.invexp.client.util.InvExpClientUtil;
import derekahedron.invexp.network.InvExpPacketHandler;
import derekahedron.invexp.network.SetSelectedIndexPacket;
import derekahedron.invexp.sack.SackContents;
import derekahedron.invexp.util.ContainerItemContents;
import derekahedron.invexp.client.util.Scroller;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Vector2i;

@Mod.EventBusSubscriber(modid = InventoryExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ScrollContainerItemEventHandler {
    public static final Scroller scroller = new Scroller();

    @SubscribeEvent
    public static void onScroll(ScreenEvent.MouseScrolled event) {
        Screen screen = event.getScreen();

        // Ensure screen is a handled screen and the player is not null
        if (!(screen instanceof AbstractContainerScreen<?> containerScreen) ||
                containerScreen.getMinecraft().player == null) {
            return;
        }
        Player player = containerScreen.getMinecraft().player;

        Slot slot = containerScreen.getSlotUnderMouse();
        if (slot == null || !slot.hasItem() || !slot.allowModification(player)) {
            return;
        }
        ItemStack stack = slot.getItem();

        // Fail if contents are invalid
        ContainerItemContents contents = ContainerItemContents.of(stack, player.level());
        if (contents == null) {
            contents = BundleContents.of(stack);
        }
        if (contents == null || contents.isEmpty()) {
            return;
        }

        // Get amount scrolled. Use horizontal scroll if vertical is empty
        Vector2i scrollVector = scroller.update(-event.getScrollDelta(), 0);
        int numScrolled = scrollVector.y == 0 ? -scrollVector.x : scrollVector.y;
        if (numScrolled == 0) {
            event.setCanceled(true);
            return;
        }

        // Scroll to the new index
        int newSelectedIndex = Scroller.scrollCycling(numScrolled, contents.getSelectedIndex(), contents.getStacks().size());
        if (newSelectedIndex == contents.getSelectedIndex()) {
            event.setCanceled(true);
            return;
        }

        // Creative screens do not have slot ids that are synced, so we must find the corresponding slot
        // in the creative inventory
        slot = InvExpClientUtil.getTrueSlot(slot, player);
        if (slot == null) {
            return;
        }

        // If you can scroll, set index and send packet to server
        if (contents instanceof SackContents) {
            // Animate equip progress when changing stack
            for (InteractionHand hand : InteractionHand.values()) {
                if (player.getItemInHand(hand) == stack) {
                    containerScreen.getMinecraft().gameRenderer.itemInHandRenderer.itemUsed(hand);
                }
            }
        }
        contents.setSelectedIndex(newSelectedIndex);
        InvExpPacketHandler.INSTANCE.sendToServer(new SetSelectedIndexPacket(slot.index, newSelectedIndex));
        event.setCanceled(true);
    }
}
