package com.toxemicfish.survival.utils;

import com.toxemicfish.survival.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

public class blockLevelYML {

    Main plugin = Main.getInstance();

    public static YamlConfiguration blockLevel = null;
    public static File blockLevelFile = null;

    public static void reloadblockLevelFile() {
        if (blockLevelFile == null) {
            blockLevelFile = new File(Bukkit.getPluginManager().getPlugin("Survival").getDataFolder(), "blockLevels.yml");
        }
        blockLevel = YamlConfiguration.loadConfiguration(blockLevelFile);

        InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("Survival").getResource("blockLevels.yml");
        if (defConfigStream != null) {
            YamlConfiguration defconfig = YamlConfiguration.loadConfiguration(defConfigStream);
            if (!blockLevelFile.exists() || blockLevelFile.length() == 0L) {
                blockLevel.setDefaults(defconfig);
            }
        }
    }

    public static FileConfiguration getblockLevels() {
        if (blockLevel == null) {
            reloadblockLevelFile();
        }
        return blockLevel;
    }

    public static void saveblockLevels() {
        if (blockLevel == null || blockLevelFile == null) {
            return;
        }
        try {
            getblockLevels().save(blockLevelFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + blockLevelFile, ex);
        }
    }
}
