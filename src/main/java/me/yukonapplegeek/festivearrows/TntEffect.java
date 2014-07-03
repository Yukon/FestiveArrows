package me.yukonapplegeek.festivearrows;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class TntEffect implements Listener {
    private final FireworkEffect effect = FireworkEffect.builder()
        .with(FireworkEffect.Type.CREEPER)
        .withColor(Color.GREEN)
        .build();

    @EventHandler(priority = EventPriority.MONITOR)
    public void onTntExplode(EntityExplodeEvent event) {
        if (event.getEntity() instanceof TNTPrimed) {
            FestiveArrows.playFirework(event.getLocation(), effect);
        }
    }
}
