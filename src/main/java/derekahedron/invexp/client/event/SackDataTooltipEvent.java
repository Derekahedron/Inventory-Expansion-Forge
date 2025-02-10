package derekahedron.invexp.client.event;

import derekahedron.invexp.InventoryExpansion;
import derekahedron.invexp.client.ClientConfig;
import derekahedron.invexp.client.gui.tooltip.ClientSackTooltip;
import derekahedron.invexp.sack.SackDataManager;
import derekahedron.invexp.sack.SacksHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = InventoryExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class SackDataTooltipEvent {
    public static final Component SACK_DATA_TOOLTIP = Component.translatable("item.invexp.sack.data_tooltip").withStyle(ChatFormatting.DARK_GRAY);

    @SubscribeEvent
    public static void onItemTooltip(@NotNull ItemTooltipEvent event) {
        if (ClientConfig.displaySackDataInTooltip && event.getFlags().isAdvanced() && SackDataManager.getInstance() != null) {
            ItemStack stack = event.getItemStack();
            String sackType = SacksHelper.getSackType(stack);
            if (sackType != null) {
                event.getToolTip().add(SACK_DATA_TOOLTIP);
                event.getToolTip().add(Component.literal(sackType).withStyle(ChatFormatting.DARK_GRAY));
                int sackWeight = SacksHelper.getSackWeight(stack);
                if (sackWeight != SacksHelper.DEFAULT_SACK_WEIGHT) {
                    event.getToolTip().add(ClientSackTooltip.formatWeight(sackWeight).withStyle(ChatFormatting.DARK_GRAY));
                }
            }
        }
    }
}
