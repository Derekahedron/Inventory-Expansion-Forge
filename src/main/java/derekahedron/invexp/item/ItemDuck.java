package derekahedron.invexp.item;

import derekahedron.invexp.sack.SackDataManager;
import org.jetbrains.annotations.Nullable;

public interface ItemDuck {
    void invexp$setDefaultSackData(@Nullable SackDataManager.DefaultSackData defaultSackInsertable);
    SackDataManager.DefaultSackData invexp$getDefaultSackData();
}
