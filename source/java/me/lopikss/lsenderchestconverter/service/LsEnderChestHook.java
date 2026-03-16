package me.lopikss.lsenderchestconverter.service;

import me.lopikss.lsenderchestconverter.config.ConvertConfig;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

public class LsEnderChestHook {

    private final JavaPlugin plugin;
    private final ConvertConfig config;

    private final Object chestManager;
    private final Object playerIdManager;
    private final Object storageProvider;

    public LsEnderChestHook(JavaPlugin plugin, ConvertConfig config) {
        this.plugin = plugin;
        this.config = config;

        Plugin lsEnderChestPlugin = plugin.getServer().getPluginManager().getPlugin("LsEnderChest");
        if (lsEnderChestPlugin == null) {
            throw new IllegalStateException("LsEnderChest is required but was not found.");
        }

        try {
            Method getChestManagerMethod = lsEnderChestPlugin.getClass().getMethod("getChestManager");
            this.chestManager = getChestManagerMethod.invoke(lsEnderChestPlugin);

            this.playerIdManager = readField(chestManager, "playerIdManager");
            this.storageProvider = readField(chestManager, "storageProvider");
        } catch (Exception exception) {
            throw new IllegalStateException("Failed to hook into LsEnderChest internals.", exception);
        }
    }

    public boolean saveToLsEnderChest(Player player, ItemStack[] contents) {
        try {
            Method getStorageUuidMethod = playerIdManager.getClass().getMethod("getStorageUuid", OfflinePlayer.class);
            UUID storageUuid = (UUID) getStorageUuidMethod.invoke(playerIdManager, player);

            Method saveMethod = storageProvider.getClass().getMethod(
                    "save",
                    UUID.class,
                    String.class,
                    ItemStack[].class
            );

            String playerName = player.getName() == null ? "unknown" : player.getName();
            saveMethod.invoke(storageProvider, storageUuid, playerName, contents);

            if (config.isDebug()) {
                plugin.getLogger().info("Saved converted data into LsEnderChest for " + playerName);
            }

            return true;
        } catch (Exception exception) {
            plugin.getLogger().severe("Could not save converted chest for " + player.getName());
            exception.printStackTrace();
            return false;
        }
    }

    private Object readField(Object instance, String fieldName) throws Exception {
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(instance);
    }
}