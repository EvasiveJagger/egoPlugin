package me.evasive.ego.api;

import me.evasive.ego.Ego;
import me.evasive.ego.util.PluginUtil;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractEgo {
    /**
     * The name of the ego. This is used to identify the ego in the key registry and for displaying it to the player.
     * @return The name of the ego.
     */
    public abstract TextComponent getName();

    /**
     * This message will be sent to the player when they obtain the ego.
     * @return The message to be sent to the player.
     */
    public abstract TextComponent getObtainedMessage();

    /**
     * This is the item form of the ego. This is what the player will see in their inventory.
     * @return The item form of the ego.
     */
    public abstract ItemStack getItem();

    /**
     * This will tick every tick for the player that has the ego. This is used to update the ego's state and perform any actions that need to be done every tick.
     * @param user The player that has the ego.
     */
    public void onTick(Player user) {}

    /**
     * This will be called when the player uses the ego on an entity. This is used to perform any actions that need to be done when the player uses the ego.
     * @param event The event that is called when the player uses the ego on an entity.
     */
    public void onUse(PlayerInteractEntityEvent event) {}

    /**
     * This will be called when the player hits an entity with the ego. This is used to perform any actions that need to be done when the player hits an entity with the ego.
     * @param event The event that is called when the player hits an entity with the ego.
     */
    public void onHit(EntityDamageByEntityEvent event) {}

    /**
     * This will be called when the player is hit by an entity with the ego. This is used to perform any actions that need to be done when the player is hit by an entity with the ego.
     * @param event The event that is called when the player is hit by an entity with the ego.
     */
    public void onHurt(EntityDamageByEntityEvent event) {}

    /**
     * This will be called when the player dies with the ego. This is used to perform any actions that need to be done when the player dies with the ego.
     * @param event The event that is called when the player dies with the ego.
     */
    public void onDeath(EntityDamageByEntityEvent event) {}

    /**
     * This is used to register any custom event handlers for the ego.
     * @return A list of custom event handlers to register for the ego.
     */
    public abstract List<Listener> getHandlers();

    /**
     * Note: Do not override this method. This is used to register the ego with the plugin.
     */
    public void register() {
        List<Listener> handlers = new ArrayList<>();
        handlers.addAll(getHandlers());
        //if onTick or any other method is not overridden, we don't need to register the handler
        handlers.add(new EgoHandler(this));
        for (Listener handler : handlers) {
            // Register the event handler
            Bukkit.getServer().getPluginManager().registerEvents(handler, Ego.getInstance());
        }
        Bukkit.getServer().getScheduler().runTaskTimer(Ego.getInstance(), new EgoTask(this), 0L, 1L);
        // Register the key

        Ego.getInstance().getKeyRegistry().registerKey("ego_instance:" + getName().content().toLowerCase(), PersistentDataType.BOOLEAN);
    }

    /**
     * This handles the events for the ego.
     */
    private static class EgoHandler implements Listener {
        private final AbstractEgo ego;

        public EgoHandler(AbstractEgo ego) {
            this.ego = ego;
        }

        @EventHandler
        public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
            if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(ego.getItem())) {
                ego.onUse(event);
            }
        }
        @EventHandler
        public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
            if (event.getDamager() instanceof Player) {
                Player player = (Player) event.getDamager();
                if (player.getInventory().getItemInMainHand().isSimilar(ego.getItem())) {
                    ego.onHit(event);
                }
            }
        }
        @EventHandler
        public void onEntityDamage(EntityDamageByEntityEvent event) {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                if (player.getInventory().getItemInMainHand().isSimilar(ego.getItem())) { // Oy moon can u change this to whether the EGO is in their inv? Cause ppl will still be fighting w swords so wont be holding item. Thanks, Janis
                    ego.onHurt(event);
                }
            }
        }
        @EventHandler
        public void onEntityDeath(EntityDamageByEntityEvent event) {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                if (player.getInventory().getItemInMainHand().isSimilar(ego.getItem())) {
                    ego.onDeath(event);
                }
            }
        }
    }

    /**
     * This handles the onTick method for the ego.
     */
    private static class EgoTask extends BukkitRunnable {
        private final AbstractEgo ego;

        public EgoTask(AbstractEgo ego) {
            this.ego = ego;
        }

        @Override
        public void run() {
            // Iterate through all online players with the key "ego_instance:get_name"

            for (Player player : Bukkit.getOnlinePlayers()) {
                Optional<?> hasEgo = PluginUtil.getKey(Ego.getInstance().getKeyRegistry().getKey("ego_instance:" + ego.getName().content().toLowerCase()), player);
                if(hasEgo.isPresent() && (boolean) hasEgo.get()) {
                    // Call the onTick method
                    ego.onTick(player);
                }
            }
        }
    }
}
