package dev.amargos.treeplugin;

import dev.amargos.treeplugin.listeners.TreeListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {
    private final HashMap<UUID, Integer> playerCoins = new HashMap<>();

    @Override
    public void onEnable() {
        getLogger().info("TreePlugin activado correctamente.");
        saveDefaultConfig();
        registerListeners();
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
}