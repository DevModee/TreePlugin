package dev.amargos.treeplugin.tasks;

import dev.amargos.treeplugin.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class TreeRespawnTask extends BukkitRunnable {
  private final Main plugin;

  public TreeRespawnTask(Main plugin) {
    this.plugin = plugin;
  }

  @Override
  public void run() {
    List<Location> spawnBlocks = plugin.getSpawnBlocks();

    for (Location loc : spawnBlocks) {
      if (loc.getBlock().getType() == Material.AIR) {
        loc.getBlock().setType(Material.LOG);
      }
    }
  }
}
