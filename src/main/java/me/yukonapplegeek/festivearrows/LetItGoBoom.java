package me.yukonapplegeek.festivearrows;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class LetItGoBoom implements Listener {
    private final FireworkEffect effect = FireworkEffect.builder()
        .with(FireworkEffect.Type.BURST)
        .withColor(Color.RED, Color.WHITE, Color.BLUE)
        .withTrail()
        .build();

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (FestiveArrows.get().letItGoBoomEnabled) {
            FestiveArrows.playFirework(event.getFrom(), effect);
        }
    }
}
