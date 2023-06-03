package br.com.bernardo;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class PvPSoup extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("PvP Soup Plugin Starting");

        ShapelessRecipe recipeCocoa = new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP));
        recipeCocoa.addIngredient(Material.BOWL);
        recipeCocoa.addIngredient(Material.INK_SACK, 3);
        Bukkit.addRecipe(recipeCocoa);

        ShapelessRecipe recipeCactus = new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP));
        recipeCactus.addIngredient(Material.BOWL);
        recipeCactus.addIngredient(Material.CACTUS);
        Bukkit.addRecipe(recipeCactus);

        ShapelessRecipe recipeFlower = new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP));
        recipeFlower.addIngredient(Material.BOWL);
        recipeFlower.addIngredient(Material.RED_ROSE);
        recipeFlower.addIngredient(Material.YELLOW_FLOWER);
        Bukkit.addRecipe(recipeFlower);
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
