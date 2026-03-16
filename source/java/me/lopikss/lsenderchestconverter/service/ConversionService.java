package me.lopikss.lsenderchestconverter.service;

import me.lopikss.lsenderchestconverter.config.ConvertConfig;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ConversionService {

    private final JavaPlugin plugin;
    private final ConvertConfig config;
    private final LsEnderChestHook lsEnderChestHook;

    public ConversionService(JavaPlugin plugin, ConvertConfig config) {
        this.plugin = plugin;
        this.config = config;
        this.lsEnderChestHook = new LsEnderChestHook(plugin, config);
    }

    public boolean convert(Player player) {
        try {
            ItemStack[] vanillaContents = player.getEnderChest().getContents();
            ItemStack[] convertedContents = new ItemStack[54];

            for (int i = 0; i < 27 && i < vanillaContents.length; i++) {
                ItemStack item = vanillaContents[i];
                convertedContents[i] = item == null ? null : item.clone();
            }

            boolean success = lsEnderChestHook.saveToLsEnderChest(player, convertedContents);

            if (success && config.isDebug()) {
                plugin.getLogger().info("Converted Ender Chest for " + player.getName());
            }

            return success;
        } catch (Exception exception) {
            plugin.getLogger().severe("Failed to convert Ender Chest for " + player.getName());
            exception.printStackTrace();
            return false;
        }
    }
}