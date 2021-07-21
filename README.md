# LanguageManager
To make use of this plugin you have to implement the `IMessageInterface` and register your class with the `ProviderService`

A simple example of this:
```java
public class TestMessageProvider implements IMessageProvider {

    /**
     * Get message for language at path
     * @param language_id String
     * @param path String
     * @return String
     */
    public String getMessage(String language_id, String path) {
        return "Language: "+language_id+" Path: "+path;
    }

}

LanguageManager.getProviderService().registerProvider(
  "plugin_id",
  new TestMessageProvider()
);
```

Next up to start using the provider we can call the `getMessage` method from `LanguageManager` like so:
```java
String msg = LanguageManager.getMessage("plugin_id", uuid, "path.to.message");
```
The uuid comes from the player, this is so their prefered language can be obtained to send the correct `language_id` to the provider.
All messages are being cached for 5 minutes to reduce method calls on the provider and internal mapping.
