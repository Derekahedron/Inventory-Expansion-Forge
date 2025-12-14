package derekahedron.invexp.client.event;

import derekahedron.invexp.InventoryExpansion;
import derekahedron.invexp.client.ClientConfig;
import derekahedron.invexp.client.gui.tooltip.ClientSackTooltip;
import derekahedron.invexp.sack.SackDefaultManager;
import derekahedron.invexp.sack.SackType;
import derekahedron.invexp.sack.SacksHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.math.Fraction;

@Mod.EventBusSubscriber(modid = InventoryExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class SackDataTooltipEvent {
    public static final Component SACK_DATA_TOOLTIP = Component.translatable("item.invexp.sack.data_tooltip").withStyle(ChatFormatting.DARK_GRAY);

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if (ClientConfig.displaySackDataInTooltip && event.getFlags().isAdvanced() && SackDefaultManager.getInstance() != null) {
            ItemStack stack = event.getItemStack();
            ResourceKey<SackType> sackType = SackDefaultManager.getInstance().getType(stack);
            if (sackType != null) {
                event.getToolTip().add(SACK_DATA_TOOLTIP);
                event.getToolTip().add(Component.literal(sackType.location().toString()).withStyle(ChatFormatting.DARK_GRAY));
                Fraction sackWeight = SacksHelper.getSackWeight(stack);
                if (sackWeight.multiplyBy(Fraction.getFraction(64)).compareTo(SacksHelper.DEFAULT_SACK_WEIGHT) != 0) {
                    event.getToolTip().add(ClientSackTooltip.formatWeight(sackWeight).withStyle(ChatFormatting.DARK_GRAY));
                }
            }
        }
    }
}
