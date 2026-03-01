package derekahedron.invexp.datagen.compat;

import derekahedron.invexp.datagen.InvExpItemTagProvider;
import derekahedron.invexp.item.InvExpItemTags;
import derekahedron.invexp.util.CompatUtil;

public class StorageDrawersItemTagsProvider extends CompatibilityItemTagsProvider {

    public StorageDrawersItemTagsProvider(InvExpItemTagProvider provider) {
        super(provider);
    }

    @Override
    public void makeTags() {
        tag(InvExpItemTags.SackType.KEY)
                .addOptional(CompatUtil.storageDrawers("drawer_key"))
                .addOptional(CompatUtil.storageDrawers("quantify_key"))
                .addOptional(CompatUtil.storageDrawers("shroud_key"))
                .addOptional(CompatUtil.storageDrawers("concealment_key")) // Old name
                .addOptional(CompatUtil.storageDrawers("personal_key"))
                .addOptional(CompatUtil.storageDrawers("personal_key_cofh"))
                .addOptional(CompatUtil.storageDrawers("personal_key_ftb"))
                .addOptional(CompatUtil.storageDrawers("personal_key_unlock"))
                .addOptional(CompatUtil.storageDrawers("suspend_key"))
                .addOptional(CompatUtil.storageDrawers("priority_key"));

        // Storage Drawers Sack Types
        tag(InvExpItemTags.SackType.DRAWER_UPGRADE)
                .addOptional(CompatUtil.storageDrawers("obsidian_storage_upgrade"))
                .addOptional(CompatUtil.storageDrawers("iron_storage_upgrade"))
                .addOptional(CompatUtil.storageDrawers("copper_storage_upgrade"))
                .addOptional(CompatUtil.storageDrawers("iron_storage_upgrade"))
                .addOptional(CompatUtil.storageDrawers("gold_storage_upgrade"))
                .addOptional(CompatUtil.storageDrawers("emerald_storage_upgrade"))
                .addOptional(CompatUtil.storageDrawers("diamond_storage_upgrade"))
                .addOptional(CompatUtil.storageDrawers("netherite_storage_upgrade"))
                .addOptional(CompatUtil.storageDrawers("one_stack_upgrade"))
                .addOptional(CompatUtil.storageDrawers("void_upgrade"))
                .addOptional(CompatUtil.storageDrawers("creative_storage_upgrade"))
                .addOptional(CompatUtil.storageDrawers("creative_vending_upgrade"))
                .addOptional(CompatUtil.storageDrawers("conversion_upgrade"))
                .addOptional(CompatUtil.storageDrawers("redstone_upgrade"))
                .addOptional(CompatUtil.storageDrawers("min_redstone_upgrade"))
                .addOptional(CompatUtil.storageDrawers("max_redstone_upgrade"))
                .addOptional(CompatUtil.storageDrawers("illumination_upgrade"))
                .addOptional(CompatUtil.storageDrawers("fill_level_upgrade"))
                .addOptional(CompatUtil.storageDrawers("balance_fill_upgrade"))
                .addOptional(CompatUtil.storageDrawers("portability_upgrade"))
                .addOptional(CompatUtil.storageDrawers("hopper_upgrade"))
                .addOptional(CompatUtil.storageDrawers("magnet_upgrade"))
                .addOptional(CompatUtil.storageDrawers("magnet_upgrade_2"))
                .addOptional(CompatUtil.storageDrawers("magnet_upgrade_3"))
                .addOptional(CompatUtil.storageDrawers("magnet_upgrade"))
                .addOptional(CompatUtil.storageDrawers("remote_upgrade"))
                .addOptional(CompatUtil.storageDrawers("remote_group_upgrade"))
                .addOptional(CompatUtil.storageDrawers("upgrade_template"));
        tag(InvExpItemTags.SackType.DRAWER_CONTROLLER)
                .addOptional(CompatUtil.storageDrawers("controller"))
                .addOptional(CompatUtil.storageDrawers("controller_io")) // Old name
                .addOptional(CompatUtil.storageDrawers("controller_slave"));
        tag(InvExpItemTags.SackType.KEY_BUTTON)
                .addOptional(CompatUtil.storageDrawers("keybutton_drawer"))
                .addOptional(CompatUtil.storageDrawers("keybutton_quantify"))
                .addOptional(CompatUtil.storageDrawers("keybutton_concealment"));
    }
}
