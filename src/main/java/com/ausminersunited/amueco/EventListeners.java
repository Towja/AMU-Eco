package com.ausminersunited.amueco;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EventListeners implements Listener {

    Main main;
    FileConfiguration config;

    public EventListeners(Main plugin) {
        main = plugin;
        config = main.getConfig();
    }

    @EventHandler
    public void OnEntityDeath(EntityDeathEvent event) {
        LivingEntity mob = event.getEntity();
        if (mob.getKiller() != null) {
            Player killer = mob.getKiller();
            String mobpath = "mob-rewards." + mob.getType().toString();
            if (config.getInt(mobpath) > 0) {
                main.getEconomy().depositPlayer(killer, config.getInt(mobpath));
                if (mob.getType().toString() == "PLAYER") {
                    if (config.getBoolean("player.drop-money")) {
                        main.getEconomy().withdrawPlayer((Player) mob, config.getInt(mobpath));
                        if (config.getBoolean("send-loss-message")) {
                            mob.sendMessage(ChatColor.RED + "You lost $" + config.getInt(mobpath) + " in your death.");
                        }
                        if (config.getBoolean("send-payment-message")) {
                            killer.sendMessage(ChatColor.GREEN + "You got $" + config.getInt(mobpath) + " for killing " + ((Player) mob).getDisplayName());
                        }
                    }
                } else {
                    if (config.getBoolean("send-payment-message")) {
                        killer.sendMessage(ChatColor.GREEN + "You got $" + config.getInt(mobpath) + " for killing a " + mob.getType().toString().toLowerCase());
                    }
                }
            }
        }
    }
}
