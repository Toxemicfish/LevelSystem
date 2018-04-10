package com.toxemicfish.survival;

import com.toxemicfish.survival.commands.resetStatsBoard;
import com.toxemicfish.survival.events.BlockBreak;
import com.toxemicfish.survival.events.Enderpearl;
import com.toxemicfish.survival.events.Join;
import com.toxemicfish.survival.events.Quit;
import com.toxemicfish.survival.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {

        instance = this;


        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
    }

    private void registerCommands() {
        getCommand("resetlevel").setExecutor(new resetStatsBoard());
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Join(), this);
        pm.registerEvents(new Quit(), this);
        pm.registerEvents(new BlockBreak(), this);
        pm.registerEvents(new Enderpearl(), this);
    }

    public void setStatsBoard(Player player, int level, int xp, int xpNeeded, int totalxp) {
        Scoreboard scoreboard = this.getServer().getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplayName(MessageManager.color("&cStats"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score lvl = objective.getScore(MessageManager.color("Level: &a" + level));
        lvl.setScore(2);
        Score exp = objective.getScore(MessageManager.color("XP: &a" + xp + "&7/&a" + xpNeeded));
        exp.setScore(1);
        Score totalexp = objective.getScore(MessageManager.color("TotalXP: &a" + totalxp));
        totalexp.setScore(0);

        player.setScoreboard(scoreboard);
    }

    public void setStatsBoardMaxedLevel(Player player, int level, int xp, int xpNeeded, int totalxp) {
        Scoreboard scoreboard = this.getServer().getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplayName(MessageManager.color("&cStats"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score lvl = objective.getScore(MessageManager.color("Level: &6" + level));
        lvl.setScore(2);
        Score exp = objective.getScore(MessageManager.color("XP: &a" + xp  + "&7/&a" + xpNeeded));
        exp.setScore(1);
        Score totalexp = objective.getScore(MessageManager.color("TotalXP: &a" + totalxp));
        totalexp.setScore(0);

        player.setScoreboard(scoreboard);
    }

    public static Main getInstance() {
        return instance;
    }
}
