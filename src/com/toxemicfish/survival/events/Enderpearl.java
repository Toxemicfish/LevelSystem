package com.toxemicfish.survival.events;

import com.toxemicfish.survival.utils.Check;
import com.toxemicfish.survival.utils.MessageManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Enderpearl implements Listener {


    @EventHandler
    public void onEat(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if((event.getAction() == Action.RIGHT_CLICK_BLOCK || (event.getAction() == Action.RIGHT_CLICK_AIR)))
        {
             if(player.getItemInHand().getType() == Material.ENDER_PEARL) {
                 if(Check.levelCheck(player) < 7) {
                     player.sendMessage(MessageManager.color("&aYou must me level 7 your higher to use this item!"));
                     player.sendMessage(MessageManager.color("&6" + Check.levelCheck(player)));
                     event.setCancelled(true);
                 }
             }
        }
    }

}
