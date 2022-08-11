package com.minecraftplugin.fantasyfootball.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static com.minecraftplugin.fantasyfootball.Utilities.Constants.*;

public class Helper {
    public static Inventory[] addItemInventory(Inventory arr[], Inventory inv)
    {
        int i;

        Inventory[] newarr = new Inventory[arr.length + 1];

        for (i = 0; i < arr.length; i++)
            newarr[i] = arr[i];

        newarr[arr.length] = inv;

        return newarr;
    }

    public static void lockEvent(InventoryClickEvent e, Player p, boolean locked, String message, Sound sound) {
        e.setCancelled(true);
        p.sendMessage(ChatColor.GREEN + PLUGIN_ID + " " + message);
        p.playSound(p.getLocation(), sound, 100, 1);
        p.closeInventory();
    }

    public static ItemStack createItemWithCustomModelData(ChatColor color, String name, Material item, int modelData) {

        ItemStack newItem = new ItemStack(item);
        ItemMeta itemMeta = newItem.getItemMeta();
        itemMeta.setDisplayName(color + name);
        itemMeta.setCustomModelData(modelData);
        newItem.setItemMeta(itemMeta);

        return newItem;
    }
}
