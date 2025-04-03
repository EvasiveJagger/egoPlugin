package me.evasive.ego.tasks;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class BlackHolePull extends BukkitRunnable {
    private Player p;

    public BlackHolePull (Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        ArrayList<Player> nearPlayers = (ArrayList<Player>) p.getLocation().getNearbyEntitiesByType(Player.class, 10);
        Location ogLoc = p.getLocation();
        for (Player ex : nearPlayers) {
            Location exLoc = ex.getLocation();
            double dist = ogLoc.distanceSquared(exLoc);
            double angle = ogLoc.getDirection().angle(exLoc.getDirection());
            //figure out what the FUCK these mean at all
            //maybe swap them
        }
    }
}
