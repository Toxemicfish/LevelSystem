package com.toxemicfish.survival.commands;

import com.toxemicfish.survival.utils.MessageManager;
import com.toxemicfish.survival.utils.blockLevelYML;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;

public class BlockSetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            Block block = player.getTargetBlock((HashSet<Byte>) null, 5);
            Location bockLocation = block.getLocation();

            String blockName = block.getType().name().toLowerCase();

            if (args.length == 0) {
                MessageManager.commandUsage_BlocksetCommands(player);
                return true;
            }

            if (args[0].equalsIgnoreCase("set")) {

                if (args.length != 1) {
                    if (args.length != 2) {

                        if (!isInt(args[1])) {
                            MessageManager.numbersOnly(player);
                            return true;
                        }

                        if (!isInt(args[2])) {
                            MessageManager.numbersOnly(player);
                            return true;
                        }

                        if(block.getType() == Material.AIR) {
                            player.sendMessage(MessageManager.color("&6Air &acan not be set in the config"));
                            return true;
                        }

                        if (blockLevelYML.getblockLevels().contains("blocksValues." + blockName) || blockLevelYML.getblockLevels().getStringList("blocks").contains(blockName)) {
                            player.sendMessage(MessageManager.color("&aThe block &6" + blockName + " &ais already in the config!"));
                            return true;
                        }

                        int lowXP = Integer.parseInt(args[1]);
                        int highXP = Integer.parseInt(args[2]);

                        List<String> list = blockLevelYML.getblockLevels().getStringList("blocks");
                        list.add(blockName);
                        blockLevelYML.getblockLevels().set("blocks", list);

                        blockLevelYML.getblockLevels().set("blocksValues." + blockName + ".lowXP", lowXP);
                        blockLevelYML.getblockLevels().set("blocksValues." + blockName + ".highXP", highXP);
                        blockLevelYML.saveblockLevels();
                        blockLevelYML.reloadblockLevelFile();

                        MessageManager.setBlockconfig(player, blockName, lowXP, highXP);

                        //player.sendMessage(MessageManager.color("&aYou are looking at &6" + block.getType().name().toLowerCase()));
                    } else {
                        player.sendMessage(MessageManager.color("&7- &6/block set <lowXP> <highXP> &7Added the block to the config!"));
                        return true;
                    }
                } else {
                    player.sendMessage(MessageManager.color("&7- &6/block set <lowXP> <highXP> &7Added the block to the config!"));
                    return true;
                }

            } else if (args[0].equalsIgnoreCase("remove")) {
                if (!blockLevelYML.getblockLevels().contains("blocksValues." + blockName) || !blockLevelYML.getblockLevels().getStringList("blocks").contains(blockName)) {
                    player.sendMessage(MessageManager.color("&aThe block &6" + blockName + " &ais not in the config!"));
                    return true;
                }

                List<String> list = blockLevelYML.getblockLevels().getStringList("blocks");
                list.remove(blockName);
                blockLevelYML.getblockLevels().set("blocks", list);

                blockLevelYML.getblockLevels().set("blocksValues." + blockName, null);
                blockLevelYML.saveblockLevels();
                blockLevelYML.reloadblockLevelFile();

                MessageManager.removedBlockconfig(player, blockName);
                return true;
            } else {
                MessageManager.commandUsage_BlocksetCommands(player);
            }

        } else {
            MessageManager.playerOnly(sender);
        }
        return true;
    }

    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean isBoolean(String s) {
        try {
            Boolean.parseBoolean(s);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
