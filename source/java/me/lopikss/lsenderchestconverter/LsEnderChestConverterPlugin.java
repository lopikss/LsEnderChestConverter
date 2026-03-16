package me.lopikss.lsenderchestconverter;

import me.lopikss.lsenderchestconverter.command.ConvertCommand;
import me.lopikss.lsenderchestconverter.config.ConvertConfig;
import me.lopikss.lsenderchestconverter.listener.FirstJoinListener;
import me.lopikss.lsenderchestconverter.listener.UpdateNotifyListener;
import me.lopikss.lsenderchestconverter.service.ConversionService;
import me.lopikss.lsenderchestconverter.storage.ConvertedPlayersManager;
import me.lopikss.lsenderchestconverter.update.UpdateChecker;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class LsEnderChestConverterPlugin extends JavaPlugin {

    private ConvertConfig convertConfig;
    private ConversionService conversionService;
    private ConvertedPlayersManager convertedPlayersManager;
    private UpdateChecker updateChecker;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveResource("converted-players.yml", false);

        this.convertConfig = new ConvertConfig(this);
        this.conversionService = new ConversionService(this, convertConfig);
        this.convertedPlayersManager = new ConvertedPlayersManager(this);
        this.updateChecker = new UpdateChecker(this);

        PluginCommand command = getCommand("lsecconvert");
        if (command != null) {
            command.setExecutor(new ConvertCommand(conversionService));
        }

        if (convertConfig.isConvertOnFirstJoin()) {
            getServer().getPluginManager().registerEvents(
                    new FirstJoinListener(conversionService, convertedPlayersManager),
                    this
            );
        }

        getServer().getPluginManager().registerEvents(
                new UpdateNotifyListener(this, updateChecker),
                this
        );

        updateChecker.checkNow();

        getLogger().info("LsEnderChestConverter enabled.");
    }

    public ConvertConfig getConvertConfig() {
        return convertConfig;
    }

    public ConversionService getConversionService() {
        return conversionService;
    }

    public ConvertedPlayersManager getConvertedPlayersManager() {
        return convertedPlayersManager;
    }

    public UpdateChecker getUpdateChecker() {
        return updateChecker;
    }
}