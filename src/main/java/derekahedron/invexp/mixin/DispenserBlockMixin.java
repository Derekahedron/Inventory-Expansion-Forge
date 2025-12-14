package derekahedron.invexp.mixin;

import derekahedron.invexp.util.ContainerItemContents;
import derekahedron.invexp.util.ContainerItemContentsReader;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSourceImpl;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(DispenserBlock.class)
public class DispenserBlockMixin {

    /**
     * Dispensers will randomly select a non-empty item to try to dispense.
     * We do not want dispensers to dispense empty sacks and quivers, so if one is selected,
     * we re-roll except this time avoiding empty containers. This is done after the initial roll
     * to avoid modifying the vanilla code unless necessary.
     */
    @ModifyVariable(
            method = "dispenseFrom",
            at = @At("STORE"),
            ordinal = 0
    )
    private int avoidEmptyContainers(int slot, ServerLevel world, BlockPos pos) {
        if (slot == -1) {
            return slot;
        }
        BlockSourceImpl blocksourceimpl = new BlockSourceImpl(world, pos);
        DispenserBlockEntity dispenser = blocksourceimpl.getEntity();
        ContainerItemContentsReader contents = ContainerItemContents.of(dispenser.getItem(slot));
        // Only recalculate if the selected item has contents but is not empty
        if (contents != null && contents.isEmpty()) {
            slot = -1;
            int tries = 0;
            for (int i = 0; i < dispenser.getItems().size(); i++) {
                ItemStack stack = dispenser.getItem(i);
                contents = ContainerItemContents.of(stack);
                // Use vanilla logic for getting random slot, only this time avoiding empty contents
                if (!stack.isEmpty() && (contents == null || !contents.isEmpty())) {
                    tries++;
                    if (world.random.nextInt(tries++) == 0) {
                        slot = i;
                    }
                }
            }
        }
        return slot;
    }
}
