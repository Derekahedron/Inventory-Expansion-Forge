package derekahedron.invexp.mixin;

import derekahedron.invexp.sack.SackContents;
import derekahedron.invexp.sack.SackItemPredicate;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(InventoryChangeTrigger.TriggerInstance.class)
public class InventoryChangeTriggerTriggerInstanceMixin {

    @Shadow
    @Final
    private ItemPredicate[] predicates;

    /**
     * Modify the predicate list so all predicates also test sack contents
     */
    @ModifyVariable(
            method = "matches",
            at = @At("STORE")
    )
    private @NotNull List<ItemPredicate> modifyPredicateList(@NotNull List<ItemPredicate> list) {
        List<ItemPredicate> modified = new ObjectArrayList<>(list.size());
        for (ItemPredicate predicate : list) {
            modified.add(new SackItemPredicate(predicate));
        }
        return modified;
    }

    /**
     * Modify the item to be tested to a nested item that will pass the test
     */
    @ModifyArg(
            method = "matches",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/advancements/critereon/ItemPredicate;matches(Lnet/minecraft/world/item/ItemStack;)Z"
            ),
            index = 0
    )
    private ItemStack changeTestItem(ItemStack stack) {
        SackContents contents = SackContents.of(stack);
        if (contents != null && !contents.isEmpty()) {
            for (ItemStack nestedStack : contents.getStacks()) {
                if (predicates[0].matches(nestedStack)) {
                    return nestedStack;
                }
            }
        }
        return stack;
    }
}
