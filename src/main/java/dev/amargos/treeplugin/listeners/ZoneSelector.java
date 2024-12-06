package dev.amargos.treeplugin.listeners;

import dev.amargos.treeplugin.Main;
import org.bukkit.Location;
import org.bukkit.Material;
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
    }
  }
}
