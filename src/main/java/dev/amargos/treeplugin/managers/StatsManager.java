package dev.amargos.treeplugin.managers;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class StatsManager {
  private final Map<Player, PlayerStats> playerStats;

  public StatsManager() {
    this.playerStats = new HashMap<>();
  }

  public void addTreeChopped(Player player) {
    PlayerStats stats = getStats(player);
    stats.setTreesChopped(stats.getTreesChopped() + 1);
  }

  public void addCoins(Player player, int amount) {
    PlayerStats stats = getStats(player);
    stats.setCoins(stats.getCoins() + amount);
  }

  public void setLevel(Player player, int level) {
    PlayerStats stats = getStats(player);
    stats.setLevel(level);
  }

  public int getTreesChopped(Player player) {
    return getStats(player).getTreesChopped();
  }

  public int getLevel(Player player) {
    return getStats(player).getTreesChopped();
  }

  public int getCoins(Player player) {
    return getStats(player).getCoins();
  }

  private PlayerStats getStats(Player player) {
    return playerStats.computeIfAbsent(player, p -> new PlayerStats());
  }

  public void resetStats(Player player) {
    playerStats.remove(player);
  }

  public void saveStats(Player player) {

  }

  public void loadStats(Player player) {

  }
}
