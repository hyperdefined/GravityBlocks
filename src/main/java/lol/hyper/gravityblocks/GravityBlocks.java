package lol.hyper.gravityblocks;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class GravityBlocks extends JavaPlugin implements Listener {

    public static List<Chunk> chunks;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerLoadChunk(ChunkLoadEvent event) {
        Chunk chunk = event.getChunk();
        Bukkit.getLogger().info("Getting chunk: " + chunk);
        World world = Bukkit.getWorld("world");
        World nether = Bukkit.getWorld("world_nether");
        World end = Bukkit.getWorld("world_the_end");
        if (!chunks.contains(chunk)) {
            Bukkit.getLogger().info("Chunk has not been loaded.");
            chunks.add(chunk);
            int cx = chunk.getX() << 4;
            int cz = chunk.getZ() << 4;
            for (int x = cx; x < cx + 16; x++) {
                for (int z = cz; z < cz + 16; z++) {
                    for (int y = 0; y < 128; y++) {
                        if (chunk.getWorld().equals(world)) {
                            Bukkit.getLogger().info("Chunk is overworld, applying gravity...");
                            Block block = world.getBlockAt(x, y, z);
                            block.getWorld().spawnFallingBlock(block.getLocation(), block.getType(), block.getData());
                            block.setType(Material.AIR);
                        }
                        if (chunk.getWorld().equals(nether)) {
                            Bukkit.getLogger().info("Chunk is nether, applying gravity...");
                            Block block = nether.getBlockAt(x, y, z);
                            block.getWorld().spawnFallingBlock(block.getLocation(), block.getType(), block.getData());
                            block.setType(Material.AIR);
                        }
                        if (chunk.getWorld().equals(end)) {
                            Bukkit.getLogger().info("Chunk is end, applying gravity...");
                            Block block = end.getBlockAt(x, y, z);
                            block.getWorld().spawnFallingBlock(block.getLocation(), block.getType(), block.getData());
                            block.setType(Material.AIR);
                        }
                    }
                }
            }
        }
    }
}
