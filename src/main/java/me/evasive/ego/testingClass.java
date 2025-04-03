package me.evasive.ego;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.net.http.WebSocket;
import java.util.ArrayList;

public class testingClass implements Listener {

    @EventHandler
    public void onShift(PlayerToggleSneakEvent ev) {
        ArrayList<LivingEntity> list = (ArrayList<LivingEntity>) ev.getPlayer().getLocation().getNearbyLivingEntities(5);
        ev.getPlayer().sendMessage(list.get(0).getName());
        double distance = list.get(0).getLocation().distanceSquared(ev.getPlayer().getLocation());
        double yawChick = list.get(0).getYaw();
        double pitchChick = list.get(0).getPitch();
        double yawPly = ev.getPlayer().getYaw();
        double pitchPly = ev.getPlayer().getPitch();
        ev.getPlayer().sendMessage("Distance: " + distance + "\nChicken's Yaw - Player's Yaw " + (yawChick - yawPly) + "\nChicken's Pitch - Player's Pitch " + (pitchChick - pitchPly));
    }

}
