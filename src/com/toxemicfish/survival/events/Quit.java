package com.toxemicfish.survival.events;

import com.toxemicfish.survival.Main;
import com.toxemicfish.survival.utils.playerLevelYML;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener {

    Main plugin = Main.getInstance();

    @EventHandler
    public void quit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (playerLevelYML.getLevels().contains("PlayerLevels." + player.getUniqueId())) {

        }
    }

}
