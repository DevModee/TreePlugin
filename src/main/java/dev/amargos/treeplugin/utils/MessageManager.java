package dev.amargos.treeplugin.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MessageManager {
  private final JavaPlugin plugin;
  private FileConfiguration messagesConfig;
  private File messagesFile;

  public MessageManager(JavaPlugin plugin) {
    this.plugin = plugin;
    loadMessages();
  }

  private void loadMessages() {
    messagesFile = new File(plugin.getDataFolder(), "messages.yml");
    if (!messagesFile.exists()) {
      plugin.saveResource("messages.yml", false);
    }
    messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
  }

  public String getMessage(String path) {
    return messagesConfig.getString(path, "Mensaje no encontrado:" + path);
  }

  public String getMessage(String path, String placeholder, String value) {
    String message = getMessage(path);
    return message.replace(placeholder, value);
  }

  public void reloadMessages() {
    messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
  }
}
