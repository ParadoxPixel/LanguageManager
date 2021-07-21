package nl.iobyte.languagemanager.generic.language.interfaces;

public interface ILanguage {

    /**
     * Get id of language
     * @return String
     */
    String getID();

    /**
     * Get filename of language
     * @return String
     */
    String getFileName();

    /**
     * Get message for plugin at path in language
     * @param plugin_id String
     * @param path String
     * @return String
     */
    String getMessage(String plugin_id, String path);

}
