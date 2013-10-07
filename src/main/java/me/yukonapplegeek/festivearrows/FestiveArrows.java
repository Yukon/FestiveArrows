package me.yukonapplegeek.festivearrows;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
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
    public void onBowShoot(EntityShootBowEvent event) {
        FireworkMeta fireworkMeta = (FireworkMeta) (new ItemStack(Material.FIREWORK)).getItemMeta();
        Firework firework = (Firework) event.getProjectile().getLocation().getWorld().spawnEntity(event.getProjectile().getLocation(), EntityType.FIREWORK);

             fireworkMeta.addEffect(FireworkEffect.builder()
                                             .with(Type.CREEPER)
                                             .withColor(Color.ORANGE).withColor(Color.WHITE)
                                             .withFade(Color.WHITE).withFade(Color.BLACK)
                                             .withTrail()
                                             .build());
        firework.setFireworkMeta(fireworkMeta);
        event.getProjectile().setPassenger(firework);
    }

    
}
