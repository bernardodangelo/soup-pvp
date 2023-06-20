package br.com.bernardo;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class SoupPvP extends JavaPlugin implements Listener {
    private boolean enableCocoaRecipe;
    private boolean enableCactusRecipe;
    private boolean enableFlowerRecipe;
    private boolean enablePotatoCarrotRecipe;

    @Override
    public void onEnable() {
        loadConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Soup PvP Plugin by Excambaw Enabling");

        if (enableCocoaRecipe) {
            ShapelessRecipe recipeCocoa = new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP));
            recipeCocoa.addIngredient(Material.BOWL);
            recipeCocoa.addIngredient(Material.INK_SACK, 3);
            Bukkit.addRecipe(recipeCocoa);
        }

        if (enableCactusRecipe) {
            ShapelessRecipe recipeCactus = new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP));
            recipeCactus.addIngredient(Material.BOWL);
            recipeCactus.addIngredient(Material.CACTUS);
            Bukkit.addRecipe(recipeCactus);
        }

        if (enableFlowerRecipe) {
            ShapelessRecipe recipeFlower = new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP));
            recipeFlower.addIngredient(Material.BOWL);
            recipeFlower.addIngredient(Material.RED_ROSE);
            recipeFlower.addIngredient(Material.YELLOW_FLOWER);
            Bukkit.addRecipe(recipeFlower);
        }

        if (enablePotatoCarrotRecipe) {
            ShapelessRecipe recipePotatoCarrot = new ShapelessRecipe(new ItemStack(Material.MUSHROOM_SOUP));
            recipePotatoCarrot.addIngredient(Material.BOWL);
            recipePotatoCarrot.addIngredient(Material.POTATO_ITEM);
            recipePotatoCarrot.addIngredient(Material.CARROT_ITEM);
            Bukkit.addRecipe(recipePotatoCarrot);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Soup PvP Plugin by Excambaw Disabling");
    }

    private void loadConfig() {
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        enableCocoaRecipe = config.getBoolean("cocoa");
        enableCactusRecipe = config.getBoolean("cactus");
        enableFlowerRecipe = config.getBoolean("flowers");
        enablePotatoCarrotRecipe = config.getBoolean("potato-carrot");
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
