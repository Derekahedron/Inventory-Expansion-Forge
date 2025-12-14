package derekahedron.invexp.mixin;

import derekahedron.invexp.item.ItemDuck;
import derekahedron.invexp.sack.SackDefaultManager;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Item.class)
public class ItemMixin implements ItemDuck {

    @Unique
    private SackDefaultManager.SackDefaults invexp$sackDefaults;

    @Override
    public void invexp$setSackDefaults(@Nullable SackDefaultManager.SackDefaults sackDefaults) {
        this.invexp$sackDefaults = sackDefaults;
    }

    @Override
    @Nullable
    public SackDefaultManager.SackDefaults invexp$getSackDefaults() {
        return invexp$sackDefaults;
    }
}
