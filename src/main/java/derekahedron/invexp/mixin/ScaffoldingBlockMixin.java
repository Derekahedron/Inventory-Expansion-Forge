package derekahedron.invexp.mixin;

import derekahedron.invexp.sack.SackContents;
import derekahedron.invexp.sack.SackContentsReader;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.ScaffoldingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ScaffoldingBlock.class)
public class ScaffoldingBlockMixin {

    /**
     * Scaffolding has a different outline shape if you are holding a scaffolding block.
     * We want to also display that shape if you are holding a sack with scaffolding as a selected stack.
     */
    @Inject(
            method = "getShape",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getOutlineShapeForSack(
            BlockState state, BlockGetter world, BlockPos pos, CollisionContext context,
            CallbackInfoReturnable<VoxelShape> cir
    ) {
        if (context instanceof EntityCollisionContext entityContext) {
            SackContentsReader contents = SackContents.of(entityContext.heldItem);
            if (contents != null && !contents.isEmpty() && contents.getSelectedStack().is(state.getBlock().asItem())) {
                cir.setReturnValue(Shapes.block());
            }
        }
    }
}
