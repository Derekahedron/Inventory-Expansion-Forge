package derekahedron.invexp.mixin.client;

import derekahedron.invexp.bundle.BundleContents;
import derekahedron.invexp.client.util.ContainerItemSlotDragger;
import derekahedron.invexp.client.util.InvExpClientUtil;
import derekahedron.invexp.client.util.OpenItemTextures;
import derekahedron.invexp.item.QuiverItem;
import derekahedron.invexp.item.DyeableSackItem;
import derekahedron.invexp.network.InvExpPacketHandler;
import derekahedron.invexp.network.SetSelectedIndexPacket;
import derekahedron.invexp.sack.SackContents;
import derekahedron.invexp.sack.SackContentsReader;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin {

    @Shadow @Final protected Set<Slot> quickCraftSlots;
    @Shadow protected boolean isQuickCrafting;
    @Shadow @Nullable private Slot clickedSlot;

    @Shadow protected abstract List<Component> getTooltipFromContainerItem(ItemStack p_283689_);

    @Shadow protected int imageWidth;

    @Shadow protected abstract boolean isHovering(Slot p_97775_, double p_97776_, double p_97777_);

    @Shadow @Nullable protected Slot hoveredSlot;

    @Unique
    @Nullable
    private Slot invexp_$currentSlot;
    @Unique
    private boolean invexp$hasMoved;
    @Unique
    @Nullable
    private SackContentsReader invexp_$openContents;
    @Unique
    private int invexp$mouseX;
    @Unique
    private int invexp$mouseY;
    @Unique
    @Nullable
    private Slot invexp_$hoveredBundleSlot;


    /**
     * Start dragging a container item.
     */
    @Inject(
            method = "mouseClicked",
            at = @At("HEAD")
    )
    private void startDraggingContainer(
            double mouseX,
            double mouseY,
            int button,
            CallbackInfoReturnable<Boolean> cir) {
        AbstractContainerScreen<?> self = (AbstractContainerScreen<?>) (Object) this;
        if (ContainerItemSlotDragger.of(self.getMenu().getCarried()) != null) {
            invexp_$currentSlot = self.getSlotUnderMouse();
            invexp$hasMoved = false;
        }
    }

    /**
     * Handle hovering when dragging a container item.
     */
    @Inject(
            method = "mouseDragged",
            at = @At("HEAD"),
            cancellable = true
    )
    private void dragContainer(
            double mouseX,
            double mouseY,
            int button,
            double deltaX,
            double deltaY,
            CallbackInfoReturnable<Boolean> cir) {
        AbstractContainerScreen<?> self = (AbstractContainerScreen<?>) (Object) this;
        // Vanilla checks for dragging
        if (self.quickCraftingType == 2 ||
                !isQuickCrafting ||
                clickedSlot != null ||
                self.getMinecraft() == null ||
                self.getMinecraft().options.touchscreen().get()) {
            return;
        }

        // Make sure there is a valid slot
        Slot slot = self.getSlotUnderMouse();
        if (slot == null) {
            return;
        }

        // Check that there is a valid dragger
        ItemStack cursorStack = self.getMenu().getCarried();
        ContainerItemSlotDragger dragger = ContainerItemSlotDragger.of(cursorStack);
        if (dragger == null) {
            return;
        }

        // If current slot is not set, update current slot
        if (invexp_$currentSlot == null) {
            invexp_$currentSlot = slot;
        }
        else if (invexp_$currentSlot != slot) {
            if (!invexp$hasMoved) {
                invexp$hasMoved = true;
                quickCraftSlots.clear();
                dragger.onHover(invexp_$currentSlot, self);
            }
            invexp_$currentSlot = slot;
            dragger.onHover(invexp_$currentSlot, self);
        }
        cir.setReturnValue(true);
        cir.cancel();
    }

    /**
     * Reset values when stop dragging container
     */
    @Inject(
            method = "mouseReleased",
            at = @At("HEAD"),
            cancellable = true
    )
    private void finishDraggingContainer(
            double mouseX,
            double mouseY,
            int button,
            CallbackInfoReturnable<Boolean> cir) {
        AbstractContainerScreen<?> self = (AbstractContainerScreen<?>) (Object) this;
        if (invexp$hasMoved && ContainerItemSlotDragger.of(self.getMenu().getCarried()) != null) {
            invexp$hasMoved = false;
            invexp_$currentSlot = null;
            isQuickCrafting = false;
            cir.setReturnValue(true);
            cir.cancel();
        }
    }

    @Inject(
            method = "renderTooltip",
            at = @At("HEAD"),
            cancellable = true
    )
    private void renderContainerTooltip(GuiGraphics guiGraphics, int p_282171_, int p_281909_, CallbackInfo ci) {
        AbstractContainerScreen<?> self = (AbstractContainerScreen<?>) (Object) this;
        Slot hoveredSlot = self.getSlotUnderMouse();
        if (!self.getMenu().getCarried().isEmpty() && hoveredSlot != null && hoveredSlot.hasItem()) {
            ItemStack hoverStack = hoveredSlot.getItem();
            Item item = hoverStack.getItem();
            if (item instanceof BundleItem || item instanceof DyeableSackItem || item instanceof QuiverItem) {
                guiGraphics.renderTooltip(self.font, getTooltipFromContainerItem(hoverStack), hoverStack.getTooltipImage(), hoverStack, p_282171_, p_281909_);
                ci.cancel();
            }
        }
    }

    @Inject(
            method = "render",
            at = @At("HEAD")
    )
    private void saveMousePos(GuiGraphics guiGraphics, int x, int y, float delta, CallbackInfo ci) {
        invexp$mouseX = x;
        invexp$mouseY = y;
    }

    @Inject(
            method = "renderSlot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;renderItem(Lnet/minecraft/world/item/ItemStack;III)V"
            )
    )
    private void drawOpenSack(GuiGraphics context, Slot slot, CallbackInfo ci) {
        invexp_$openContents = null;
        AbstractContainerScreen<?> self = (AbstractContainerScreen<?>) (Object) this;
        if (!slot.hasItem() ||
                !isHovering(slot, invexp$mouseX, invexp$mouseY) ||
                self.getMinecraft() == null ||
                !self.getMenu().canDragTo(slot)) {
            return;
        }
        ItemStack cursorStack = self.getMenu().getCarried();
        SackContentsReader contents = SackContents.of(slot.getItem());
        if (contents == null || contents.isEmpty() || (!cursorStack.isEmpty() && !contents.canTryInsert(cursorStack))) {
            return;
        }
        invexp_$openContents = contents;
        Player player = self.getMinecraft().player;
        Level level = player != null ? player.level() : null;
        OpenItemTextures.renderOpenItem(context, slot.getItem(), slot.x, slot.y, level, player, slot.x + slot.y * imageWidth);
    }

    @ModifyArg(
            method = "renderSlot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;renderItem(Lnet/minecraft/world/item/ItemStack;III)V")
    )
    private ItemStack renderSelectedItem(ItemStack stack) {
        if (invexp_$openContents != null) {
            ItemStack selectedStack = invexp_$openContents.getSelectedStack();
            invexp_$openContents = null;
            return selectedStack;
        }
        else {
            return stack;
        }
    }

    @Inject(
            method = "render",
            at = @At("RETURN")
    )
    private void trackBundleSlot(GuiGraphics guiGraphics, int x, int y, float delta, CallbackInfo ci) {
        invexp_$setHoveredSlot(hoveredSlot);
    }

    @Inject(
            method = "onClose",
            at = @At("HEAD")
    )
    private void closeBundle(CallbackInfo ci) {
        invexp_$setHoveredSlot(null);
    }

    @Unique
    private void invexp_$setHoveredSlot(@Nullable Slot newHoveredSlot) {
        if (newHoveredSlot != invexp_$hoveredBundleSlot) {
            if (invexp_$hoveredBundleSlot != null) {
                BundleContents contents = BundleContents.of(invexp_$hoveredBundleSlot.getItem());
                if (contents != null && !contents.isEmpty() && contents.getSelectedIndex() != -1) {
                    contents.setSelectedIndex(-1);
                    AbstractContainerScreen<?> self = (AbstractContainerScreen<?>) (Object) this;
                    Slot trueSlot = InvExpClientUtil.getTrueSlot(invexp_$hoveredBundleSlot, self.getMinecraft().player);
                    if (trueSlot != null) {
                        InvExpPacketHandler.INSTANCE.sendToServer(new SetSelectedIndexPacket(trueSlot.index, -1));
                    }
                }
            }

            if (newHoveredSlot != null && newHoveredSlot.getItem().getItem() instanceof BundleItem) {
                invexp_$hoveredBundleSlot = newHoveredSlot;
            }
            else {
                invexp_$hoveredBundleSlot = null;
            }
        }
    }
}
