package fr.snaydo.eternaltools.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import fr.snaydo.eternaltools.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerDeathListener implements Listener {

    private final Main plugin;

    public PlayerDeathListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        List<ItemStack> inventoryItems = new ArrayList<>();
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null) {
                inventoryItems.add(item);
            }
        }

        List<ItemStack> itemsToKeep = inventoryItems.stream()
                .filter(item -> item != null && isItemEternal(item))
                .collect(Collectors.toList());

        event.getDrops().removeAll(itemsToKeep);

        // Redonner les items éternels au joueur après la mort
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            Player p = plugin.getServer().getPlayer(player.getUniqueId());
            if (p != null) {
                for (ItemStack item : itemsToKeep) {
                    p.getInventory().addItem(item);
                }
            }
        }, 1L); // 1 tick de délai pour assurer que le joueur est réapparu
    }

    private boolean isItemEternal(ItemStack item) {
        FileConfiguration config = plugin.getConfig();
        List<?> eternalItems = config.getList("eternal-tools");
        if (eternalItems != null) {
            for (Object eternalItem : eternalItems) {
                if (eternalItem instanceof Map) {
                    Map<String, Object> itemMap = (Map<String, Object>) eternalItem;
                    String material = (String) itemMap.get("Material");
                    String name = (String) itemMap.get("name");
                    if (material != null && name != null && material.equals(item.getType().toString())) {
                        ItemMeta meta = item.getItemMeta();
                        if (meta != null && name.equals(meta.getDisplayName())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}