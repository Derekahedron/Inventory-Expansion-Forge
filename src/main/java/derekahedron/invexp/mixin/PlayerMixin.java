package derekahedron.invexp.mixin;

import derekahedron.invexp.entity.player.PlayerEntityDuck;
import derekahedron.invexp.quiver.QuiverContents;
import derekahedron.invexp.sack.SackContents;
import derekahedron.invexp.sack.SackUsage;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.function.Predicate;

@Mixin(Player.class)
public abstract class PlayerMixin implements PlayerEntityDuck {

    private @Unique boolean invexp$usingSack = false;
    private @Unique SackUsage invexp$mainHandSackUsage;
    private @Unique SackUsage invexp$offHandSackUsage;

    /**
     * If the player is using a sack, getting the equipped stack should return the stack
     * in usage if applicable.
     */
    @Inject(
            method = "getItemBySlot",
            at = @At("RETURN"),
            cancellable = true
    )
    private void getEquippedStackInSack(EquipmentSlot slot, CallbackInfoReturnable<ItemStack> cir) {
        if (invexp$usingSack && (slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND)) {
            SackUsage usage = invexp$getUsageForSackStack(cir.getReturnValue());
            if (usage != null) {
                cir.setReturnValue(usage.selectedStack);
            }
        }
    }

    /**
     * If the player is using a sack, equipping the stack in that slot should set it to the
     * selected stack in the sack usage if applicable.
     */
    @Inject(
            method = "setItemSlot",
            at = @At("HEAD"),
            cancellable = true
    )
    private void equipStackInSack(EquipmentSlot slot, ItemStack stack, @NotNull CallbackInfo ci) {
        if (invexp$usingSack && (slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND)) {
            Player self = (Player) (Object) this;
            // Get the stack that is being held
            ItemStack heldStack;
            switch (slot) {
                case MAINHAND -> heldStack = self.getInventory().getSelected();
                case OFFHAND -> heldStack = self.getInventory().offhand.get(0);
                default -> throw new IllegalArgumentException("Invalid slot " + slot);
            }
            // If the stack in the slot is a sack being used, replace there instead
            SackUsage usage = invexp$getUsageForSackStack(heldStack);
            if (usage != null) {
                self.onEquipItem(slot, usage.selectedStack, stack);
                usage.selectedStack = stack;
                ci.cancel();
            }
        }
    }

    /**
     * Check if player is currently using sack.
     *
     * @return  true if the player is using a sack; false otherwise
     */
    @Override
    public boolean invexp$isUsingSack() {
        return invexp$usingSack;
    }

    /**
     * Start player using sack by creating usages for the items in the main hand and
     * offhand. Merge previous usages if they are of the same sack stack.
     */
    @Override
    public void invexp$startUsingSack() {
        if (invexp$usingSack) {
            invexp$stopUsingSack();
        }

        Player self = (Player) (Object) this;
        SackUsage[] usages = new SackUsage[InteractionHand.values().length];
        for (int i = 0; i < InteractionHand.values().length; i++) {
            ItemStack heldStack = self.getItemInHand(InteractionHand.values()[i]);
            SackContents contents = SackContents.of(heldStack);
            if (contents != null && !contents.isEmpty()) {
                SackUsage usage = invexp$getUsageForSackStack(heldStack);
                if (usage != null) {
                    usages[i] = new SackUsage(contents, usage.selectedStack);
                }
                else {
                    usages[i] = new SackUsage(contents);
                }
            }
        }

        for (int i = 0; i < InteractionHand.values().length; i++) {
            invexp$setUsageByHand(InteractionHand.values()[i], usages[i]);
        }

        invexp$usingSack = true;
    }

    /**
     * Stop player using sack. Updates sack usages with the new selected stacks.
     */
    @Override
    public void invexp$stopUsingSack() {
        if (!invexp$usingSack) {
            return;
        }

        ArrayList<ItemStack> leftoverStacks = new ArrayList<>();
        Player self = (Player) (Object) this;
        for (InteractionHand hand : InteractionHand.values()) {
            SackUsage usage = invexp$getUsageByHand(hand);
            if (usage != null) {
                usage.update(leftoverStacks::add);
            }
        }

        invexp$usingSack = false;
        for (ItemStack leftoverStack : leftoverStacks) {
            if (!leftoverStack.isEmpty() && !self.getInventory().add(leftoverStack)) {
                self.drop(leftoverStack, false);
            }
        }
    }

    /**
     * Gets the usage for the player with a sack stack matching the given stack.
     *
     * @param sackStack     sack stack to get usage for
     * @return              sack usage associated with the sack stack; null if there is none
     */
    @Override
    public SackUsage invexp$getUsageForSackStack(ItemStack sackStack) {
        for (InteractionHand hand : InteractionHand.values()) {
            SackUsage usage = invexp$getUsageByHand(hand);
            if (usage != null && usage.sackStack == sackStack) {
                return usage;
            }
        }
        return null;
    }


    /**
     * Gets the usage for the player with a selected stack matching the given stack.
     *
     * @param selectedStack     selected stack to get usage for
     * @return                  sack usage associated with the selected stack; null if there is none
     */
    @Override
    public SackUsage invexp$getUsageForSelectedStack(ItemStack selectedStack) {
        for (InteractionHand hand : InteractionHand.values()) {
            SackUsage usage = invexp$getUsageByHand(hand);
            if (usage != null && usage.selectedStack == selectedStack) {
                return usage;
            }
        }
        return null;
    }

    /**
     * Sets usage for the given hand.
     *
     * @param hand      hand to set the usage for
     * @param usage     usage to set in the hand
     */
    @Unique
    private void invexp$setUsageByHand(@NotNull InteractionHand hand, @Nullable SackUsage usage) {
        switch (hand) {
            case MAIN_HAND -> invexp$mainHandSackUsage = usage;
            case OFF_HAND -> invexp$offHandSackUsage = usage;
            default -> throw new IllegalArgumentException("Invalid hand " + hand);
        }
    }

    /**
     * Gets usage for the given hand.
     *
     * @param hand  hand to get the usage for
     * @return      usage in the given hand
     */
    @Unique
    private SackUsage invexp$getUsageByHand(@NotNull InteractionHand hand) {
        switch (hand) {
            case MAIN_HAND -> {
                return invexp$mainHandSackUsage;
            }
            case OFF_HAND -> {
                return invexp$offHandSackUsage;
            }
            default -> throw new IllegalArgumentException("Invalid hand " + hand);
        }
    }

    /**
     * Returns new QuiveredItemStack for a quiver with a selected item that matches the
     * given predicate.
     */
    @Inject(
            method = "getProjectile",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ProjectileWeaponItem;getAllSupportedProjectiles()Ljava/util/function/Predicate;"
            ),
            cancellable = true
    )
    private void getQuiveredProjectile(
            ItemStack stack, CallbackInfoReturnable<ItemStack> cir
    ) {
        Player self = (Player) (Object) this;
        Predicate<ItemStack> predicate = ((ProjectileWeaponItem) stack.getItem()).getAllSupportedProjectiles();
        for (int i = 0; i < self.getInventory().getContainerSize(); i++) {
            QuiverContents contents = QuiverContents.of(self.getInventory().getItem(i));
            if (contents != null) {
                ItemStack projectile = contents.getProjectileStack(predicate);
                if (!projectile.isEmpty()) {
                    cir.setReturnValue(ForgeHooks.getProjectile(self, stack, projectile));
                    return;
                }
            }
        }
    }
}
