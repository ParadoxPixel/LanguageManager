package nl.iobyte.languagemanager.generic.language;

import nl.iobyte.languagemanager.generic.language.interfaces.ILanguage;
import nl.iobyte.languagemanager.generic.language.objects.NullLanguage;
import nl.iobyte.languagemanager.generic.language.menu.LanguageMenu;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LanguageService {

    private final Map<String, ILanguage> languages = new ConcurrentHashMap<>();
    private LanguageMenu menu;
    private String def;

    public LanguageMenu getMenu() {
        return menu;
    }

    public void setMenu(LanguageMenu menu) {
        this.menu = menu;
    }

    /**
     * Get identifier of default language
     * @return String
     */
    public String getDefault() {
        return def;
    }

    /**
     * Set identifier of default language
     * @param id String
     */
    public void setDefault(String id) {
        if(!hasLanguage(id))
            return;

        def = id;
    }

    /**
     * Add language to service
     * @param language ILanguage
     */
    public void addLanguage(ILanguage language) {
        languages.put(language.getID(), language);
    }

    /**
     * Check if service has language
     * @param id String
     * @return Boolean
     */
    public boolean hasLanguage(String id) {
        return languages.containsKey(id);
    }

    /**
     * Get language from identifier
     * @param id String
     * @return ILanguage
     */
    public ILanguage getLanguage(String id) {
        return languages.get(id);
    }

    /**
     * Get specific language or default language
     * @param id String
     * @return ILanguage
     */
    public ILanguage getOrDefault(String id) {
        ILanguage language = null;
        if(id != null && !id.isEmpty())
            language = getLanguage(id);

        if(language != null)
            return language;

        language = getLanguage(def);
        if(language != null)
            return language;

        return new NullLanguage();
    }

    /**
     * Get optional language from identifier
     * @param id String
     * @return Optional<ILanguage>
     */
    public Optional<ILanguage> getOptionalLanguage(String id) {
        return Optional.ofNullable(getLanguage(id));
    }

    /**
     * Get optional default language
     * @return String
     */
    public Optional<ILanguage> getOptionalDefaultLanguage() {
        return getOptionalLanguage(def);
    }

    /**
     * Remove language from service
     * @param id String
     * @return ILanguage
     */
    public ILanguage removeLanguage(String id) {
        return languages.remove(id);
    }

}
