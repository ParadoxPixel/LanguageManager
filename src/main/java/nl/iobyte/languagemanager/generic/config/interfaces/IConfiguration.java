package nl.iobyte.languagemanager.generic.config.interfaces;

import java.util.List;
import java.util.Set;

public interface IConfiguration {

    /**
     * Get name of configuration file
     * @return String
     */
    String getFileName();

    /**
     * Save configuration to file
     */
    void save();

    /**
     * Delete configuration file
     */
    void delete();

    /**
     * Check if configuration contains a path
     * @param key Path
     * @return boolean
     */
    boolean contains(String key);

    /**
     * Set value in configuration
     * @param key Path to value
     * @param value Value
     */
    void set(String key, Object value);

    /**
     * Get object
     * @param key Path to object
     * @return Object
     */
    Object get(String key);

    /**
     * Get string
     * @param key Path to string
     * @return String
     */
    String getString(String key);

    /**
     * Get boolean
     * @param key Path to boolean
     * @return boolean
     */
    boolean getBoolean(String key);

    /**
     * Get integer
     * @param key Path to integer
     * @return Integer
     */
    int getInt(String key);

    /**
     * Get Short
     * @param key Path to Short
     * @return Short
     */
    short getShort(String key);

    /**
     * Get Double
     * @param key Path to Double
     * @return Double
     */
    double getDouble(String key);

    /**
     * Get Float
     * @param key Path to Float
     * @return Float
     */
    float getFloat(String key);

    /**
     * Get Long
     * @param key Path to Long
     * @return Long
     */
    long getLong(String key);

    /**
     * Get List of strings
     * @param key Path to list
     * @return List of strings
     */
    List<String> getStringList(String key);

    /**
     * Get section's keys
     * @param key Path to section
     * @return Set<String>
     */
    Set<String> getSection(String key);

}