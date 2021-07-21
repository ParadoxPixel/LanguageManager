package nl.iobyte.languagemanager.generic.message.objects;

import nl.iobyte.languagemanager.generic.map.WeakConcurrentHashMap;
import nl.iobyte.languagemanager.generic.provider.interfaces.IMessageProvider;

public class MessageManager {

    private final IMessageProvider provider;
    private final WeakConcurrentHashMap<String, String> messages = new WeakConcurrentHashMap<>(5 * 60 * 1000);

    public MessageManager(IMessageProvider provider) {
        this.provider = provider;
    }

    /**
     * Stop the thread behind the map
     */
    public void stop() {
        messages.quitMap();
    }

    /**
     * Get message provider
     * @return IMessageProvider
     */
    public IMessageProvider getProvider() {
        return provider;
    }

    /**
     * Add message to cache
     * @param path String
     * @param msg String
     */
    public void addMessage(String language_id, String path, String msg) {
        if(path == null || path.isEmpty())
            return;

        messages.put(language_id+"_"+path, msg);
    }

    /**
     * Get message and cache if needed
     * @param language_id String
     * @param path String
     * @return String
     */
    public String getMessage(String language_id, String path) {
        if(path == null || path.isEmpty())
            return "Invalid path to message";

        return messages.computeIfAbsent(language_id+"_"+path, key -> provider.getMessage(language_id, path));
    }

}
