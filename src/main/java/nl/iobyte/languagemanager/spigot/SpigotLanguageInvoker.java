package nl.iobyte.languagemanager.spigot;

import nl.iobyte.languagemanager.LanguageManager;
import nl.iobyte.languagemanager.generic.ILanguageManagerInvoker;
import nl.iobyte.languagemanager.generic.config.enums.StorageLocation;
import nl.iobyte.languagemanager.generic.config.interfaces.IConfigurationFactory;
import nl.iobyte.languagemanager.generic.database.objects.types.SQLiteDatabase;
import nl.iobyte.languagemanager.generic.menu.MenuAPI;
import nl.iobyte.languagemanager.generic.scheduler.IScheduler;
import nl.iobyte.languagemanager.spigot.commands.MenuCommand;
import nl.iobyte.languagemanager.spigot.config.SpigotConfigurationFactory;
import nl.iobyte.languagemanager.spigot.listeners.PlayerListener;
import nl.iobyte.languagemanager.spigot.loaders.LanguageLoader;
import nl.iobyte.languagemanager.spigot.loaders.MenuLoader;
import nl.iobyte.languagemanager.spigot.provider.LanguageManagerProvider;
import nl.iobyte.languagemanager.spigot.scheduler.SpigotScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class SpigotLanguageInvoker extends JavaPlugin implements ILanguageManagerInvoker {

    private IScheduler scheduler;
    private IConfigurationFactory factory;

    public void onEnable() {
        scheduler = new SpigotScheduler(this);
        factory = new SpigotConfigurationFactory(this);

        LanguageManager manager = new LanguageManager(this);
        manager.getProviderService().registerProvider(
                "LANGUAGE",
                new LanguageManagerProvider()
        );
        manager.getConfigManager().addConfiguration(
                StorageLocation.SETTINGS,
                factory.newInstance(StorageLocation.SETTINGS.getFileName())
        );
        manager.getConfigManager().addConfiguration(
                StorageLocation.MESSAGES,
                factory.newInstance(StorageLocation.MESSAGES.getFileName())
        );
        manager.getDatabaseService()
                .addDatabase(
                        "local",
                        new SQLiteDatabase(
                                this,
                                "sqlite.db"
                        )
                );

        manager.getDatabaseService().execute("local", "CREATE TABLE IF NOT EXISTS preferences(language_id VARCHAR(255) NOT NULL, uuid VARCHAR(255) NOT NULL PRIMARY KEY)", null);

        LanguageLoader.load();
        MenuLoader.load();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        MenuAPI.register(this);

        Objects.requireNonNull(getCommand("lm")).setExecutor(new MenuCommand());
    }

    public void onDisable() {
        LanguageManager.getInstance()
                .getMessageService()
                .stop();
    }

    /**
     * Get invokers scheduler implementation
     * @return IScheduler
     */
    public IScheduler getScheduler() {
        return scheduler;
    }

    /**
     * Get invokers configuration factory implementation
     * @return IConfigurationFactory
     */
    public IConfigurationFactory getFactory() {
        return factory;
    }
}
