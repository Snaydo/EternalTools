package fr.snaydo.eternaltools.commands;

import fr.snaydo.eternaltools.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final Main plugin;

    public ReloadCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission(command.getPermission())) {
            sender.sendMessage(plugin.getPermissionMessage());
            return false;
        }

        plugin.reloadConfig();
        sender.sendMessage("Configuration rechargée !");
        return true;
    }
}