package com.toxemicfish.survival.events;

import com.toxemicfish.survival.Main;
import com.toxemicfish.survival.utils.LevelYML;
import com.toxemicfish.survival.utils.MessageManager;
import com.toxemicfish.survival.utils.playerLevelYML;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    Main plugin = Main.getInstance();

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if(block.getType() == Material.DIRT || block.getType() == Material.GRASS) {
            xpFunction(player, 1);
        }
        else if (block.getType() == Material.STONE) {
            xpFunction(player, 100);
        } else if (block.getType() == Material.DIAMOND_ORE) {
            xpFunction(player, 300);
        } else if(block.getType() == Material.OBSIDIAN) {
            xpFunction(player, 500);
        } else if(block.getType() == Material.BEDROCK) {
            xpFunction(player, 1250);
        }
    }

    private void xpFunction(Player player, int xpAmount) {
        int Currentlevel = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".level");
        int Currentxp = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".xp");
        int totalxp = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".totalxp");

        int Nextedlevel = Currentlevel + 1;

        int xpneeded = LevelYML.getLevels().getInt("levels." + Nextedlevel + ".xp");

        if(!LevelYML.getLevels().contains("levels." + Nextedlevel)) {
            plugin.setStatsBoardMaxedLevel(player, Nextedlevel - 1, 0, 0, totalxp);
            return;
        }

        playerLevelYML.getLevels().set("PlayerLevels." + player.getUniqueId() + ".xp", Currentxp + xpAmount);
        playerLevelYML.getLevels().set("PlayerLevels." + player.getUniqueId() + ".totalxp", totalxp + xpAmount);
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

        //player.sendMessage(MessageManager.color("&a+&e" + xpAmount + " &bExperience"));

        int UpdatedCurrentlevel = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".level");

        int NewUpdatedTotalxp = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".totalxp");

        plugin.setStatsBoard(player, UpdatedCurrentlevel, UpdatedCurrentxp, xpneeded, NewUpdatedTotalxp);
    }


}
