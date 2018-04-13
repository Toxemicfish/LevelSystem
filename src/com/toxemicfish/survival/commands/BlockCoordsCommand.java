package com.toxemicfish.survival.commands;

import com.toxemicfish.survival.utils.MessageManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class BlockCoordsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            Block block = player.getTargetBlock((HashSet<Byte>) null, 5);
            Location bockLocation = block.getLocation();

            String blockName = block.getType().name().toLowerCase();

            if(block.getType() == Material.AIR) {
                player.sendMessage(MessageManager.color("&6You can not get the coords of AIR"));
                return true;
            }

            MessageManager.color_toPlayer(player, "&aThe coords of the block &e" + blockName + " &a is &7---------");
            MessageManager.color_toPlayer(player, "&bX&7- &e" + bockLocation.getX());
            MessageManager.color_toPlayer(player, "&bY&7- &e" + bockLocation.getY());
            MessageManager.color_toPlayer(player, "&bZ&7- &e" + bockLocation.getZ());

        } else {
            MessageManager.playerOnly(sender);
        }
        return true;
    }

}
