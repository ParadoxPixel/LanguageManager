package nl.iobyte.languagemanager.generic.provider.interfaces;

public interface IMessageProvider {

    /**
     * Get message for language at path
     * @param language_id String
     * @param path String
     * @return String
     */
    String getMessage(String language_id, String path);

}
