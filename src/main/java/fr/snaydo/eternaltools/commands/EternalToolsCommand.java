package fr.snaydo.eternaltools.commands;

import fr.snaydo.eternaltools.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EternalToolsCommand implements CommandExecutor {

    private final Main plugin;

    public EternalToolsCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission(command.getPermission())) {
                plugin.reloadConfig();
                sender.sendMessage("Configuration rechargée !");
                return true;
            } else {
                sender.sendMessage(plugin.getConfig().getString("permission-messages.default", "§cVous n'avez pas la permission d'exécuter cette commande."));
                return false;
            }
        }
        return false;
    }
}