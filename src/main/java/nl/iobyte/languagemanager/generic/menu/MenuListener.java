package nl.iobyte.languagemanager.generic.menu;

import nl.iobyte.languagemanager.generic.menu.action.InteractAction;
import nl.iobyte.languagemanager.generic.menu.interfaces.IMenu;
import nl.iobyte.languagemanager.generic.menu.multi.MenuPage;
import nl.iobyte.languagemanager.generic.menu.multi.MultiMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getClickedInventory() == null)
            return;

        Inventory inventory = e.getClickedInventory();
        if(!(inventory.getHolder() instanceof IMenu menu))
            return;

        if(e.isCancelled())
            return;

        Player player = (Player) e.getWhoClicked();
        int slot = e.getRawSlot();
        ClickType type = e.getClick();

        e.setCancelled(menu.execute(player, slot, type));
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();
        Inventory inventory = e.getInventory();
        if(!(inventory.getHolder() instanceof IMenu))
            return;

        if(!(inventory.getHolder() instanceof MenuPage page)) {
            IMenu menu = (IMenu) inventory.getHolder();
            InteractAction open = menu.getOpenAction();
            if (open == null)
                return;

            open.execute(player);
            e.setCancelled(open.doCancel());
        } else {
            MultiMenu menu = page.getParent();
            if(!menu.isActivePage(player.getUniqueId(), page))
                return;

            InteractAction open = menu.getOpenAction();
            if (open == null)
                return;

            open.execute(player);
            e.setCancelled(open.doCancel());
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        Inventory inventory = e.getInventory();
        if(!(inventory.getHolder() instanceof IMenu))
            return;

        if(!(inventory.getHolder() instanceof MenuPage page)) {
            IMenu menu = (IMenu) inventory.getHolder();
            InteractAction close = menu.getCloseAction();
            if(close == null)
                return;

            close.execute(player);
            if(close.doCancel())
                menu.open(player);
        } else {
            MultiMenu menu = page.getParent();
            if(!menu.isActivePage(player.getUniqueId(), page))
                return;

            InteractAction close = menu.getCloseAction();
            if (close == null)
                return;

            close.execute(player);
            if(close.doCancel()) {
                page.open(player);
            } else {
                menu.close(player);
            }
        }
    }

}
