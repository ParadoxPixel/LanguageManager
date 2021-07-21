package nl.iobyte.languagemanager.generic.language.objects;

import nl.iobyte.languagemanager.generic.language.interfaces.ILanguage;

/**
 * Empty implementation of ILanguage
 */
public class NullLanguage implements ILanguage {

    /**
     * Get id of language
     * @return String
     */
    public String getID() {
        return "NULL";
    }

    /**
     * Get filename of language
     * @return String
     */
    public String getFileName() {
        return "NULL.yml";
    }

    /**
     * Get message at path for language
     * @param plugin_id String
     * @param path String
     * @return String
     */
    public String getMessage(String plugin_id, String path) {
        return "Language not found";
    }

}
