package me.yukonapplegeek.festivearrows;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class FestiveArrows extends JavaPlugin implements Listener {

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
}