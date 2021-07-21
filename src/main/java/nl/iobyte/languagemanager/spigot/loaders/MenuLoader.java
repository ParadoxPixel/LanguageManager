package nl.iobyte.languagemanager.spigot.loaders;

import nl.iobyte.languagemanager.LanguageManager;
import nl.iobyte.languagemanager.generic.config.enums.StorageLocation;
import nl.iobyte.languagemanager.generic.config.interfaces.IConfiguration;
import nl.iobyte.languagemanager.generic.language.menu.LanguageMenu;
import nl.iobyte.languagemanager.generic.language.setting.objects.LanguageSetting;

public class MenuLoader {

    public static void load() {
        IConfiguration config = LanguageManager.getInstance().getConfigManager().getConfiguration(StorageLocation.SETTINGS);

        String title = config.getString("menu.title");
        if(title == null || title.isEmpty())
            return;

        int size = config.getInt("menu.size");
        if(size < 1 || size > 54)
            return;

        LanguageMenu menu = new LanguageMenu(
                title,
                size
        );

        LanguageManager.getInstance()
                .getLanguageService()
                .setMenu(menu);

        int slot;
        for(LanguageSetting setting : LanguageManager.getInstance().getLanguageSettingService().getSettings().values()) {
            slot = config.getInt("languages."+setting.getID()+".slot");
            if(slot < 0 || slot >= size)
                continue;

            menu.add(slot, setting);
        }

        menu.build();
    }

}
