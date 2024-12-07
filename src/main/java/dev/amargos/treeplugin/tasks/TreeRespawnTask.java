package dev.amargos.treeplugin.tasks;

import dev.amargos.treeplugin.managers.ZoneManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

public class TreeRespawnTask extends BukkitRunnable {
  private final ZoneManager zoneManager;

  public TreeRespawnTask(ZoneManager zoneManager) {
    this.zoneManager = zoneManager;
  }

  @Override
  public void run() {
    zoneManager.getSpawnBlocks().forEach((zoneName, locations) -> {
      for (Location loc : locations) {
        if (loc.getBlock().getType() == Material.AIR) {
          loc.getBlock().setType(Material.LOG);
        }
      }
    });
  }
}
