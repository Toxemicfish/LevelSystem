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

    public static void color_toPlayer(Player player, String message) {
        player.sendMessage(color(message));
    }

    public static void playerOnly(CommandSender sender) {
        sender.sendMessage(prefix + ChatColor.RED + "Player only command!");
    }

    public static void consoleOnly(Player player) {
        player.sendMessage(prefix + ChatColor.RED + "Console only command!");
    }

    public static void numbersOnly(Player player) {
        player.sendMessage(color("&cYou may only use numbers!"));
    }

    //BlocksetCommands
    public static void commandUsage_BlocksetCommands(Player player) {
        player.sendMessage(color("&7Usage&8: &efor /block"));
        player.sendMessage(color("&7- &6/block set <lowXP> <highXP> &7Added the block to the config!"));
        player.sendMessage(color("&7- &6/block remove &7Removes the block from the config!"));
    }

    public static void setBlockconfig(Player player, String replaceblockName, int replacelowXP, int replacehighXP) {
        String sreplacelowXP = String.valueOf(replacelowXP);
        String sreplacehighXP = String.valueOf(replacehighXP);
        player.sendMessage(MessageManager.color("&aThe block &6%1 &awas added in the config with the &elowXP&7: &6%2 &ehighXP&7: &6%3").replace("%1", replaceblockName).replace("%2", sreplacelowXP).replace("%3", sreplacehighXP));
    }

    public static void removedBlockconfig(Player player, String replaceblockName) {
        player.sendMessage(MessageManager.color("&aThe block &6%1 &awas removed from the config").replace("%1", replaceblockName));
    }
}
