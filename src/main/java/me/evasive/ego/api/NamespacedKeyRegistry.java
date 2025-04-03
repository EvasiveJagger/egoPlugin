package me.evasive.ego.api;

import me.evasive.ego.Ego;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

/**
 * A registry for NamespacedKeys and their associated PersistentDataTypes.
 * <p>
 * Example:
 * <pre>
 * {@code
 * // In your plugin's onEnable method, you can register keys like this:
 * NamespacedKeyRegistry.registerKey("example_key", PersistentDataType.STRING);
 * }
 * </pre>
 * </p>
 * <p>
 * Later somewhere else:
 * <pre>
 * {@code
 * PluginUtil.getKey(NamespacedKeyRegistry.getKey("example_key"), player);
 * }
 * </pre>
 * This will return an Optional of the value associated with the key in the player's PersistentDataContainer.
 * </p>
 * <p>
 *     ---
 * </p>
 * <p>
 *     Very Cool!
 * </p>
 */

public class NamespacedKeyRegistry {
    private List<Key> keys;
    private boolean frozen = false;

    public NamespacedKeyRegistry() {
        keys = new ArrayList<>();
    }

    public void registerKey(String key, PersistentDataType<?, ?> type) {
        if (frozen) {
            throw new IllegalStateException("Cannot register keys after the registry has been frozen.");
        }
        if (keys.contains(key)) {
            throw new IllegalArgumentException("Key already registered: " + key);
        }
        keys.add(new Key(key, type));
    }

    public Key getKey(String key) {
        if(!frozen) {
            Ego.getInstance().getLogger().warning("Key registry is not frozen. This may cause issues.");
        }
        for (Key k : keys) {
            if (k.getKey().equals(key)) {
                return k;
            }
        }
        throw new IllegalArgumentException("Key not found: " + key);
    }

    public void freeze() {
        frozen = true;
    }


    public static class Key {
        private final String key;
        private final PersistentDataType<?, ?> type;

        public Key(String key, PersistentDataType<?, ?> type) {
            this.key = key;
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public PersistentDataType<?, ?> getType() {
            return type;
        }
        public NamespacedKey getNamespacedKey() {
            return new NamespacedKey(Ego.getInstance(), key);
        }
    }
}
