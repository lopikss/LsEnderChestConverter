package me.lopikss.lsenderchestconverter.command;

import me.lopikss.lsenderchestconverter.service.ConversionService;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConvertCommand implements CommandExecutor {

    private final ConversionService conversionService;

    public ConvertCommand(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length != 1) {
            sender.sendMessage("§cUsage: /lsecconvert <player|all>");
            return true;
        }

        if (args[0].equalsIgnoreCase("all")) {
            int converted = 0;

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (conversionService.convert(player)) {
                    converted++;
                }
            }

            sender.sendMessage("§aConverted vanilla Ender Chests for " + converted + " online player(s).");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            sender.sendMessage("§cThat player must be online to convert their vanilla Ender Chest.");
            return true;
        }

        if (conversionService.convert(target)) {
            sender.sendMessage("§aConverted vanilla Ender Chest for §f" + target.getName() + "§a.");
        } else {
            sender.sendMessage("§cFailed to convert vanilla Ender Chest for §f" + target.getName() + "§c.");
        }

        return true;
    }
}