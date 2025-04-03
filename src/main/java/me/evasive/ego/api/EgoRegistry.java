package me.evasive.ego.api;

import java.util.ArrayList;
import java.util.List;

public class EgoRegistry {
    private List<AbstractEgo> registeredEgos;
    private boolean frozen = false;

    public EgoRegistry() {
        registeredEgos = new ArrayList<>();
    }

    public void registerEgo(AbstractEgo ego) {
        if (registeredEgos.contains(ego)) {
            throw new IllegalArgumentException("Ego already registered: " + ego.getName());
        }
        if (frozen) {
            throw new IllegalStateException("Cannot register Egos after the registry has been frozen.");
        }
        ego.register();
        registeredEgos.add(ego);
    }

    public void freeze() {
        frozen = true;
    }
}
