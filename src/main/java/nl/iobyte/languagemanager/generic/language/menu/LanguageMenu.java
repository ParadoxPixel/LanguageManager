package nl.iobyte.languagemanager.generic.language.menu;

import nl.iobyte.languagemanager.generic.language.setting.objects.LanguageSetting;
import nl.iobyte.languagemanager.generic.menu.MenuAPI;
import nl.iobyte.languagemanager.generic.menu.basic.Menu;
import nl.iobyte.languagemanager.generic.menu.enums.Types;
import nl.iobyte.languagemanager.generic.menu.item.MenuItem;
import nl.iobyte.languagemanager.generic.language.menu.actions.LanguageSelectAction;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class LanguageMenu {

    private final Menu menu;

    public LanguageMenu(String title, int size) {
        menu = new Menu(title, MenuAPI.toSize(size));
    }

    /**
     * Add language to slot
     * @param slot Integer
     * @param setting LanguageSetting
     */
    public void add(int slot, LanguageSetting setting) {
        if(slot < 0 || slot >= menu.getSize())
            return;

        ItemStack item = setting.getSkull();
        menu.setItem(slot, new MenuItem(
                item,
                true
        ).addActions(
                Types.NORMAL.getTypesList(),
                new LanguageSelectAction(setting.getID())
        ));
        menu.updateItem(slot);
    }

    /**
     * Open the menu for a player
     * @param player Player
     */
    public void open(Player player) {
        menu.open(player);
    }

    /**
     * Build menu
     */
    public void build() {
        ItemStack stack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        Optional.ofNullable(stack.getItemMeta()).ifPresent(meta -> {
            meta.setDisplayName(" ");
            stack.setItemMeta(meta);
        });

        MenuItem item = new MenuItem(
                stack,
                true
        );

        for (int i = 0; i < menu.getSize(); i++)
            if (i < 9 || i > (menu.getSize() - 9) || i % 9 == 0 || (i + 1) % 9 == 0)
                menu.setItem(i, item);

        menu.build();
    }

}
