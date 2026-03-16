package me.lopikss.lsenderchestconverter.listener;

import me.lopikss.lsenderchestconverter.LsEnderChestConverterPlugin;
import me.lopikss.lsenderchestconverter.update.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateNotifyListener implements Listener {

    private final LsEnderChestConverterPlugin plugin;
    private final UpdateChecker updateChecker;

    public UpdateNotifyListener(LsEnderChestConverterPlugin plugin, UpdateChecker updateChecker) {
        this.plugin = plugin;
        this.updateChecker = updateChecker;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> updateChecker.notifyPlayer(event.getPlayer()), 40L);
    }
}