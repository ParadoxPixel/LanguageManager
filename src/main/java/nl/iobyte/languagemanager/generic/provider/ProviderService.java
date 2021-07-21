package nl.iobyte.languagemanager.generic.provider;

import nl.iobyte.languagemanager.generic.provider.interfaces.IMessageProvider;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ProviderService {

    private final Map<String, IMessageProvider> providers = new ConcurrentHashMap<>();

    /**
     * Register provider for plugin
     * @param plugin_id String
     * @param provider IMessageProvider
     */
    public void registerProvider(String plugin_id, IMessageProvider provider) {
        providers.put(plugin_id, provider);
    }

    /**
     * Get provider for plugin
     * @param plugin_id String
     * @return IMessageProvider
     */
    public IMessageProvider getProvider(String plugin_id) {
        return providers.get(plugin_id);
    }

    /**
     * Get optional provider for plugin
     * @param plugin_id String
     * @return Optional<IMessageProvider>
     */
    public Optional<IMessageProvider> getOptionalProvider(String plugin_id) {
        return Optional.ofNullable(getProvider(plugin_id));
    }

    /**
     * Unregister provider for plugin
     * @param plugin_id String
     * @return IMessageProvider
     */
    public IMessageProvider unregisterProvider(String plugin_id) {
        return providers.remove(plugin_id);
    }

}
