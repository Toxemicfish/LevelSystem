package com.toxemicfish.survival.utils;

import org.bukkit.entity.Player;

public class Check {

    public static int levelCheck(Player player) {
        return playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".level");

    }

}
