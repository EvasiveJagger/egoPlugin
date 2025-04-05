package me.evasive.ego.tasks;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.joml.Vector3d;

import java.util.ArrayList;
import java.util.Vector;

public class BlackHolePull extends BukkitRunnable {
    private Player p;

    public BlackHolePull (Player p) {
        this.p = p;
    }

    @Override
    public void run() {
        ArrayList<Player> nearPlayers = (ArrayList<Player>) p.getLocation().getNearbyEntitiesByType(Player.class, 10);
        Location userLoc = p.getLocation();
        Vector3d userLocVector = LocToVector(userLoc);

        for (Player target : nearPlayers) {
            //i think it would go smth like this
            Location targetLoc = target.getLocation();
            Vector3d targetLocVector = LocToVector(targetLoc);

            //make vectors outa the locations
            //then subtract target's location from ur location

            Vector3d changeVector = new Vector3d(userLocVector);
            changeVector.sub(targetLocVector);

            //if the user was at the coords 1,1,1 and the target was at the coords 2,2,2 the sub would make the change vector -1,-1,-1, which is the distance that the target needs to go to reach the user


           // double dist = ogLoc.distanceSquared(targetLoc); //squares the distance
            //double angle = ogLoc.getDirection().angle(targetLoc.getDirection()); //ogLoc returns a vector, i have no clue why ur trying to get the angle between user's and targets vectors
            //figure out what the FUCK these mean at all
            //maybe swap them


        }
    }
    public static Vector3d LocToVector(Location loc){
        return new Vector3d(loc.getX(), loc.getY(), loc.getZ());
    }
}
