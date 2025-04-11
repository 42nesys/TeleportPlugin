package pashmash.teleportPlugin.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import pashmash.teleportPlugin.TeleportPlugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtil {

    // Credits VertrauterDavid


    public static final String RED = "&#ff0000";
    public static final String MAIN = "&#face87";
    public static final String PREFIX = TeleportPlugin.getInstance().getConfig().getString("Settings.prefix");

    public static Component translate(String message) {
        message = message.replaceAll("&([0-9a-fk-or])", "§$1");
        Pattern pattern = Pattern.compile("&#([A-Fa-f0-9]{6})");
        Matcher matcher = pattern.matcher(message);
        StringBuilder buffer = new StringBuilder();

        while (matcher.find()) {
            matcher.appendReplacement(buffer, "§x"
                    + "§" + matcher.group(1).charAt(0)
                    + "§" + matcher.group(1).charAt(1)
                    + "§" + matcher.group(1).charAt(2)
                    + "§" + matcher.group(1).charAt(3)
                    + "§" + matcher.group(1).charAt(4)
                    + "§" + matcher.group(1).charAt(5));
        }
        matcher.appendTail(buffer);
        return LegacyComponentSerializer.legacySection().deserialize(buffer.toString());
    }

}