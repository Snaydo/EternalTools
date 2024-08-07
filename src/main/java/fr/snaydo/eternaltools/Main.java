package fr.snaydo.eternaltools;

import fr.snaydo.eternaltools.commands.GiveEternalItemCommand;
import fr.snaydo.eternaltools.listeners.PlayerDeathListener;
import fr.snaydo.eternaltools.commands.EternalToolsCommand;
import fr.snaydo.eternaltools.commands.ReloadCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Enregistrement des écouteurs d'événements
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(this), this);

        // Enregistrement des commandes
        this.getCommand("eternaltools").setExecutor(new EternalToolsCommand(this));
        this.getCommand("reload").setExecutor(new ReloadCommand(this));
        this.getCommand("giveeternalitem").setExecutor(new GiveEternalItemCommand(this));

        // Sauvegarder la configuration par défaut
        saveDefaultConfig();
        getConfig().addDefault("permission-messages.default", "§cVous n'avez pas la permission d'utiliser cette commande.");
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        // Actions à effectuer lors de la désactivation du plugin
    }

    public String getPermissionMessage() {
        return getConfig().getString("permission-messages.default");
    }
}