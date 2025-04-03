package me.evasive.ego;

import me.evasive.ego.api.EgoRegistry;
import me.evasive.ego.api.NamespacedKeyRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public class Ego extends JavaPlugin {
    private static Ego instance;

    private NamespacedKeyRegistry keyRegistry;
    private EgoRegistry egoRegistry;
    @Override
    public void onEnable() {
        instance = this;

        keyRegistry = new NamespacedKeyRegistry();
        egoRegistry = new EgoRegistry();

        // Register keys here

        // Register Egos here




        // On end of onEnable, freeze the registries
        keyRegistry.freeze();
        egoRegistry.freeze();
    }

    @Override
    public void onDisable() {

    }

    public static Ego getInstance() {
        return instance;
    }

    public NamespacedKeyRegistry getKeyRegistry() {
        return keyRegistry;
    }
    public EgoRegistry getEgoRegistry() {
        return egoRegistry;
    }
}
