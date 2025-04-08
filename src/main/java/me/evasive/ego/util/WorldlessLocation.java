package me.evasive.ego.util;

import org.bukkit.Location;

public class WorldlessLocation {
    private double x, y, z, pitch, yaw;

    public WorldlessLocation(Location loc) {
        this(loc.getX(), loc.getY(), loc.getZ(), loc.getPitch(), loc.getYaw());
    }
    public WorldlessLocation(double x, double y, double z) {
        this(x, y, z, 0, 0);
    }
    public WorldlessLocation(double x, double y, double z, double pitch, double yaw) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getPitch() {
        return pitch;
    }

    public double getYaw() {
        return yaw;
    }
}
