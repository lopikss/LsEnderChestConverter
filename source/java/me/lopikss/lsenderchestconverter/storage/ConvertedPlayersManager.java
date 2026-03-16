package me.lopikss.lsenderchestconverter.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ConvertedPlayersManager {

    private final JavaPlugin plugin;
    private final File file;
    private FileConfiguration config;

    public ConvertedPlayersManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "converted-players.yml");

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Could not create converted-players.yml", e);
            }
        }

        this.config = YamlConfiguration.loadConfiguration(file);

        if (!config.isList("converted")) {
            config.set("converted", new java.util.ArrayList<String>());
            save();
        }
    }

    public boolean isConverted(UUID uuid) {
        return getConverted().contains(uuid.toString());
    }

    public void markConverted(UUID uuid) {
        Set<String> converted = getConverted();
        converted.add(uuid.toString());
        config.set("converted", new java.util.ArrayList<>(converted));
        save();
    }

    private Set<String> getConverted() {
        return new HashSet<>(config.getStringList("converted"));
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException("Could not save converted-players.yml", e);
        }
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(file);
    }
}