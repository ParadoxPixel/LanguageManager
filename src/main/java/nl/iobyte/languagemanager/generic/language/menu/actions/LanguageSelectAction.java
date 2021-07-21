package nl.iobyte.languagemanager.generic.language.menu.actions;

import nl.iobyte.languagemanager.LanguageManager;
import nl.iobyte.languagemanager.generic.language.setting.objects.LanguageSetting;
import nl.iobyte.languagemanager.generic.menu.action.MenuAction;
import nl.iobyte.languagemanager.generic.util.Color;
import org.bukkit.entity.Player;
import java.util.HashMap;

public class LanguageSelectAction extends MenuAction {

    private final String id;

    public LanguageSelectAction(String id) {
        this.id = id;
    }

    /**
     * Execute on player click
     * @param player Player
     */
    public void execute(Player player) {
        player.closeInventory();
        LanguageManager.getInstance().getLanguageSettingService().getOptionalSetting(id).ifPresent(newLanguage -> {
            String old_id = LanguageManager.getInstance()
                    .getPlayerService()
                    .addPreference(
                            player.getUniqueId(),
                            id
                    );

            if(newLanguage.getID().equals(old_id))
                return;

            LanguageSetting oldLanguage = null;
            if(old_id != null)
                oldLanguage = LanguageManager.getInstance().getLanguageSettingService().getSetting(old_id);

            String path = oldLanguage == null ? "changed.V1" : "changed.V2";
            String message = LanguageManager.getInstance().getMessageService().getMessage("LANGUAGE", newLanguage.getID(), path);
            message = message.replace("%NEW%", newLanguage.getName());
            if(oldLanguage != null)
                message = message.replace("%OLD%", oldLanguage.getName());

            player.sendMessage(Color.parse(message));
            LanguageManager.getInstance().getInvoker().getScheduler().runAsync(() -> {
                boolean b = LanguageManager.getInstance().getDatabaseService().execute(
                        "local",
                        "INSERT OR IGNORE INTO preferences(uuid, language_id) VALUES (?,?)",
                        new HashMap<>(){{
                            put(1, player.getUniqueId().toString());
                            put(2, newLanguage.getID());
                        }}
                );
                if(!b)
                    LanguageManager.getInstance().getDatabaseService().executeUpdate(
                            "local",
                            "UPDATE preferences SET language_id=? WHERE uuid=?",
                            new HashMap<>(){{
                                put(1, newLanguage.getID());
                                put(2, player.getUniqueId().toString());
                            }}
                    );
            });
        });
    }

}
