package dev.amargos.treeplugin.commands;

import dev.amargos.treeplugin.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WandCommand implements CommandExecutor {
  private final Main plugin;

  public WandCommand(Main plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage(plugin.getMessageManager().getMessage("only_players"));
      return true;
    }

    Player player = (Player) sender;
    ItemStack wand = new ItemStack(Material.STICK);
    ItemMeta meta = wand.getItemMeta();
    if (meta != null) {
      meta.setDisplayName(plugin.getMessageManager().getMessage("wand_name"));
      wand.setItemMeta(meta);
    }
    player.getInventory().addItem(wand);
    player.sendMessage(plugin.getMessageManager().getMessage("wand_given"));
    return true;
  }
}
