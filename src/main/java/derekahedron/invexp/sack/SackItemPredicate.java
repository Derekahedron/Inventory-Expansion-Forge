package derekahedron.invexp.sack;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.item.ItemStack;

public class SackItemPredicate extends ItemPredicate {
    ItemPredicate predicate;

    public SackItemPredicate(ItemPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean matches(ItemStack stack) {
        if (predicate.matches(stack)) {
            return true;
        }
        SackContentsReader contents = SackContents.of(stack);
        if (contents != null && !contents.isEmpty()) {
            for (ItemStack nestedStack : contents.getStacks()) {
                if (nestedStack.isEmpty() && predicate.matches(nestedStack)) {
                    return true;
                }
            }
        }
        return false;
    }
}
