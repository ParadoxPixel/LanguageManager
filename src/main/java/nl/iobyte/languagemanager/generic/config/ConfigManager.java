package nl.iobyte.languagemanager.generic.config;

import nl.iobyte.languagemanager.generic.config.enums.StorageLocation;
import nl.iobyte.languagemanager.generic.config.interfaces.IConfiguration;
import nl.iobyte.languagemanager.generic.config.objects.NullConfiguration;
import java.util.*;

public class ConfigManager {

    private final Map<String, IConfiguration> configurations = new HashMap<>();

    /**
     * Add configuration to manager
     * @param id Identifier
     * @param configuration IConfiguration
     */
    public void addConfiguration(String id, IConfiguration configuration) {
        configurations.put(id, configuration);
    }

    /**
     * Add configuration to manager
     * @param location StorageLocation
     * @param configuration IConfiguration
     */
    public void addConfiguration(StorageLocation location, IConfiguration configuration) {
        addConfiguration(location.toString(), configuration);
    }

    /**
     * Check if manager has configuration
     * @param id Identifier
     * @return boolean
     */
    public boolean hasConfiguration(String id) {
        return configurations.containsKey(id);
    }

    /**
     * Check if manager has configuration
     * @param location StorageLocation
     * @return boolean
     */
    public boolean hasConfiguration(StorageLocation location) {
        return hasConfiguration(location.toString());
    }

    /**
     * Get configuration
     * @param id Identifier
     * @return IConfiguration
     */
    public IConfiguration getConfiguration(String id) {
        return Optional.ofNullable(configurations.get(id))
                .orElse(new NullConfiguration());
    }

    /**
     * Get configuration
     * @param location StorageLocation
     * @return IConfiguration
     */
    public IConfiguration getConfiguration(StorageLocation location) {
        return getConfiguration(location.toString());
    }

    /**
     * Remove configuration from manager
     * @param id Identifier
     * @return IConfiguration
     */
    public IConfiguration removeConfiguration(String id) {
        return configurations.remove(id);
    }

    /**
     * Remove configuration from manager
     * @param location StorageLocation
     * @return IConfiguration
     */
    public IConfiguration removeConfiguration(StorageLocation location) {
        return removeConfiguration(location.toString());
    }

    /**
     * Get name of configuration file
     * @param id Identifier
     * @return String
     */
    public String getFileName(String id) {
        return getConfiguration(id).getFileName();
    }

    /**
     * Get name of configuration file
     * @param location StorageLocation
     * @return String
     */
    public String getFileName(StorageLocation location) {
        return getFileName(location.toString());
    }

    /**
     * Save configuration to file
     * @param id Identifier
     */
    public void save(String id) {
        getConfiguration(id).save();
    }

    /**
     * Save configuration to file
     * @param location StorageLocation
     */
    public void save(StorageLocation location) {
        save(location.toString());
    }

    /**
     * Save all configurations stored in manager
     */
    public void saveAll() {
        for(IConfiguration configuration : configurations.values())
            configuration.save();
    }

    /**
     * Delete configuration file
     * @param id Identifier
     */
    public void delete(String id) {
        getConfiguration(id).delete();
    }

    /**
     * Delete configuration file
     * @param location StorageLocation
     */
    public void delete(StorageLocation location) {
        delete(location.toString());
    }

    /**
     * Check if configuration contains a path
     * @param id Identifier
     * @param key Path
     * @return boolean
     */
    public boolean contains(String id, String key) {
        return getConfiguration(id).contains(key);
    }

    /**
     * Check if configuration contains a path
     * @param location StorageLocation
     * @param key Path
     * @return boolean
     */
    public boolean contains(StorageLocation location, String key) {
        return contains(location.toString(), key);
    }

    /**
     * Set value in configuration
     * @param id Identifier
     * @param key Path to value
     * @param value Value
     */
    public void set(String id, String key, Object value) {
        getConfiguration(id).set(key, value);
    }

    /**
     * Set value in configuration
     * @param location StorageLocation
     * @param key Path to value
     * @param value Value
     */
    public void set(StorageLocation location, String key, Object value) {
        set(location.toString(), key, value);
    }

    /**
     * Get Object
     * @param id Identifier
     * @param key Path to Object
     * @return Object
     */
    public Object get(String id, String key) {
        return getConfiguration(id).get(key);
    }

    /**
     * Get Object
     * @param location StorageLocation
     * @param key Path to Object
     * @return Object
     */
    public Object get(StorageLocation location, String key) {
        return get(location.toString(), key);
    }

    /**
     * Get String
     * @param id Identifier
     * @param key Path to String
     * @return String
     */
    public String getString(String id, String key) {
        return getConfiguration(id).getString(key);
    }

    /**
     * Get String
     * @param location StorageLocation
     * @param key Path to String
     * @return String
     */
    public String getString(StorageLocation location, String key) {
        return getString(location.toString(), key);
    }

    /**
     * Get boolean
     * @param id Identifier
     * @param key Path to boolean
     * @return boolean
     */
    public boolean getBoolean(String id, String key) {
        return getConfiguration(id).getBoolean(key);
    }

    /**
     * Get boolean
     * @param location StorageLocation
     * @param key Path to boolean
     * @return boolean
     */
    public boolean getBoolean(StorageLocation location, String key) {
        return getBoolean(location.toString(), key);
    }

    /**
     * Get Integer
     * @param id Identifier
     * @param key Path to integer
     * @return Integer
     */
    public int getInt(String id, String key) {
        return getConfiguration(id).getInt(key);
    }

    /**
     * Get Integer
     * @param location StorageLocation
     * @param key Path to integer
     * @return Integer
     */
    public int getInt(StorageLocation location, String key) {
        return getInt(location.toString(), key);
    }

    /**
     * Get Short
     * @param id Identifier
     * @param key Path to Short
     * @return Short
     */
    public short getShort(String id, String key) {
        return getConfiguration(id).getShort(key);
    }

    /**
     * Get Short
     * @param location StorageLocation
     * @param key Path to Short
     * @return Short
     */
    public short getShort(StorageLocation location, String key) {
        return getShort(location.toString(), key);
    }

    /**
     * Get Double
     * @param id Identifier
     * @param key Path to Double
     * @return Double
     */
    public double getDouble(String id, String key) {
        return getConfiguration(id).getDouble(key);
    }

    /**
     * Get Double
     * @param location StorageLocation
     * @param key Path to Double
     * @return Double
     */
    public double getDouble(StorageLocation location, String key) {
        return getDouble(location.toString(), key);
    }

    /**
     * Get Float
     * @param id Identifier
     * @param key Path to Float
     * @return Float
     */
    public float getFloat(String id, String key) {
        return getConfiguration(id).getFloat(key);
    }

    /**
     * Get Float
     * @param location StorageLocation
     * @param key Path to Float
     * @return Float
     */
    public float getFloat(StorageLocation location, String key) {
        return getFloat(location.toString(), key);
    }

    /**
     * Get Long
     * @param id Identifier
     * @param key Path to Long
     * @return Long
     */
    public long getLong(String id, String key) {
        return getConfiguration(id).getLong(key);
    }

    /**
     * Get Long
     * @param location StorageLocation
     * @param key Path to Long
     * @return Long
     */
    public long getLong(StorageLocation location, String key) {
        return getLong(location.toString(), key);
    }

    /**
     * Get List of Strings
     * @param id Identifier
     * @param key Path to list
     * @return List of Strings
     */
    public List<String> getStringList(String id, String key) {
        return getConfiguration(id).getStringList(key);
    }

    /**
     * Get List of Strings
     * @param location StorageLocation
     * @param key Path to list
     * @return List of Strings
     */
    public List<String> getStringList(StorageLocation location, String key) {
        return getStringList(location.toString(), key);
    }

    /**
     * Get section's keys
     * @param id Identifier
     * @param key Path to section
     * @return Set<String>
     */
    public Set<String> getSection(String id, String key) {
        return getConfiguration(id).getSection(key);
    }

    /**
     * Get section's keys
     * @param location StorageLocation
     * @param key Path to section
     * @return Set<String>
     */
    public Set<String> getSection(StorageLocation location, String key) {
        return getSection(location.toString(), key);
    }

}