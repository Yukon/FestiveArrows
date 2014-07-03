package me.yukonapplegeek.festivearrows;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEffect implements Listener {
    private final FireworkEffect effect = FireworkEffect.builder()
        .with(FireworkEffect.Type.BALL)
        .withColor(Color.RED, Color.WHITE, Color.BLUE)
        .build();

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(final PlayerDeathEvent event) {
        FestiveArrows.playFirework(event.getEntity().getLocation(), effect);
    }
}
