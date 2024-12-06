package dev.amargos.treeplugin.listeners;

import dev.amargos.treeplugin.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class ZoneSelector implements Listener {
  private final Main plugin;
  private final HashMap<UUID, Location> firstPositions = new HashMap<>();
  private final HashMap<UUID, Location> secondPositions = new HashMap<>();

  public ZoneSelector(Main plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onPlayerInterect(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    ItemStack item = player.getInventory().getItemInHand();

    if (item.getType() == Material.STICK && item.getItemMeta() != null && "Zone selector".equals(item.getItemMeta().getDisplayName())) {
      event.setCancelled(true);
      Location clickedBlock = event.getClickedBlock().getLocation();

      if (event.getAction().toString().contains("LEFT_CLICK")) {
        firstPositions.put(player.getUniqueId(), clickedBlock);
        String message = plugin.getMessageManager().getMessage(
                "messages.zone-selector.first-point-set",
                "%location%", clickedBlock.toString()
        );
        player.sendMessage(message);
      } else if (event.getAction().toString().contains("RIGHT_CLICK")) {
        secondPositions.put(player.getUniqueId(), clickedBlock);
        String message = plugin.getMessageManager().getMessage(
                "messages.zone-selector.second-point-set",
                "%location%", clickedBlock.toString()
        );
        player.sendMessage(message);
      }

      if (firstPositions.containsKey(player.getUniqueId())) {
        Location pos1 = firstPositions.get(player.getUniqueId());
        Location pos2 = clickedBlock;

        FileConfiguration config = plugin.getConfig();
        String zoneName = "Zone" + (config.getConfigurationSection("zones") == null ? 1 : config.getConfigurationSection("zones").getKeys(false).size() + 1);

        config.set("zones." + zoneName + ".pos1.x", pos1.getBlockX());
        config.set("zones." + zoneName + ".pos1.y", pos1.getBlockY());
        config.set("zones." + zoneName + ".pos1.z", pos1.getBlockZ());

        config.set("zones." + zoneName + ".pos2.x", pos1.getBlockX());
        config.set("zones." + zoneName + ".pos2.y", pos1.getBlockY());
        config.set("zones." + zoneName + ".pos2.z", pos1.getBlockZ());

        plugin.saveConfig();;
        player.sendMessage("Zone guardada como " + zoneName);
      }

    }
  }
}
