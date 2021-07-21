package nl.iobyte.languagemanager.generic.util;

import net.md_5.bungee.api.ChatColor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color {

    /**
     * Parse hex and regular minecraft color codes starting with '&'
     * @param str String
     * @return String
     */
    public static String parse(String str) {
        if(str == null || str.isEmpty())
            return "";

        Matcher matcher = Pattern.compile("&#([a-fA-F0-9]{6})").matcher(str);
        StringBuilder buffer = new StringBuilder(str.length() + 4 * 8);
        while (matcher.find()) {
            String group = matcher.group(1);
            matcher.appendReplacement(
                    buffer,
                    "&x&" +
                            group.charAt(0) + "&" +
                            group.charAt(1) + "&" +
                            group.charAt(2) + "&" +
                            group.charAt(3) + "&" +
                            group.charAt(4) + "&" +
                            group.charAt(5)
            );
        }

        return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());
    }

}
