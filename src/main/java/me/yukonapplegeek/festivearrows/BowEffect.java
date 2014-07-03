package me.yukonapplegeek.festivearrows;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.scheduler.BukkitRunnable;

public class BowEffect implements Listener {
    public static final String IDENTIFIER = "Festive-Arrow";
    public static final String EXPLODED_IDENTIFIER = "Festive-Arrow-Sploded";

    private final FireworkEffect effect = FireworkEffect.builder()
        .with(FireworkEffect.Type.BURST)
        .withColor(Color.RED, Color.WHITE, Color.BLUE)
        .withTrail()
        .build();

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBowShoot(final EntityShootBowEvent event) {
        if (event.getProjectile() instanceof Arrow) {
            final Arrow arrow = (Arrow) event.getProjectile();
            // Mark the arrow as "festive"
            arrow.setMetadata(IDENTIFIER, new FixedMetadataValue(FestiveArrows.get(), true));

            new BukkitRunnable() {
                @Override
                public void run() {
                    // Only play the effect out if another event hasn't taken place beforehand
                    // and that arrow is festive
                    if (!arrow.isDead() && !metadataAsBoolean(arrow, EXPLODED_IDENTIFIER)) {
                        BowEffect.this.playEffect(arrow);
                    }
                }
            }.runTaskLater(FestiveArrows.get(),FestiveArrows.get().getConfig().getInt("bow.delay", 15));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onArrowCollide(EntityDamageByEntityEvent event) {
        this.playEffect(event.getDamager());
    }

    @EventHandler
    public void onArrowInBlock(ProjectileHitEvent event) {
        this.playEffect(event.getEntity());
    }

    private void playEffect(Entity projectile) {
        if (projectile instanceof Arrow) {
            Arrow arrow = (Arrow) projectile;
            if (this.metadataAsBoolean(arrow, IDENTIFIER) && !arrow.isDead()) {
                FestiveArrows.playFirework(projectile.getLocation(), effect);
                arrow.setMetadata(EXPLODED_IDENTIFIER, new FixedMetadataValue(FestiveArrows.get(), true));
            }
        }
    }

    /**
     * Util method for determining if a given metadata value is true as a boolean.
     *
     * @param metadatable The metadata container.
     * @param identifier The metadata identifier.
     *
     * @return Whether or not that boolean is true.
     */
    private boolean metadataAsBoolean(Metadatable metadatable, String identifier) {
        for (MetadataValue value : metadatable.getMetadata(identifier)) {
            if (value.getOwningPlugin() == FestiveArrows.get()) {
                return value.asBoolean();
            }
        }

        return false;
    }
}
