package me.yukonapplegeek.festivearrows;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FestiveArrows extends JavaPlugin implements Listener {

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler
    public void onBowShoot(final EntityShootBowEvent event) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            FireworkEffectPlayer fwp = new FireworkEffectPlayer();
            FireworkEffect fe = FireworkEffect.builder()
                    .with(FireworkEffect.Type.BURST)
                    .withColor(Color.RED, Color.WHITE, Color.BLUE)
                    .withTrail()
                    .build();
            @Override
            public void run() {
                try {
                    fwp.playFirework(event.getProjectile().getWorld(), event.getProjectile().getLocation(), fe);
                }
                catch (Exception exc) {
                    exc.getStackTrace();
                }
            }
        }, 15);
    }
    
}