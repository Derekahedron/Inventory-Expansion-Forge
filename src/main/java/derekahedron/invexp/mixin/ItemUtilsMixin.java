package derekahedron.invexp.mixin;

import derekahedron.invexp.entity.player.PlayerEntityDuck;
import derekahedron.invexp.sack.SackContents;
import derekahedron.invexp.sack.SackUsage;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemUtils.class)
public class ItemUtilsMixin {

    @Inject(
            method = "createFilledResult(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;Z)Lnet/minecraft/world/item/ItemStack;",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void exchange(
            ItemStack inputStack, @NotNull Player player, ItemStack outputStack, boolean creativeOverride,
            @NotNull CallbackInfoReturnable<ItemStack> cir
    ) {
        if (player.isCreative()) {
            if (creativeOverride) {
                for (List<ItemStack> stacks : player.getInventory().compartments) {
                    for (ItemStack stack : stacks) {
                        SackContents contents = SackContents.of(stack);
                        if (contents != null && !contents.isEmpty()) {
                            for (ItemStack nestedStack : contents.getStacks()) {
                                if (!nestedStack.isEmpty() && ItemStack.isSameItem(nestedStack, outputStack)) {
                                    cir.setReturnValue(inputStack);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            return;
        }

        if (inputStack.getCount() <= 1) {
            return;
        }
        SackUsage usage = ((PlayerEntityDuck) player).invexp$getUsageForSelectedStack(inputStack);
        if (usage == null || !ItemStack.isSameItemSameTags(usage.originalSelectedStack, usage.selectedStack) || usage.originalSelectedStack.getCount() <= 1) {
            return;
        }
        SackContents contents = SackContents.of(usage.sackStack);
        if (contents == null || contents.isEmpty()) {
            return;
        }

        if (contents.remove(usage.selectedStack.copyWithCount(1)) == 0) {
            return;
        }
        usage.originalSelectedStack.shrink(1);
        inputStack.shrink(1);
        contents.add(outputStack);
        if (!outputStack.isEmpty() && !player.getInventory().add(outputStack)) {
            player.drop(outputStack, false);
        }
        cir.setReturnValue(inputStack);
    }
}
