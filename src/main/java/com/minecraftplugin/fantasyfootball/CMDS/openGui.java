package com.minecraftplugin.fantasyfootball.CMDS;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.minecraftplugin.fantasyfootball.Utilities.Constants.*;
import static com.minecraftplugin.fantasyfootball.Utilities.Helper.*;


public class openGui implements CommandExecutor {

    private void openGUI(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, GUI_ID);

        ItemStack emptyMap = createItemWithCustomModelData(ChatColor.GREEN, "", Material.MAP, 1010);
        ItemStack confirm = createItemWithCustomModelData(ChatColor.GREEN, "CONFIRM", Material.MAP, 1010);
        ItemStack neverMind = createItemWithCustomModelData(ChatColor.RED, "NEVERMIND", Material.MAP, 1010);

        for(int i = 0; i < arr.length; i++) {
            inv.setItem(arr[i], emptyMap);
        }

        inv.setItem(37, confirm);
        inv.setItem(46, confirm);

        inv.setItem(39, neverMind);
        inv.setItem(48, neverMind);

        if(gui_content.containsKey(player.getName()))
            inv.setContents(gui_content.get(player.getName()));
            player.openInventory(inv);

        player.openInventory(inv);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(cmd.getName().equalsIgnoreCase("gui")) {
            Player player = (Player) sender;
            openGUI(player);
        }
        return true;
    }
}