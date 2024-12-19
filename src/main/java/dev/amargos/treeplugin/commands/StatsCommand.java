package dev.amargos.treeplugin.commands;

import dev.amargos.treeplugin.Main;
import dev.amargos.treeplugin.managers.InventoryManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {
  private final Main plugin;
  private final InventoryManager inventoryManager;

  public StatsCommand(Main plugin) {
    this.plugin = plugin;
    this.inventoryManager = plugin.getInventoryManager();
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("Este comando solo puede ser usado por jugadores.");
      return true;
    }

    Player player = (Player) sender;

    int treesChopped = plugin.getStatsManager().getTreesChopped(player);
    int level = plugin.getStatsManager().getLevel(player);
    int coins = plugin.getStatsManager().getCoins(player);

    inventoryManager.openStatsMenu(player, treesChopped, level, coins);

    return true;
  }
}
