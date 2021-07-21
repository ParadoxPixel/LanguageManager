package nl.iobyte.languagemanager.generic.language.setting.objects;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

public class LanguageSetting {

    private final String id, name, url;

    public LanguageSetting(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    /**
     * Get identifier of language
     * @return String
     */
    public String getID() {
        return id;
    }

    /**
     * Get name of language
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get url of skull texture
     * @return String
     */
    public String getUrl() {
        return url;
    }

    /**
     * Get skull item of language
     * @return ItemStack
     */
    public ItemStack getSkull() {
        if (url == null || url.isEmpty())
            return null;

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta skullMeta = skull.getItemMeta();
        if(skullMeta == null)
            return null;

        skullMeta.setDisplayName(name);

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        Field profileField = null;
        try {
            assert skullMeta != null;
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        assert profileField != null;
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        skull.setItemMeta(skullMeta);
        return skull;
    }

}
