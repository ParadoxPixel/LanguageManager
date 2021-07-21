package nl.iobyte.languagemanager.spigot.loaders;

import nl.iobyte.languagemanager.LanguageManager;
import nl.iobyte.languagemanager.generic.config.enums.StorageLocation;
import nl.iobyte.languagemanager.generic.config.interfaces.IConfiguration;
import nl.iobyte.languagemanager.generic.language.objects.BaseLanguage;
import nl.iobyte.languagemanager.generic.language.setting.objects.LanguageSetting;
import nl.iobyte.languagemanager.generic.logger.LanguageLogger;
import java.util.Set;

public class LanguageLoader {

    public static void load() {
        IConfiguration config = LanguageManager.getInstance().getConfigManager().getConfiguration(StorageLocation.SETTINGS);
        Set<String> keys = config.getSection("languages");
        if(keys == null || keys.isEmpty()) {
            LanguageLogger.toConsole("No languages found");
            return;
        }

        for(String id : keys) {
            LanguageSetting setting = load(id, "languages."+id, config);
            if (setting == null) {
                LanguageLogger.toConsole("Unable to load language with ID: "+id);
                continue;
            }

            LanguageManager.getInstance()
                    .getLanguageService()
                    .addLanguage(new BaseLanguage(id, id+".yml"));

            LanguageManager.getInstance()
                    .getLanguageSettingService()
                    .addSetting(setting);
        }

        String def = config.getString("default-language");
        if(def == null || def.isEmpty()) {
            for(LanguageSetting setting : LanguageManager.getInstance().getLanguageSettingService().getSettings().values()) {
                def = setting.getID();
                break;
            }

            if(def == null || def.isEmpty())
                return;
        }

        LanguageManager.getInstance().getLanguageService().setDefault(def);
    }

    public static LanguageSetting load(String id, String path, IConfiguration config) {
        if(!config.contains(path))
            return null;

        String name = config.getString(path+".name");
        if(name == null || name.isEmpty())
            return null;

        String url = config.getString(path+".url");
        if(url == null || url.isEmpty())
            return null;

        return new LanguageSetting(
                id,
                name,
                url
        );
    }

}
