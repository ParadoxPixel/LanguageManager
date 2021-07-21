package nl.iobyte.languagemanager.generic.menu.interfaces;

import org.bukkit.entity.Player;

public interface LoadAction {

    /**
     * Run action for player in menu
     * @param menu IMenu
     * @param player Player
     */
    void run(IMenu menu, Player player);

}
