package com.toxemicfish.survival.events;

import com.toxemicfish.survival.Main;
import com.toxemicfish.survival.utils.LevelYML;
import com.toxemicfish.survival.utils.MessageManager;
import com.toxemicfish.survival.utils.blockLevelYML;
import com.toxemicfish.survival.utils.playerLevelYML;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BlockBreak implements Listener {

    Main plugin = Main.getInstance();

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        for (String blocksName : blockLevelYML.getblockLevels().getStringList("blocks")) {

            ItemStack blockMat = new ItemStack(Material.getMaterial(blocksName.toUpperCase()));

            String blockName_JustBroke = block.getType().name();

            if(block.getType() == blockMat.getType()) {

                if(!blockLevelYML.getblockLevels().contains("blocksValues." + blocksName)) {
                    player.sendMessage(MessageManager.color("&aThe block &e" + blockName_JustBroke + " &ahas no value!!"));
                    plugin.getServer().getConsoleSender().sendMessage(MessageManager.color("&aThe block &e" + blockName_JustBroke + " &ahas no value!!"));
                    return;
                }

                int high = blockLevelYML.getblockLevels().getInt("blocksValues." + blocksName + ".highXP");
                int low = blockLevelYML.getblockLevels().getInt("blocksValues." + blocksName + ".lowXP");

                xpFunction(player, high, low);
            }
        }
    }

    private void xpFunction(Player player, int xphigh, int xplow) {
        int Currentlevel = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".level");
        int Currentxp = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".xp");
        int totalxp = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".totalxp");

        int Nextedlevel = Currentlevel + 1;

        int xpneeded = LevelYML.getLevels().getInt("levels." + Nextedlevel + ".xp");

        Random r = new Random();

        int fixedXPhigh = xphigh + 1;
        int fixedXPlow = xplow - 1;


        int result = r.nextInt(xphigh-xplow) + xplow;

        if(!LevelYML.getLevels().contains("levels." + Nextedlevel)) {
            plugin.setStatsBoardMaxedLevel(player, Nextedlevel - 1, 0, 0, totalxp);
            return;
        }

        playerLevelYML.getLevels().set("PlayerLevels." + player.getUniqueId() + ".xp", Currentxp + result);
        playerLevelYML.getLevels().set("PlayerLevels." + player.getUniqueId() + ".totalxp", totalxp + result);
        playerLevelYML.saveLevels();

        int UpdatedCurrentxp = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".xp");

        if (UpdatedCurrentxp >= xpneeded) {
            player.sendMessage(MessageManager.color("&6Leveled UP!"));
            playerLevelYML.getLevels().set("PlayerLevels." + player.getUniqueId() + ".level", Currentlevel + 1);
            playerLevelYML.getLevels().set("PlayerLevels." + player.getUniqueId() + ".xp", UpdatedCurrentxp - xpneeded);
            playerLevelYML.saveLevels();

            int UpdatedCurrentlevel = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".level");
            int NEWUpdatedCurrentxp = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".xp");

            int NewNextedlevel = UpdatedCurrentlevel + 1;

            int UpdatedTotalxp = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".totalxp");

            int Newxpneeded = LevelYML.getLevels().getInt("levels." + NewNextedlevel + ".xp");

            plugin.setStatsBoard(player, UpdatedCurrentlevel, NEWUpdatedCurrentxp, Newxpneeded, UpdatedTotalxp);
            return;
        }

        player.sendMessage(MessageManager.color("&a+&e" + result + " &bExperience"));

        int UpdatedCurrentlevel = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".level");

        int NewUpdatedTotalxp = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".totalxp");

        plugin.setStatsBoard(player, UpdatedCurrentlevel, UpdatedCurrentxp, xpneeded, NewUpdatedTotalxp);
    }


}
