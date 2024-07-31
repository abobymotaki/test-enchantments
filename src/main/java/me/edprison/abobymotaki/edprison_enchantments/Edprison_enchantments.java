package me.edprison.abobymotaki.edprison_enchantments;

import com.edwardbelt.edprison.api.models.EnchantModel;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;


public final class Edprison_enchantments extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        registerEnchantments();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerEnchantments() {
        // Register custom enchantments
        new tornado();
        // Add other enchantments here
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        // Check if the player has the Tornado enchantment
        if (hasTornadoEnchantment(player)) {
            EnchantModel enchants = new EnchantModel();
            enchants.getLevel(player.getUniqueId(), "tornado");
        }
    }

    private boolean hasTornadoEnchantment(Player player) {
        return true;
    }
}