package com.toxemicfish.survival.events;

import com.toxemicfish.survival.Main;
import com.toxemicfish.survival.utils.LevelYML;
import com.toxemicfish.survival.utils.MessageManager;
import com.toxemicfish.survival.utils.playerLevelYML;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    Main plugin = Main.getInstance();

    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if(!player.hasPlayedBefore()) {
            player.sendMessage(MessageManager.color("&aWelcome, your level is &e0"));

            playerLevelYML.getLevels().set("PlayerLevels." + player.getUniqueId() + ".level", 1);
            playerLevelYML.getLevels().set("PlayerLevels." + player.getUniqueId() + ".xp", 0);
            playerLevelYML.saveLevels();

            int level = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".level");

            int Nextedlevel = level + 1;

            int xpneeded = LevelYML.getLevels().getInt("levels." + Nextedlevel + ".xp");

            plugin.setStatsBoard(player, 1, 0, xpneeded, 0);
        } else {
            int level = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".level");
            int xp = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".xp");
            int totalxp = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".totalxp");

            int Nextedlevel = level + 1;

            int xpneeded = LevelYML.getLevels().getInt("levels." + Nextedlevel + ".xp");

            plugin.setStatsBoard(player, level, xp, xpneeded, totalxp);
        }
    }

}
