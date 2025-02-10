package derekahedron.invexp.mixin.client;

import derekahedron.invexp.bundle.BundleContents;
import derekahedron.invexp.client.util.OpenItemTextures;
import derekahedron.invexp.item.BetterBundleItem;
import derekahedron.invexp.sack.SackContents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {

    @Shadow @Final
    public Minecraft minecraft;

    /**
     * Draw cooldown progress for the selected stack if it exists, otherwise draw for the
     * original stack.
     */
    @ModifyVariable(
            method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
            at = @At("STORE"),
            ordinal = 0
    )
    private float drawCooldownProgressForSelectedStack(float f, Font font, ItemStack stack, int p_282641_, int p_282146_, @Nullable String p_282803_) {
        if (minecraft.player == null) {
            return f;
        }
        SackContents contents = SackContents.of(stack);
        if (contents == null || contents.isEmpty()) {
            return f;
        }
        return minecraft.player.getCooldowns().getCooldownPercent(contents.getSelectedStack().getItem(), minecraft.getFrameTime());
    }

    @ModifyVariable(
            method = "renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;IIII)V",
            at = @At("HEAD"),
            argsOnly = true
    )
    private ItemStack renderOpenBundle(ItemStack bundleStack, @Nullable LivingEntity entity, @Nullable Level level, ItemStack stack, int x, int y, int seed, int z) {
        if (!stack.is(Items.BUNDLE) && !(stack.getItem() instanceof BetterBundleItem)) {
            return bundleStack;
        }
        BundleContents contents = BundleContents.of(bundleStack);
        if (contents == null || contents.isEmpty() || contents.getSelectedIndex() == -1) {
            return bundleStack;
        }

        GuiGraphics self = (GuiGraphics) (Object) this;
        OpenItemTextures.renderOpenItem(self, bundleStack, x, y, level, entity, seed);
        return contents.getSelectedStack();
    }
}
