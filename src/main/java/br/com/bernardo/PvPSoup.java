package br.com.bernardo;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class PvPSoup extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("PvP Soup Plugin Starting");
    }

    @Override
    public void onDisable() {
        getLogger().info("PvP Soup Plugin Finishing");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (event.getAction().toString().contains("RIGHT") && item != null && item.getType() == Material.MUSHROOM_SOUP) {
            if (player.getHealth() < player.getMaxHealth()) {
                double newHealth = Math.min(player.getHealth() + 7, player.getMaxHealth());
                player.setHealth(newHealth);
                item.setType(Material.BOWL);
            } else if (player.getFoodLevel() < 20) {
                player.setFoodLevel(Math.min(player.getFoodLevel() + 6, 20));
                item.setType(Material.BOWL);
            }

            event.setCancelled(true);
        }
    }
}
