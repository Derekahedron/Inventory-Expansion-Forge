package derekahedron.invexp.block.dispenser;

import derekahedron.invexp.quiver.QuiverContents;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;

/**
 * Dispenser Behavior for Quiver that allow sacks to dispense their selected arrow
 */
public class QuiverDispenserBehavior extends OptionalDispenseItemBehavior {
    public static final DefaultDispenseItemBehavior defaultBehavior = new DefaultDispenseItemBehavior();

    /**
     * Dispenser behavior for quivers
     *
     * @param pointer Pointer to the dispenser
     * @param stack Quiver stack that is being dispensed
     * @return Quiver stack after dispense
     */
    @Override
    public final ItemStack dispense(BlockSource pointer, ItemStack stack) {
        QuiverContents contents = QuiverContents.of(stack);
        // Fail dispense if invalid or empty
        if (contents == null || contents.isEmpty()) {
            setSuccess(false);
            playSound(pointer);
            playAnimation(pointer, pointer.getBlockState().getValue(DispenserBlock.FACING));
            return stack;
        }

        ItemStack selectedStack = contents.getSelectedStack().copy();
        DispenseItemBehavior behavior = DispenserBlock.DISPENSER_REGISTRY.getOrDefault(selectedStack.getItem(), null);
        if (behavior instanceof AbstractProjectileDispenseBehavior projectileBehavior) {
            // Use projectile behavior if it exists
            selectedStack = projectileBehavior.dispense(pointer, selectedStack);
        }
        else {
            // If, somehow, the quiver has an item without a projectile behavior, dispense
            // regularly
            selectedStack = super.dispense(pointer, selectedStack);
        }

        // Update selected stack and try to add remainder back into quiver
        contents.updateSelectedStack(selectedStack, (itemStack -> {
            contents.add(itemStack);
            if (!itemStack.isEmpty()) {
                if (((DispenserBlockEntity) pointer.getEntity()).addItem(itemStack) < 0) {
                    defaultBehavior.dispense(pointer, itemStack);
                }
            }
        }));
        return stack;
    }
}
