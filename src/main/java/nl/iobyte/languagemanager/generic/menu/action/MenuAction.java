package nl.iobyte.languagemanager.generic.menu.action;

import org.bukkit.entity.Player;

public abstract class MenuAction {

    /**
     * Execute Action for Player
     * @param player Player instance
     */
    public abstract void execute(Player player);

}
