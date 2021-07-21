package nl.iobyte.languagemanager.generic.language.setting;

import nl.iobyte.languagemanager.generic.language.setting.objects.LanguageSetting;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LanguageSettingService {

    private final Map<String, LanguageSetting> languages = new ConcurrentHashMap<>();

    /**
     * Get settings
     * @return Map<String, LanguageSetting>
     */
    public Map<String, LanguageSetting> getSettings() {
        return languages;
    }

    /**
     * Add language to service
     * @param setting ILanguage
     */
    public void addSetting(LanguageSetting setting) {
        languages.put(setting.getID(), setting);
    }

    /**
     * Check if service has language
     * @param id String
     * @return Boolean
     */
    public boolean hasSetting(String id) {
        return languages.containsKey(id);
    }

    /**
     * Get language from identifier
     * @param id String
     * @return LanguageSetting
     */
    public LanguageSetting getSetting(String id) {
        return languages.get(id);
    }

    /**
     * Get optional language from identifier
     * @param id String
     * @return Optional<LanguageSetting>
     */
    public Optional<LanguageSetting> getOptionalSetting(String id) {
        return Optional.ofNullable(getSetting(id));
    }

    /**
     * Remove language from service
     * @param id String
     * @return LanguageSetting
     */
    public LanguageSetting removeSetting(String id) {
        return languages.remove(id);
    }

}
