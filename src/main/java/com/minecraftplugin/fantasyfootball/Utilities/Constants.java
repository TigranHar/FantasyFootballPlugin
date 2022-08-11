package com.minecraftplugin.fantasyfootball.Utilities;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static int[] arr = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 14, 15, 16, 17, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};

    public static Inventory[] inv_arr = new Inventory[] {};
    public static final String INV_NAME = "Admin view ";
    public static final int SLOT_NUM = 54;
    public static final int PREVIOUS_ARROW = SLOT_NUM - 9;
    public static final int NEXT_ARROW = SLOT_NUM - 1;

    public static final String PLUGIN_ID = "[Fantasy Football] ";
    public static final String GUI_ID = ChatColor.WHITE + "七七七七七七七ㇺ";
    public static final String GUI_CONTENT = "gui_content";
    public static final String GUI_LOCKED = "gui_locked";

    public static Map<String, ItemStack[]> gui_content = new HashMap<String,ItemStack[]>();
    public static Map<String, Boolean> gui_locked = new HashMap<String, Boolean>();

    public static final ItemStack NEXT_ARROW_ITEM = new ItemStack(Material.SPECTRAL_ARROW);
    public static final ItemStack PREVIOUS_ARROW_ITEM = new ItemStack(Material.SPECTRAL_ARROW);
}
