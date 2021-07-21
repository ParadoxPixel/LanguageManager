package nl.iobyte.languagemanager.spigot.config;

import nl.iobyte.languagemanager.generic.config.interfaces.IConfiguration;
import nl.iobyte.languagemanager.generic.config.interfaces.IConfigurationFactory;
import org.bukkit.plugin.Plugin;
import java.io.File;

public class SpigotConfigurationFactory implements IConfigurationFactory {

    private final Plugin plugin;

    public SpigotConfigurationFactory(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * New configuration instance from name
     * @param fileName String
     * @return IConfiguration
     */
    public IConfiguration newInstance(String fileName) {
        return newInstance(new File(
                plugin.getDataFolder(),
                fileName
        ));
    }

    /**
     * New configuration instance from file
     * @param file File
     * @return IConfiguration
     */
    public IConfiguration newInstance(File file) {
        return new SpigotConfiguration(
                plugin,
                file
        );
    }

    public File getDataFolder() {
        return plugin.getDataFolder();
    }
}
