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
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
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
    private Slot invexp$currentSlot;
    @Unique
    private boolean invexp$hasMoved;
    @Unique
    private SackContents invexp$openContents;
    @Unique
    private int invexp$mouseX;
    @Unique
    private int invexp$mouseY;
    @Unique
    private Slot invexp$hoveredBundleSlot;


    /**
     * Start dragging a container item.
     */
    @Inject(
            method = "mouseClicked",
            at = @At("HEAD")
    )
    private void startDraggingContainer(
            double mouseX, double mouseY, int button, @NotNull CallbackInfoReturnable<Boolean> cir
    ) {
        AbstractContainerScreen<?> self = (AbstractContainerScreen<?>) (Object) this;
        if (ContainerItemSlotDragger.of(self.getMenu().getCarried()) != null) {
            invexp$currentSlot = self.getSlotUnderMouse();
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
            double mouseX, double mouseY, int button, double deltaX, double deltaY,
            @NotNull CallbackInfoReturnable<Boolean> cir
    ) {
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
        if (invexp$currentSlot == null) {
            invexp$currentSlot = slot;
        }
        else if (invexp$currentSlot != slot) {
            if (!invexp$hasMoved) {
                invexp$hasMoved = true;
                quickCraftSlots.clear();
                dragger.onHover(invexp$currentSlot, self);
            }
            invexp$currentSlot = slot;
            dragger.onHover(invexp$currentSlot, self);
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
            double mouseX, double mouseY, int button, @NotNull CallbackInfoReturnable<Boolean> cir
    ) {
        AbstractContainerScreen<?> self = (AbstractContainerScreen<?>) (Object) this;
        if (invexp$hasMoved && ContainerItemSlotDragger.of(self.getMenu().getCarried()) != null) {
            invexp$hasMoved = false;
            invexp$currentSlot = null;
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
    private void drawOpenSack(GuiGraphics context, @NotNull Slot slot, @NotNull CallbackInfo ci) {
        invexp$openContents = null;
        AbstractContainerScreen<?> self = (AbstractContainerScreen<?>) (Object) this;
        if (!slot.hasItem() ||
                !isHovering(slot, invexp$mouseX, invexp$mouseY) ||
                self.getMinecraft() == null ||
                !self.getMenu().canDragTo(slot)) {
            return;
        }
        ItemStack cursorStack = self.getMenu().getCarried();
        SackContents contents = SackContents.of(slot.getItem());
        if (contents == null || contents.isEmpty() || (!cursorStack.isEmpty() && !contents.canTryInsert(cursorStack))) {
            return;
        }
        invexp$openContents = contents;
        Player player = self.getMinecraft().player;
        Level level = player != null ? player.level() : null;
        OpenItemTextures.renderOpenItem(context, slot.getItem(), slot.x, slot.y, level, player, slot.x + slot.y * imageWidth);
    }

    @ModifyArg(
            method = "renderSlot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;renderItem(Lnet/minecraft/world/item/ItemStack;III)V"
            )
    )
    private ItemStack renderSelectedItem(ItemStack stack) {
        if (invexp$openContents != null) {
            ItemStack selectedStack = invexp$openContents.getSelectedStack();
            invexp$openContents = null;
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
        invexp$setHoveredSlot(hoveredSlot);
    }

    @Inject(
            method = "onClose",
            at = @At("HEAD")
    )
    private void closeBundle(CallbackInfo ci) {
        invexp$setHoveredSlot(null);
    }

    @Unique
    private void invexp$setHoveredSlot(Slot newHoveredSlot) {
        if (newHoveredSlot != invexp$hoveredBundleSlot) {
            if (invexp$hoveredBundleSlot != null) {
                BundleContents contents = BundleContents.of(invexp$hoveredBundleSlot.getItem());
                if (contents != null && !contents.isEmpty() && contents.getSelectedIndex() != -1) {
                    contents.setSelectedIndex(-1);
                    AbstractContainerScreen<?> self = (AbstractContainerScreen<?>) (Object) this;
                    Slot trueSlot = InvExpClientUtil.getTrueSlot(invexp$hoveredBundleSlot, self.getMinecraft().player);
                    if (trueSlot != null) {
                        InvExpPacketHandler.INSTANCE.sendToServer(new SetSelectedIndexPacket(trueSlot.index, -1));
                    }
                }
            }

            if (newHoveredSlot != null && newHoveredSlot.getItem().getItem() instanceof BundleItem) {
                invexp$hoveredBundleSlot = newHoveredSlot;
            }
            else {
                invexp$hoveredBundleSlot = null;
            }
        }
    }

}
