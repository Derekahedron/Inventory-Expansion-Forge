package derekahedron.invexp.mixin;

import derekahedron.invexp.item.ItemDuck;
import derekahedron.invexp.sack.SackDataManager;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Item.class)
public class ItemMixin implements ItemDuck {
    @Unique
    private SackDataManager.DefaultSackData invexp$defaultSackInsertable;

    @Override
    public void invexp$setDefaultSackData(@Nullable SackDataManager.DefaultSackData defaultSackInsertable) {
        invexp$defaultSackInsertable = defaultSackInsertable;
    }

    @Override
    public SackDataManager.DefaultSackData invexp$getDefaultSackData() {
        return invexp$defaultSackInsertable;
    }
}
