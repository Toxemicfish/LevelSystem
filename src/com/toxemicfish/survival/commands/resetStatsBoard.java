package com.toxemicfish.survival.commands;

import com.toxemicfish.survival.Main;
import com.toxemicfish.survival.utils.LevelYML;
import com.toxemicfish.survival.utils.MessageManager;
import com.toxemicfish.survival.utils.playerLevelYML;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class resetStatsBoard implements CommandExecutor {

    Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            playerLevelYML.getLevels().set("PlayerLevels." + player.getUniqueId() + ".level", 1);
            playerLevelYML.getLevels().set("PlayerLevels." + player.getUniqueId() + ".xp", 0);
            playerLevelYML.getLevels().set("PlayerLevels." + player.getUniqueId() + ".totalxp", 0);
            playerLevelYML.saveLevels();
            playerLevelYML.reloadLevelFile();

            int Currentlevel = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".level");
            int Currentxp = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".xp");
            int totalxp = playerLevelYML.getLevels().getInt("PlayerLevels." + player.getUniqueId() + ".totalxp");

            int Nextedlevel = Currentlevel + 1;

            int xpneeded = LevelYML.getLevels().getInt("levels." + Nextedlevel + ".xp");

            plugin.setStatsBoard(player, Currentlevel, Currentxp, xpneeded, totalxp);

            player.sendMessage(MessageManager.color("&aLevel reset!"));

        } else {
            MessageManager.playerOnly(sender);
        }
        return true;
    }

}
