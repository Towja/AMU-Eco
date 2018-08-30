package com.ausminersunited.amueco;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAmueco implements CommandExecutor {

    Main main;

    public CommandAmueco(Main plugin) {
        main = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(main.getDescription().getCommands().get("amueco").get("permission").toString())) {
                sender.sendMessage(ChatColor.GOLD + "=== " + main.getDescription().getName() + ChatColor.DARK_AQUA + " v" + main.getDescription().getVersion() + ChatColor.GOLD + " ===" + ChatColor.DARK_AQUA + "\nDescription: " + ChatColor.WHITE + main.getDescription().getDescription() + ChatColor.DARK_AQUA + "\nAuthor: " + ChatColor.WHITE + main.getDescription().getAuthors());
                return true;
            } else {
                return false;
            }
        } else {
            sender.sendMessage("=== " + main.getDescription().getName() + " v" + main.getDescription().getVersion() + " ===" + "\nDescription: " + main.getDescription().getDescription() + "\nAuthor: " + main.getDescription().getAuthors());
            return true;
        }
    }
}
