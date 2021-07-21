package nl.iobyte.languagemanager.generic.config.interfaces;

import java.io.File;

public interface IConfigurationFactory {

    /**
     * New configuration instance from file name
     * @param fileName String
     * @return IConfiguration
     */
    IConfiguration newInstance(String fileName);

    /**
     * New configuration instance from file
     * @param file File
     * @return IConfiguration
     */
    IConfiguration newInstance(File file);

    /**
     * Get data folder of plugin
     * @return File
     */
    File getDataFolder();

}
