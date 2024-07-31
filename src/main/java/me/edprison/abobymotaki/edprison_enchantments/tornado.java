package me.edprison.abobymotaki.edprison_enchantments;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import com.edwardbelt.edprison.enchantments.manager.obj.Enchantment;

import java.util.Objects;

public final class tornado extends Enchantment {

    public tornado() {
        super("tornado", "BlockBreakEvent");
    }

    @Override
    public void execute(Player player, Block block) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Location blockLocation = block.getLocation();
                double radius = 3.0;
                double height = 5.0;
                double speed = 0.2;
                int iterations = 0;

                while (iterations < 100) {
                    double angle = iterations * speed;
                    double x = radius * Math.cos(angle);
                    double y = height * (iterations / 100.0);
                    double z = radius * Math.sin(angle);

                    Location newLocation = blockLocation.clone().add(x, y, z);
                    block.getWorld().spawnParticle(Particle.SWEEP_ATTACK, newLocation, 5);
                    iterations++;

                    if (newLocation.getBlockY() >= blockLocation.getBlockY() + height) {
                        break;
                    }
                }

                createExplosion(blockLocation);
                rewardPlayer(player, iterations);
                despawnEntity(block);
                cancel();
            }
        }.runTaskTimer(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("Edprison_enchantments")), 0, 1L);

        player.sendMessage("§eTornado enchantment activated!");
    }

    private void createExplosion(Location location) {
        Objects.requireNonNull(location.getWorld()).createExplosion(location, 4F, false, false);
    }

    private void rewardPlayer(Player player, double blocks) {
        // Implement your reward logic here. For example:
        // player.getInventory().addItem(new ItemStack(Material.DIAMOND, (int) (blocks * 0.1)));
        player.sendMessage("Tornado has triggered! received" + blocks);
    }

    private void despawnEntity(Block block) {
        block.setType(Material.AIR);
    }
}