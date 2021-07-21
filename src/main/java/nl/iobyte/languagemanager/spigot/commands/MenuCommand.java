package nl.iobyte.languagemanager.spigot.commands;

import nl.iobyte.languagemanager.LanguageManager;
import nl.iobyte.languagemanager.generic.util.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Color.parse("&4Only players can use this command"));
            return true;
        }

        LanguageManager.getInstance()
                .getLanguageService()
                .getMenu()
                .open((Player) sender);
        return true;
    }

}
