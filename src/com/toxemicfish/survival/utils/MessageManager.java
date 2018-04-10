package com.toxemicfish.survival.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageManager {

    static String prefix = ChatColor.translateAlternateColorCodes('&', "&7[&eScarcePvP&7]&f ");

    public static String getPrefix() {
        return prefix;
    }

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static void playerOnly(CommandSender sender) {
        sender.sendMessage(prefix + ChatColor.RED + "Player only command!");
    }

    public static void consoleOnly(Player player) {
        player.sendMessage(prefix + ChatColor.RED + "Console only command!");
    }

}
