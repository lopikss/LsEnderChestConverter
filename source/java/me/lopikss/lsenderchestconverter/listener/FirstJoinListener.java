package me.lopikss.lsenderchestconverter.listener;

import me.lopikss.lsenderchestconverter.service.ConversionService;
import me.lopikss.lsenderchestconverter.storage.ConvertedPlayersManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstJoinListener implements Listener {

    private final ConversionService conversionService;
    private final ConvertedPlayersManager convertedPlayersManager;

    public FirstJoinListener(ConversionService conversionService, ConvertedPlayersManager convertedPlayersManager) {
        this.conversionService = conversionService;
        this.convertedPlayersManager = convertedPlayersManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (convertedPlayersManager.isConverted(player.getUniqueId())) {
            return;
        }

        if (conversionService.convert(player)) {
            convertedPlayersManager.markConverted(player.getUniqueId());
            player.getServer().getLogger().info("Successfully converted Ender Chest for: " + player.getUniqueId());
        }
    }
}