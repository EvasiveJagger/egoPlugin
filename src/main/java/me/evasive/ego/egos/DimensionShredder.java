package me.evasive.ego.egos;

import me.evasive.ego.api.AbstractEgo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DimensionShredder extends AbstractEgo {
    @Override
    public TextComponent getName() {
        return Component.text("Dimension Shredder");
    }

    @Override
    public TextComponent getObtainedMessage() {
        return Component.text("Do not avert your eyes even for a moment, lest you be ensnared in an eternal wandering.");
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Material.OBSIDIAN);
    }

    @Override
    public List<Listener> getHandlers() {
        return List.of();
    }

    //Blink: Instantly teleport where you're looking. If looking at a player, teleport behind and above them for a crit. Dimensional Travel: Puts you at the same coords on the nether roof, after a bit tp you back
    //
    @Override
    public void onTick(Player user) {
        super.onTick(user);
    }
}
