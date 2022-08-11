package com.minecraftplugin.fantasyfootball.AdminView;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import static com.minecraftplugin.fantasyfootball.Utilities.Constants.*;

public class ChangePages implements Listener {

    @EventHandler
    public void onPageChange(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if(e.getView().getTitle().contains("Admin view")) {
            e.setCancelled(true);
            if(e.getCurrentItem() != null && e.getSlot() == NEXT_ARROW) {
                e.setCancelled(true);

                p.closeInventory();

                int lastChar = Integer.parseInt(Character.toString(e.getView().getTitle().charAt(e.getView().getTitle().length() - 1)));

                if(lastChar < inv_arr.length - 1) {
                    p.openInventory(inv_arr[lastChar + 1]);
                }
            }

            if(e.getCurrentItem() != null && e.getSlot() == PREVIOUS_ARROW) {
                e.setCancelled(true);

                p.closeInventory();

                int lastChar = Integer.parseInt(Character.toString(e.getView().getTitle().charAt(e.getView().getTitle().length() - 1)));

                if(lastChar > 0) {
                    p.openInventory(inv_arr[lastChar - 1]);
                }
            }

            if(e.getCurrentItem() != null && e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {

                if(gui_content.containsKey(e.getCurrentItem().getItemMeta().getDisplayName())) {
                    Inventory inv = Bukkit.createInventory(null, 54, GUI_ID + "七");

                    inv.setContents(gui_content.get(e.getCurrentItem().getItemMeta().getDisplayName()));
                    p.openInventory(inv);
                }
            }
        }
    }
}
