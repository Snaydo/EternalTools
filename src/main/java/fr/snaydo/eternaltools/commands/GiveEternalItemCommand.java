package fr.snaydo.eternaltools.commands;

import fr.snaydo.eternaltools.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class GiveEternalItemCommand implements CommandExecutor {

    private final Main plugin;

    public GiveEternalItemCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission(command.getPermission())) {
            sender.sendMessage(plugin.getPermissionMessage());
            return false;
        }

        if (args.length != 2) {
            sender.sendMessage("Usage: /giveeternalitem <player> <id>");
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage("Le joueur spécifié est introuvable.");
            return false;
        }

        int itemId;
        try {
            itemId = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("L'ID de l'item doit être un nombre.");
            return false;
        }

        ItemStack item = createEternalItem(itemId);
        if (item == null) {
            sender.sendMessage("Aucun item éternel trouvé avec l'ID spécifié.");
            return false;
        }

        player.getInventory().addItem(item);
        sender.sendMessage("L'item éternel a été donné à " + player.getName() + ".");
        return true;
    }

    private ItemStack createEternalItem(int id) {
        FileConfiguration config = plugin.getConfig();
        List<?> eternalItems = config.getList("eternal-tools");
        if (eternalItems != null) {
            for (Object eternalItem : eternalItems) {
                if (eternalItem instanceof Map) {
                    Map<String, Object> itemMap = (Map<String, Object>) eternalItem;
                    int itemId = (Integer) itemMap.get("id");
                    if (itemId == id) {
                        Material material = Material.valueOf((String) itemMap.get("Material"));
                        ItemStack item = new ItemStack(material);

                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName((String) itemMap.get("name"));

                        List<String> lore = (List<String>) itemMap.get("Lore");
                        meta.setLore(lore);

                        List<?> enchantments = (List<?>) itemMap.get("Enchantments");
                        if (enchantments != null) {
                            for (Object enchantmentObj : enchantments) {
                                if (enchantmentObj instanceof Map) {
                                    Map<String, Object> enchantmentMap = (Map<String, Object>) enchantmentObj;
                                    Enchantment enchantment = Enchantment.getByName((String) enchantmentMap.get("enchantment"));
                                    int level = (Integer) enchantmentMap.get("level");
                                    if (enchantment != null) {
                                        meta.addEnchant(enchantment, level, true);
                                    }
                                }
                            }
                        }

                        boolean unbreakable = itemMap.get("Unbreakable") != null && (Boolean) itemMap.get("Unbreakable");
                        if (unbreakable) {
                            meta.spigot().setUnbreakable(true);
                            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                        }

                        item.setItemMeta(meta);
                        return item;
                    }
                }
            }
        }
        return null;
    }
}