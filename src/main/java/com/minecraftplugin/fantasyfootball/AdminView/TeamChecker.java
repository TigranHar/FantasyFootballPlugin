package com.minecraftplugin.fantasyfootball.AdminView;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Map;

import static com.tigran.fantasyfootball118.fantasyfootball.Utilities.Constants.*;
import static com.tigran.fantasyfootball118.fantasyfootball.Utilities.Helper.addItemInventory;

public class TeamChecker implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        int[] gui_arr = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
        //
        int cycle = 0;
        ItemMeta next_arrow_meta = NEXT_ARROW_ITEM.getItemMeta();
        ItemMeta previous_arrow_meta = PREVIOUS_ARROW_ITEM.getItemMeta();
        next_arrow_meta.setDisplayName("Next Page");
        previous_arrow_meta.setDisplayName("Previous Page");
        NEXT_ARROW_ITEM.setItemMeta(next_arrow_meta); PREVIOUS_ARROW_ITEM.setItemMeta(previous_arrow_meta);

        if(cmd.getName().equalsIgnoreCase("admin-gui")) {
            Player player = (Player) sender;
            Inventory inv = Bukkit.createInventory(null, SLOT_NUM, INV_NAME + cycle);
            inv_arr = new Inventory[]{};
            int i = 0;

            for(Map.Entry <String, ItemStack[]> entry : gui_content.entrySet()) {
                ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
                SkullMeta playerHeadItemMeta = (SkullMeta) playerHead.getItemMeta();
                playerHeadItemMeta.setDisplayName(entry.getKey());
                playerHead.setItemMeta(playerHeadItemMeta);

                inv.setItem(gui_arr[i],playerHead);

                if(i == 0) {
                    inv.setItem(PREVIOUS_ARROW, PREVIOUS_ARROW_ITEM);
                    inv.setItem(NEXT_ARROW, NEXT_ARROW_ITEM);
                    inv_arr = addItemInventory(inv_arr, inv);
                }

                if(i < gui_arr.length - 1) i++;

                if(i >= gui_arr.length - 1 || i >= SLOT_NUM - 9) {
                    i = 0;
                    cycle++;
                    inv.setItem(PREVIOUS_ARROW, PREVIOUS_ARROW_ITEM);
                    inv.setItem(NEXT_ARROW, NEXT_ARROW_ITEM);
                    inv = Bukkit.createInventory(null, SLOT_NUM, INV_NAME + cycle);
                }
            }

            player.openInventory(inv_arr[0]);
        }
        return true;
    }
}
