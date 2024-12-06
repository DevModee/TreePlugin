package dev.amargos.treeplugin.listeners;

import dev.amargos.treeplugin.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;
import java.util.UUID;

public class TreeListener implements Listener {
    private final Main plugin;
    private final HashMap<UUID, Integer> playerCoins;

    public TreeListener(Main plugin) {
        this.plugin = plugin;
        this.playerCoins = plugin.getPlayerCoins();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (block.getType() == Material.LOG || block.getType() == Material.LOG_2) {
            UUID playerId = player.getUniqueId();

            playerCoins.put(playerId, playerCoins.getOrDefault(playerId, 0) + 1);

            player.sendMessage("¡Recibiste 1 moneda! Total: " + playerCoins.get(playerId));
        }
    }
}
