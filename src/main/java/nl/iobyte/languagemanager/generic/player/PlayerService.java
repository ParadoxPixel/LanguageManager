package nl.iobyte.languagemanager.generic.player;

import nl.iobyte.languagemanager.LanguageManager;
import nl.iobyte.languagemanager.generic.language.interfaces.ILanguage;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerService {

    private final Map<UUID, String> preferences = new ConcurrentHashMap<>();

    /**
     * Register preferred language for player
     * @param uuid UUID
     * @param str String
     * @return String
     */
    public String addPreference(UUID uuid, String str) {
        return preferences.put(uuid, str);
    }

    /**
     * Check if player has preferred language
     * @param uuid UUID
     * @return Boolean
     */
    public boolean hasPreference(UUID uuid) {
        return preferences.containsKey(uuid);
    }

    /**
     * Get preferred language for player
     * @param uuid UUID
     * @return String
     */
    public String getPreference(UUID uuid) {
        return preferences.get(uuid);
    }

    /**
     * Get preferred language for player
     * @param uuid UUID
     * @return ILanguage
     */
    public ILanguage getPreferredLanguage(UUID uuid) {
        return LanguageManager.getInstance()
                .getLanguageService()
                .getOrDefault(
                        preferences.get(uuid)
                );
    }

    /**
     * Unregister preference of player
     * @param uuid UUID
     * @return String
     */
    public String removePreference(UUID uuid) {
        return preferences.remove(uuid);
    }

}
