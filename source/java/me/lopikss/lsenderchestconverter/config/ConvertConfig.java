package me.lopikss.lsenderchestconverter.config;

import org.bukkit.plugin.java.JavaPlugin;

public class ConvertConfig {

    private final JavaPlugin plugin;

    public ConvertConfig(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean isConvertOnFirstJoin() {
        return plugin.getConfig().getBoolean("convert-on-first-join", true);
    }

    public boolean isDebug() {
        return plugin.getConfig().getBoolean("debug", false);
    }
}