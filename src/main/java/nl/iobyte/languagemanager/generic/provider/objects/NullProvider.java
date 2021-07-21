package nl.iobyte.languagemanager.generic.provider.objects;

import nl.iobyte.languagemanager.generic.provider.interfaces.IMessageProvider;

public class NullProvider implements IMessageProvider {

    public String getMessage(String language_id, String path) {
        return "Unknown plugin";
    }

}
