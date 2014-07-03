package me.yukonapplegeek.festivearrows;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BowEffect implements Listener {
    private final FireworkEffect effect = FireworkEffect.builder()
        .with(FireworkEffect.Type.BURST)
        .withColor(Color.RED, Color.WHITE, Color.BLUE)
        .withTrail()
        .build();

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBowShoot(final EntityShootBowEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                FestiveArrows.playFirework(event.getProjectile().getLocation(), effect);
            }
        }.runTaskLater(FestiveArrows.get(), FestiveArrows.get().getConfig().getInt("bow.delay", 15));
    }
}
