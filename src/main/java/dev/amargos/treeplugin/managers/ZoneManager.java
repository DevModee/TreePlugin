package dev.amargos.treeplugin.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZoneManager {
  private final ConfigurationSection zonesSection;

  public ZoneManager(ConfigurationSection zonesSection) {
    this.zonesSection = zonesSection;
  }

  public Map<String, List<Location>> getSpawnBlocks() {
    Map<String, List<Location>> spawnBlocks = new HashMap<>();

    if (zonesSection != null) {
      for (String zoneKey : zonesSection.getKeys(false)) {
        ConfigurationSection zone = zonesSection.getConfigurationSection(zoneKey);
        if (zone != null) {
          String zoneName = zone.getString("name", "Unnamed zone");
          String worldName = zone.getString("world", "world");
          World world = Bukkit.getWorld(worldName);

          if (world != null) {
            List<Location> zoneSpawnBlocks = new ArrayList<>();
            List<Map<?, ?>> blocks = zone.getMapList("spawn_blocks");

            for (Map<?, ?> block : blocks) {
              Object xObj = block.get("x");
              Object yObj = block.get("y");
              Object zObj = block.get("z");

              if (xObj instanceof Number && yObj instanceof Number && zObj instanceof Number) {
                int x = ((Number) xObj).intValue();
                int y = ((Number) yObj).intValue();
                int z = ((Number) zObj).intValue();
                zoneSpawnBlocks.add(new Location(world, x, y, z));
              } else {
                Bukkit.getLogger().warning("Coordenadas invalidas en 'spawn_blocks' para una zona.");
              }
            }
            spawnBlocks.put(zoneName, zoneSpawnBlocks);
          } else {
            // Si el mundo no existe manda una advertencia
            Bukkit.getLogger().warning("El mundo '" + worldName + "' no está cargado");
          }
        }
      }
    }
    return spawnBlocks;
  }
}
