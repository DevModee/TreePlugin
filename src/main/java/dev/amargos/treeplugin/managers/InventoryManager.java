package dev.amargos.treeplugin.managers;

import dev.amargos.treeplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryManager {
  private final Main plugin;

  public InventoryManager(Main plugin) {
    this.plugin = plugin;
  }

  public void openStatsMenu(Player player, int treesChopped, int level, int coins) {
    ConfigurationSection guiSection = plugin.getConfig().getConfigurationSection("gui");
    if (guiSection == null) {
      player.sendMessage("Error: Configuración de GUI no encontrada.");
      return;
    }

    String menuTitle = guiSection.getString("menu_title", "Menu");
    int size = guiSection.getInt("size", 27); // Tamaño del inventario
    Inventory statsMenu = Bukkit.createInventory(null, size, menuTitle);

    ConfigurationSection itemsSection = guiSection.getConfigurationSection("items");
    if (itemsSection != null) {
      for (String key : itemsSection.getKeys(false)) {
        ConfigurationSection itemConfig = itemsSection.getConfigurationSection(key);
        if (itemConfig != null) {
          Material material = Material.matchMaterial(itemConfig.getString("material", "STONE"));
          int slot = itemConfig.getInt("slot", 0);
          String displayName = itemConfig.getString("display_name", "");
          List<String> lore = itemConfig.getStringList("lore");

          if (material != null) {
            ItemStack item = createItem(material, displayName, lore);
            statsMenu.setItem(slot, item);
          }
        }
      }
    }

    player.openInventory(statsMenu);
  }

  private ItemStack createItem(Material material, String name, List<String> lore) {
    ItemStack item = new ItemStack(material);
    ItemMeta meta = item.getItemMeta();
    if (meta != null) {
      meta.setDisplayName(name);
      meta.setLore(lore.stream().map(line -> line.replace("&", "§")).collect(Collectors.toList()));
      item.setItemMeta(meta);
    }
    return item;
  }
}
