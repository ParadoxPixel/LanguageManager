package nl.iobyte.languagemanager.spigot.provider;

import nl.iobyte.languagemanager.LanguageManager;
import nl.iobyte.languagemanager.generic.config.enums.StorageLocation;
import nl.iobyte.languagemanager.generic.provider.interfaces.IMessageProvider;

public class LanguageManagerProvider implements IMessageProvider {

    /**
     * Get message for language at path
     * @param language_id String
     * @param path String
     * @return String
     */
    public String getMessage(String language_id, String path) {
        boolean b = path.equals("prefix");
        if(!b)
            path = language_id+"."+path;

        String msg = LanguageManager.getInstance().getConfigManager().getString(StorageLocation.MESSAGES, path);
        if(!b)
            msg = msg.replace("{prefix}", getMessage("", "prefix"));

        if(msg == null || msg.isEmpty())
            return "";

        return msg;
    }

}
