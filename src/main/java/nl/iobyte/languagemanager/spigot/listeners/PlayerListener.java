package nl.iobyte.languagemanager.spigot.listeners;

import nl.iobyte.languagemanager.LanguageManager;
import nl.iobyte.languagemanager.generic.util.Color;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        LanguageManager manager = LanguageManager.getInstance();
        manager.getInvoker().getScheduler().runAsync(() -> {
            List<Map<String, Object>> results = manager.getDatabaseService().executeQuery(
                    "local",
                    "SELECT language_id FROM preferences WHERE uuid=?",
                    new HashMap<>(){{
                        put(1, uuid.toString());
                    }}
            );

            if(results == null)
                return;

            for(Map<String, Object> map : results) {
                manager.getPlayerService()
                        .addPreference(
                                uuid,
                                (String) map.get("language_id")
                        );

                String message = LanguageManager.getInstance()
                        .getMessageService()
                        .getMessage(
                                "LANGUAGE",
                                (String) map.get("language_id"),
                                "loaded"
                        );
                e.getPlayer().sendMessage(Color.parse(message));
                break;
            }
        });
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent e) {
        LanguageManager.getInstance()
                .getPlayerService()
                .removePreference(
                        e.getPlayer().getUniqueId()
                );
    }

}
