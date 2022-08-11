package com.minecraftplugin.fantasyfootball.Events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static com.minecraftplugin.fantasyfootball.Utilities.Constants.*;


public class GuiClickEvent implements Listener {

    @EventHandler
    public void onGuiMove(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(GUI_ID)) {

            if(e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.MAP && e.getCurrentItem().getItemMeta().getCustomModelData() == 1010)
                e.setCancelled(true);

            if(e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.MAP && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BOLD + "" + ChatColor.GREEN + "CONFIRM")) {
                e.setCancelled(true);

            }
        }
    }
}
