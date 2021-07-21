package nl.iobyte.languagemanager.generic.config.enums;

public enum StorageLocation {

    SETTINGS("settings.yml"),
    MESSAGES("messages.yml");

    private final String fileName;
    StorageLocation(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
