package derekahedron.invexp.mixin;

import com.mojang.datafixers.DataFixer;
import derekahedron.invexp.sack.SackDataManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.Services;
import net.minecraft.server.WorldStem;
import net.minecraft.server.level.progress.ChunkProgressListenerFactory;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.Proxy;
import java.util.function.Consumer;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    /**
     * After a server is made, create a new insertable manager from the registries and mark
     * DataPackChangeDetector as dirty.
     */
    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void initialPostDataPackLoad(
            Thread p_236723_, LevelStorageSource.LevelStorageAccess p_236724_, PackRepository p_236725_, WorldStem p_236726_, Proxy p_236727_, DataFixer p_236728_, Services p_236729_, ChunkProgressListenerFactory p_236730_, @NotNull CallbackInfo ci
    ) {
        MinecraftServer self = (MinecraftServer) (Object) this;
        SackDataManager.createNewInstance(self.registryAccess());
    }

    /**
     * After data packs are reloaded, mark the change detector as dirty.
     */
    @ModifyArg(
            method = "reloadResources",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/concurrent/CompletableFuture;thenAcceptAsync(Ljava/util/function/Consumer;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;"
            )
    )
    private @NotNull Consumer<Object> detectDataPackReload(Consumer<Object> consumer) {
        return (var) -> {
            consumer.accept(var);
            SackDataManager.updateInstanceTaggedData();
        };
    }
}
