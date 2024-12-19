package dev.amargos.treeplugin.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemManager {
  private final FileConfiguration coinsConfig;

  public ItemManager(FileConfiguration coinsConfig) {
    this.coinsConfig = coinsConfig;
  }

  public ItemStack getCoinItem() {
    String name = coinsConfig.getString("coins.name", "&7Default Coin");
    List<String> lore = coinsConfig.getStringList("coins.lore");
    String materialName = coinsConfig.getString("coins.item", "GOLD_NUGGET");
    Material material = Material.getMaterial(materialName.toUpperCase());
    if (material == null) material = Material.STICK;

    ItemStack item = new ItemStack(material);
    ItemMeta meta = item.getItemMeta();
    if (meta != null) {
      meta.setDisplayName(name);
      meta.setLore(lore);
      item.setItemMeta(meta);
    }
    return item;
  }
}
