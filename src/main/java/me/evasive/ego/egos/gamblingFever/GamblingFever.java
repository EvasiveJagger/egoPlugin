package me.evasive.ego.egos.gamblingFever;

import me.evasive.ego.api.AbstractEgo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GamblingFever extends AbstractEgo {
    @Override
    public TextComponent getName() {
        return Component.text("Gambling || Fever");
    }

    @Override
    public TextComponent getObtainedMessage() {
        return Component.text("99% of gamblers quit before they hit it big. But you? Never back down.");
    }

    @Override
    public ItemStack getItem() {
        return null;
    }

    @Override
    public List<Listener> getHandlers() {
        return List.of();
    }

    @Override
    public boolean isRollable() {
        return false;
    }
}
