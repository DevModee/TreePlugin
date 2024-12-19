package dev.amargos.treeplugin.managers;

public class PlayerStats {
  private int treesChopped;
  private int level;
  private int coins;

  public PlayerStats() {
    this.treesChopped = 0;
    this.level = 1;
    this.coins = 0;
  }

  public int getTreesChopped() {
    return treesChopped;
  }

  public void setTreesChopped(int treesChopped) {
    this.treesChopped = treesChopped;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getCoins() {
    return coins;
  }

  public void setCoins(int coins) {
    this.coins = coins;
  }
}
