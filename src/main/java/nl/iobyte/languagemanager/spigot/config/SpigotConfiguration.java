package nl.iobyte.languagemanager.spigot.config;

import nl.iobyte.languagemanager.generic.config.interfaces.IConfiguration;
import nl.iobyte.languagemanager.generic.logger.LanguageLogger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class SpigotConfiguration implements IConfiguration {

    private final Plugin plugin;
    private final File file;
    private FileConfiguration config;
    private final String fileName;

    public SpigotConfiguration(Plugin plugin, File file) {
        this.plugin = plugin;
        this.file = file;
        this.fileName = file.getName();

        load();
    }

    /**
     * Load configuration file
     */
    private void load() {
        if(!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                LanguageLogger.toConsole("Unable to create parent directories for config file: " + fileName);
                return;
            }
        }

        if(!file.exists()) {
            if(plugin.getResource(fileName) == null) {
                try {
                    if(!file.createNewFile())
                        LanguageLogger.toConsole("Unable to create config file: " + fileName);
                } catch(Exception e) {
                    LanguageLogger.toConsole("Unable to create config file: " + fileName);
                    return;
                }
            } else {
                plugin.saveResource(fileName, true);
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
        LanguageLogger.toConsole("Loaded configuration: " + fileName);
    }

    /**
     * Get name of configuration file
     * @return String
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Save configuration to file
     */
    public void save() {
        try {
            config.save(file);
        } catch (Exception e) {
            LanguageLogger.toConsole("Unable to save config file: "+fileName);
            e.printStackTrace();
        }
    }

    /**
     * Delete configuration file
     */
    public void delete() {
        if(!file.delete())
            LanguageLogger.toConsole("Unable to delete config file: "+fileName);
    }

    /**
     * Check if configuration contains a path
     * @param key Path
     * @return boolean
     */
    public boolean contains(String key) {
        return config.contains(key);
    }

    /**
     * Set value in configuration
     * @param key Path to value
     * @param value Value
     */
    public void set(String key, Object value) {
        config.set(key, value);
    }

    /**
     * Get object
     * @param key Path to object
     * @return Object
     */
    public Object get(String key) {
        return config.get(key);
    }

    /**
     * Get string
     * @param key Path to string
     * @return String
     */
    public String getString(String key) {
        return config.getString(key);
    }

    /**
     * Get boolean
     * @param key Path to boolean
     * @return boolean
     */
    public boolean getBoolean(String key) {
        return Optional.ofNullable(getString(key)).orElse("false").equals("true");
    }

    /**
     * Get integer
     * @param key Path to integer
     * @return Integer
     */
    public int getInt(String key) {
        return config.getInt(key);
    }

    /**
     * Get Short
     * @param key Path to Short
     * @return Short
     */
    public short getShort(String key) {
        return Short.parseShort(Optional.ofNullable(getString(key)).orElse("0"));
    }

    /**
     * Get Double
     * @param key Path to Double
     * @return Double
     */
    public double getDouble(String key) {
        return config.getDouble(key);
    }

    /**
     * Get Float
     * @param key Path to Float
     * @return Float
     */
    public float getFloat(String key) {
        return Float.parseFloat(Optional.ofNullable(getString(key)).orElse("0"));
    }

    /**
     * Get Long
     * @param key Path to Long
     * @return Long
     */
    public long getLong(String key) {
        return config.getLong(key);
    }

    /**
     * Get List of strings
     * @param key Path to list
     * @return List of strings
     */
    public List<String> getStringList(String key) {
        return config.getStringList(key);
    }

    /**
     * Get section's keys
     * @param key Path to section
     * @return Set<String>
     */
    public Set<String> getSection(String key) {
        return Objects.requireNonNull(config.getConfigurationSection(key)).getKeys(false);
    }

}
