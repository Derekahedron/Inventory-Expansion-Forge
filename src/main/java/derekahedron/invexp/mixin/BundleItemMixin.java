package derekahedron.invexp.mixin;

import derekahedron.invexp.bundle.BundleContents;
import derekahedron.invexp.item.BetterBundleItem;
import derekahedron.invexp.item.tooltip.BetterBundleTooltip;
import derekahedron.invexp.util.InvExpUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;

@Mixin(BundleItem.class)
public abstract class BundleItemMixin {

    @Shadow protected abstract void playInsertSound(Entity entity);

    @Shadow protected abstract void playRemoveOneSound(Entity entity);

    /**
     * Injects and cancels better bundle behavior for stack clicked on other stack.
     */
    @Inject(
            method = "overrideStackedOnOther",
            at = @At("HEAD"),
            cancellable = true
    )
    private void betterOverrideStackedOnOther(
            ItemStack stack, Slot slot, ClickAction clickAction,
            Player player, @NotNull CallbackInfoReturnable<Boolean> cir
    ) {
        // Make sure this is actually a valid sack
        BundleContents contents = BundleContents.of(stack);
        if (contents == null) {
            cir.setReturnValue(false);
            return;
        }
        ItemStack otherStack = slot.getItem();
        if (clickAction == ClickAction.PRIMARY && !otherStack.isEmpty()) {
            if (!contents.canTryInsert(otherStack)) {
                // Don't do anything if the other stack does not match the types
                cir.setReturnValue(false);
                return;
            }
            else if (contents.add(slot, player) > 0) {
                // If added, play sound and update screen handler
                playInsertSound(player);
                InvExpUtil.onContentChanged(player);
            }
            cir.setReturnValue(true);
            return;
        }
        else if (clickAction == ClickAction.SECONDARY && otherStack.isEmpty()) {
            if (contents.popSelectedStack(slot)) {
                // If removed, play sound and update handler
                playRemoveOneSound(player);
                InvExpUtil.onContentChanged(player);
            }
            // Always return true so quiver stays in cursor slot
            cir.setReturnValue(true);
            return;
        }
        cir.setReturnValue(false);
    }

    /**
     * Injects and cancels better bundle behavior for other stack clicked on bundle.
     */
    @Inject(
            method = "overrideOtherStackedOnMe",
            at = @At("HEAD"),
            cancellable = true
    )
    private void betterOtherStackedOnMe(
            ItemStack bundleStack, ItemStack otherStack, Slot slot, ClickAction clickAction,
            Player player, SlotAccess slotAccess, @NotNull CallbackInfoReturnable<Boolean> cir
    ) {
        // Make sure this is actually a valid sack
        BundleContents contents = BundleContents.of(bundleStack);
        if (contents == null) {
            cir.setReturnValue(false);
            return;
        }

        if (clickAction == ClickAction.PRIMARY && !otherStack.isEmpty()) {
            if (!contents.canTryInsert(otherStack)) {
                // Don't do anything if the other stack does not match the types
                cir.setReturnValue(false);
                return;
            }
            else if (contents.add(otherStack) > 0) {
                // If added, play sound and update screen handler
                playInsertSound(player);
                InvExpUtil.onContentChanged(player);
            }
            cir.setReturnValue(true);
            return;
        }
        else if (clickAction == ClickAction.SECONDARY && otherStack.isEmpty()) {
            if (slot.allowModification(player)) {
                ItemStack poppedStack = contents.popSelectedStack();
                if (!poppedStack.isEmpty()) {
                    // If removed, play sound and update handler
                    slotAccess.set(poppedStack);
                    playRemoveOneSound(player);
                    InvExpUtil.onContentChanged(player);
                }
                // Always return true so quiver stays in cursor slot
                cir.setReturnValue(true);
                return;
            }
        }
        else {
            contents.setSelectedIndex(-1);
        }
        cir.setReturnValue(false);
    }

    /**
     * Injects and cancels better bundle behavior for getting the bar color.
     */
    @Inject(
            method = "getBarColor",
            at = @At("HEAD"),
            cancellable = true
    )
    private void betterGetBarColor(ItemStack bundleStack, @NotNull CallbackInfoReturnable<Integer> cir) {
        BundleContents contents = BundleContents.of(bundleStack);
        if (contents != null && contents.getTotalWeight() >= contents.getMaxBundleWeight()) {
            cir.setReturnValue(BetterBundleItem.FULL_ITEM_BAR_COLOR);
        }
    }

    @Inject(
            method = "getTooltipImage",
            at = @At("HEAD"),
            cancellable = true
    )
    private void betterGetTooltipImage(
            ItemStack bundleStack, @NotNull CallbackInfoReturnable<Optional<TooltipComponent>> cir
    ) {
        BundleContents contents = BundleContents.of(bundleStack);
        if (contents != null) {
            cir.setReturnValue(Optional.of(new BetterBundleTooltip(contents)));
        }
        else {
            cir.setReturnValue(Optional.empty());
        }
    }

    @Inject(
            method = "appendHoverText",
            at = @At("HEAD"),
            cancellable = true
    )
    private void betterAppendHoverText(
            ItemStack bundleStack, Level level, List<Component> components, TooltipFlag flag, @NotNull CallbackInfo ci
    ) {
        ci.cancel();
    }

    @Inject(
            method = "getWeight",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void getBetterWeight(@NotNull ItemStack stack, @NotNull CallbackInfoReturnable<Integer> cir) {
        if (stack.getItem() instanceof BetterBundleItem) {
            cir.setReturnValue(BundleItem.getContentWeight(stack) + 4);
        }
    }
}
