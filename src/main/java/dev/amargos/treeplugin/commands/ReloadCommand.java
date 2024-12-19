package dev.amargos.treeplugin.commands;

import dev.amargos.treeplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
  private final Main plugin;

  public ReloadCommand(Main plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!sender.hasPermission("treeplugin.reload")) {
      sender.sendMessage(plugin.getMessageManager().getMessage("no_permission"));
      return true;
    }

    plugin.reloadConfig();
    plugin.getMessageManager().reloadMessages();
    sender.sendMessage(plugin.getMessageManager().getMessage("plugin_reloaded"));
    return true;
  }
}
