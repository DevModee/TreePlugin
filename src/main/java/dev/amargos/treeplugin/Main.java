package dev.amargos.treeplugin;

import dev.amargos.treeplugin.commands.ReloadCommand;
import dev.amargos.treeplugin.commands.StatsCommand;
import dev.amargos.treeplugin.commands.WandCommand;
import dev.amargos.treeplugin.listeners.TreeListener;
import dev.amargos.treeplugin.managers.InventoryManager;
import dev.amargos.treeplugin.managers.StatsManager;
import dev.amargos.treeplugin.managers.ZoneManager;
import dev.amargos.treeplugin.tasks.TreeRespawnTask;
import dev.amargos.treeplugin.utils.MessageManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {
    private final HashMap<UUID, Integer> playerCoins = new HashMap<>();
    private MessageManager messageManager;
    private ZoneManager zoneManager;
    private StatsManager statsManager;
    private InventoryManager inventoryManager;

  @Override
    public void onEnable() {
        getLogger().info("TreePlugin activado correctamente.");
        saveDefaultConfig();
        saveResource("messages.yml", false);
        registerListeners();

        messageManager = new MessageManager(this);
        zoneManager = new ZoneManager(getConfig().getConfigurationSection("zones"));

        zoneManager.getSpawnBlocks().forEach((zoneName, locations) -> {
          getLogger().info("Zona: " + zoneName + " tiene " + locations.size() + " bloques de spawn.");
        });

        getCommand("reload").setExecutor(new ReloadCommand(this));
        getCommand("stats").setExecutor(new StatsCommand(this));
        getCommand("wand").setExecutor(new WandCommand(this));

    }

    @Override
    public void onDisable() {
        getLogger().info("TreePlugin desactivado correctamente.");
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new TreeListener(this), this);
    }

    public HashMap<UUID, Integer> getPlayerCoins() {
        return playerCoins;
    }

  public MessageManager getMessageManager() {
    return messageManager;
  }

  public ZoneManager getZoneManager() {
    return zoneManager;
  }

  public StatsManager getStatsManager() {
    return statsManager;
  }

  public InventoryManager getInventoryManager() {
    return this.inventoryManager;
  }

}