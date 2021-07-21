package nl.iobyte.languagemanager.generic.message;

import nl.iobyte.languagemanager.LanguageManager;
import nl.iobyte.languagemanager.generic.map.WeakConcurrentHashMap;
import nl.iobyte.languagemanager.generic.message.objects.MessageManager;
import nl.iobyte.languagemanager.generic.provider.objects.NullProvider;

public class MessageService {

    private final WeakConcurrentHashMap<String, MessageManager> managers = new WeakConcurrentHashMap<>(10 * 60 * 1000);

    public MessageService() {
        managers.setConsumer(MessageManager::stop);
    }

    /**
     * Stop the thread behind the map
     */
    public void stop() {
        managers.quitMap();
    }

    /**
     * Get message for plugin in language at path
     * @param plugin_id String
     * @param language_id String
     * @param path String
     * @return String
     */
    public String getMessage(String plugin_id, String language_id, String path) {
        if(plugin_id == null || plugin_id.isEmpty())
            return "No plugin specified";

        if(language_id == null || language_id.isEmpty())
            return "No language specified";

        if(path == null || path.isEmpty())
            return "No path specified";

        MessageManager manager = managers.get(plugin_id);
        if(manager != null)
            return manager.getMessage(language_id, path);

        manager = new MessageManager(
                LanguageManager.getInstance()
                        .getProviderService()
                        .getOptionalProvider(plugin_id)
                        .orElse(new NullProvider())
        );

        managers.put(plugin_id, manager);
        return manager.getMessage(language_id, path);
    }

}
