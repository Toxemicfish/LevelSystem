package com.toxemicfish.survival.utils;

import com.toxemicfish.survival.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

public class LevelYML {

    Main plugin = Main.getInstance();

    public static YamlConfiguration level = null;
    public static File levelFile = null;

    public static void reloadLevelFile() {
        if (levelFile == null) {
            levelFile = new File(Bukkit.getPluginManager().getPlugin("Survival").getDataFolder(), "levels.yml");
        }
        level = YamlConfiguration.loadConfiguration(levelFile);

        InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("Survival").getResource("levels.yml");
        if (defConfigStream != null) {
            YamlConfiguration defconfig = YamlConfiguration.loadConfiguration(defConfigStream);
            if (!levelFile.exists() || levelFile.length() == 0L) {
                level.setDefaults(defconfig);
            }
        }
    }

    public static FileConfiguration getLevels() {
        if (level == null) {
            reloadLevelFile();
        }
        return level;
    }

    public static void saveLevels() {
        if (level == null || levelFile == null) {
            return;
        }
        try {
            getLevels().save(levelFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + levelFile, ex);
        }
    }
}
