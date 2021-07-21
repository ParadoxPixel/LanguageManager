package nl.iobyte.languagemanager.generic.menu;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class MenuAPI {

    private static boolean registered = false;

    /**
     * toSize multiple of 9
     * @param size
     * @return
     */
    public static int toSize(int size) {
        int left = size % 9;
        if(left == 0)
            return size;

        return 9 - left + size;
    }

    /**
     * Register MenuAPI Listener
     * @param plugin
     */
    public static void register(Plugin plugin) {
        if(registered || plugin == null)
            return;

        Bukkit.getPluginManager().registerEvents(new MenuListener(), plugin);
        registered = true;
    }

}
