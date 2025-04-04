package me.evasive.ego.egos;

import me.evasive.ego.api.AbstractEgo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BlackHole extends AbstractEgo {
    @Override
    public TextComponent getName() {
        return Component.text("Red Eyes // Black Hole");
    }

    @Override
    public TextComponent getObtainedMessage() {
        return Component.text("Turns out no one's come to save you... from my all-consuming gravity.");
    }

    @Override
    public ItemStack getItem() {
        return null;
    }

    @Override
    public List<Listener> getHandlers() {
        return List.of();
    }

    //thank u zach this is really convenient

    //For about 70 ticks, make movement vectors towards a specific player,
    //then explode that player after a second.
    //RunTaskTimer into a cancel and then
    //RunTaskLater
    @Override
    public void onTick(Player user) {
        super.onTick(user);
    }
}
