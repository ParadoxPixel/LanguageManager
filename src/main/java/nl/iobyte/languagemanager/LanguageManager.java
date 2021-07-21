package nl.iobyte.languagemanager;

import nl.iobyte.languagemanager.generic.ILanguageManagerInvoker;
import nl.iobyte.languagemanager.generic.config.ConfigManager;
import nl.iobyte.languagemanager.generic.database.DatabaseService;
import nl.iobyte.languagemanager.generic.language.LanguageService;
import nl.iobyte.languagemanager.generic.language.setting.LanguageSettingService;
import nl.iobyte.languagemanager.generic.message.MessageService;
import nl.iobyte.languagemanager.generic.player.PlayerService;
import nl.iobyte.languagemanager.generic.provider.ProviderService;
import java.util.UUID;

public class LanguageManager {

    private static LanguageManager instance;

    private final ILanguageManagerInvoker invoker;
    private final LanguageService languageService = new LanguageService();
    private final LanguageSettingService languageSettingService = new LanguageSettingService();
    private final MessageService messageService = new MessageService();
    private final ProviderService providerService = new ProviderService();

    private final PlayerService playerService = new PlayerService();
    private final DatabaseService databaseService = new DatabaseService();
    private final ConfigManager configManager = new ConfigManager();

    public LanguageManager(ILanguageManagerInvoker invoker) {
        instance = this;
        this.invoker = invoker;
    }

    /**
     * Get instance
     * @return LanguageManager
     */
    public static LanguageManager getInstance() {
        return instance;
    }

    /**
     * Get invoker
     * @return ILanguageManagerInvoker
     */
    public ILanguageManagerInvoker getInvoker() {
        return invoker;
    }

    /**
     * Get provider service
     * @return ProviderService
     */
    public ProviderService getProviderService() {
        return providerService;
    }

    /**
     * Get message service
     * @return MessageService
     */
    public MessageService getMessageService() {
        return messageService;
    }

    /**
     * Get language service
     * @return LanguageService
     */
    public LanguageService getLanguageService() {
        return languageService;
    }

    /**
     * Get language setting service
     * @return LanguageSettingService
     */
    public LanguageSettingService getLanguageSettingService() {
        return languageSettingService;
    }

    /**
     * Get player service
     * @return PlayerService
     */
    public PlayerService getPlayerService() {
        return playerService;
    }

    /**
     * Get database service
     * @return DatabaseService
     */
    public DatabaseService getDatabaseService() {
        return databaseService;
    }

    /**
     * Get config manager
     * @return ConfigManager
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }

    /**
     * Get message for plugin for player at path
     * @param plugin_id String
     * @param uuid UUID
     * @param path String
     * @return String
     */
    public String getMessage(String plugin_id, UUID uuid, String path) {
        return playerService.getPreferredLanguage(uuid)
                .getMessage(
                        plugin_id,
                        path
                );
    }

}
