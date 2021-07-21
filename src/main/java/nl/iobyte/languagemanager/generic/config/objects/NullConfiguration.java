package nl.iobyte.languagemanager.generic.config.objects;

import nl.iobyte.languagemanager.generic.config.interfaces.IConfiguration;
import java.util.List;
import java.util.Set;

/**
 * Empty Configuration implementation
 */
public class NullConfiguration implements IConfiguration {

    public String getFileName() {
        return null;
    }


    public void save() { }


    public void delete() { }


    public boolean contains(String key) {
        return false;
    }


    public void set(String key, Object value) { }


    public Object get(String key) {
        return null;
    }


    public String getString(String key) {
        return null;
    }

    public boolean getBoolean(String key) {
        return false;
    }

    public int getInt(String key) {
        return 0;
    }

    public short getShort(String key) {
        return 0;
    }

    public double getDouble(String key) {
        return 0;
    }

    public float getFloat(String key) {
        return 0;
    }

    public long getLong(String key) {
        return 0;
    }

    public List<String> getStringList(String key) {
        return null;
    }

    public Set<String> getSection(String key) {
        return null;
    }

}