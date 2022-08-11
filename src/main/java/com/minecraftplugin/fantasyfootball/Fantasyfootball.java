package com.minecraftplugin.fantasyfootball;

import com.minecraftplugin.fantasyfootball.AdminView.ChangePages;
import com.minecraftplugin.fantasyfootball.AdminView.TeamChecker;
import com.minecraftplugin.fantasyfootball.CMDS.openGui;
import com.minecraftplugin.fantasyfootball.Events.GuiClickEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

import static com.minecraftplugin.fantasyfootball.Utilities.Constants.*;
import static com.minecraftplugin.fantasyfootball.Utilities.Helper.*;

public final class Fantasyfootball extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.print(PLUGIN_ID + "Enabled");
        this.saveDefaultConfig();
        this.getCommand("gui").setExecutor(new openGui());
        getServer().getPluginCommand("admin-gui").setExecutor(new TeamChecker());
        getServer().getPluginManager().registerEvents(new GuiClickEvent(), this);
        getServer().getPluginManager().registerEvents(new ChangePages(), this);
        this.getServer().getPluginManager().registerEvents(this,this);

        if(this.getConfig().contains(GUI_CONTENT)) {
            this.loadGuiContent();
            this.getConfig().set(GUI_CONTENT, null);
            this.getConfig().set(GUI_LOCKED, null);
            this.saveConfig();
        }
    }

    @Override
    public void onDisable() {
        System.out.print(PLUGIN_ID + "Disabled");

        if(!gui_content.isEmpty()) {
            this.saveGuiContent();
        }
    }

    public void saveGuiContent(){
        for(Map.Entry<String, ItemStack[]>entry : gui_content.entrySet()){
            this.getConfig().set("gui_content."+entry.getKey(),entry.getValue());
        }

        for(Map.Entry<String, Boolean> entry : gui_locked.entrySet()){
            this.getConfig().set("gui_locked."+ entry.getKey(), entry.getValue());
        }

        this.saveConfig();
    }

    public void loadGuiContent(){
        this.getConfig().getConfigurationSection(GUI_CONTENT).getKeys(false).forEach(key->{
            if(!key.contains("locked")) {
                @SuppressWarnings("unchecked")
                ItemStack[]content = ((List<ItemStack>) this.getConfig().get("gui_content." + key)).toArray(new ItemStack[0]);
                gui_content.put(key,content);
            }
        });

        this.getConfig().getConfigurationSection(GUI_LOCKED).getKeys(false).forEach(key->{
            boolean value = this.getConfig().getBoolean("gui_locked."+ key);
            gui_locked.put(key, value);
        });
    }


    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase(GUI_ID)) {
            gui_content.put(e.getPlayer().getName(), e.getInventory().getContents());
        }
    }

    @EventHandler
    public void onGuiMove(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(GUI_ID)) {

            if(e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.MAP && e.getCurrentItem().getItemMeta().getCustomModelData() == 1010)
                e.setCancelled(true);

            if(e.getView().getTitle().equalsIgnoreCase(GUI_ID) && gui_locked.get(p.getName() + "locked") != null) {
                if(gui_locked.get(p.getName() + "locked").booleanValue()) {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.RED + PLUGIN_ID + " Can't change lineup, already locked in");
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 100, 1);
                }
            }

            if(e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.MAP) {

                String disName = e.getCurrentItem().getItemMeta().getDisplayName();

                if(disName.equalsIgnoreCase(ChatColor.GREEN + "CONFIRM")) {
                    lockEvent(e, p, true, "Successfully locked in lineup", Sound.BLOCK_NOTE_BLOCK_BELL);
                    gui_locked.put(p.getName() + "locked", true);
                }


                if(disName.equalsIgnoreCase(ChatColor.RED + "NEVERMIND")) {
                    lockEvent(e, p, false, "Successfully unlocked lineup", Sound.BLOCK_NOTE_BLOCK_BELL);
                    gui_locked.put(p.getName() + "locked", false);
                }
            }
        }

        if(e.getView().getTitle().equalsIgnoreCase(GUI_ID + "七")) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + PLUGIN_ID + " Can't change lineup, lineup can only be changed by the lineup owner");
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 100, 1);
        }
    }
}
