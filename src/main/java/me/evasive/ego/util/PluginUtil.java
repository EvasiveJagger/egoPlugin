package me.evasive.ego.util;

import me.evasive.ego.api.NamespacedKeyRegistry;
import org.bukkit.entity.Player;

import java.util.Optional;

public class PluginUtil {
    public static Optional<?> getKey(NamespacedKeyRegistry.Key key, Player player) {
        if (key == null || player == null) {
            return Optional.empty();
        }
        if (player.getPersistentDataContainer().has(key.getNamespacedKey(), key.getType())) {
            return Optional.ofNullable(player.getPersistentDataContainer().get(key.getNamespacedKey(), key.getType()));
        } else {
            return Optional.empty();
        }
    }
}