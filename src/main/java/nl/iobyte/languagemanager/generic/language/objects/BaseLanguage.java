package nl.iobyte.languagemanager.generic.language.objects;

import nl.iobyte.languagemanager.LanguageManager;
import nl.iobyte.languagemanager.generic.language.interfaces.ILanguage;

public class BaseLanguage implements ILanguage {

    private final String id, fileName;

    public BaseLanguage(String id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    /**
     * Get id of language
     * @return String
     */
    public String getID() {
        return id;
    }

    /**
     * Get filename of language
     * @return String
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Get message for plugin at path in language
     * @param plugin_id String
     * @param path String
     * @return String
     */
    public String getMessage(String plugin_id, String path) {
        return LanguageManager.getInstance()
                .getMessageService()
                .getMessage(
                        plugin_id,
                        id,
                        path
                );
    }

}
