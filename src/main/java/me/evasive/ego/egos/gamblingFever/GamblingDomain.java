package me.evasive.ego.egos.gamblingFever;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import me.evasive.ego.util.WorldlessLocation;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GamblingDomain {
    private Player creator;
    private Scene currentScene;
    private int scenesPlayed;

    public GamblingDomain(Player player) {
        this.creator = player;
        this.currentScene = GamblingScenes.INTERMISSION;
        this.scenesPlayed = 0;
    }

}

/**
 * Create a new gambling scene
 * @param odds Odds (out of 100) to win the scene
 * @param sceneBlocks Blocks for the scene
 */
record Scene(int odds, Map<Material, WorldlessLocation> sceneBlocks) {}

/**
 * "Constant" Scenes
 */
class GamblingScenes {
    private static final Gson GSON = new Gson();

    public static final Scene INTERMISSION;

    public static final Scene TEST;

    static {
        //Implement Later
        INTERMISSION = getScene("intermission");

        TEST = getScene("test");
    }

    /**
     * Load Scene Json
     * @param sceneName Json file name
     * @return The scene object
     */
    private static Scene getScene(String sceneName) {
        File sceneFile = new File(Objects.requireNonNull(GamblingScenes.class.getClassLoader().getResource("egos/gamblingScenes/scene_" + sceneName + ".json")).getFile());
        try {
            JsonObject json = GSON.fromJson(new BufferedReader(new FileReader(sceneFile)), JsonObject.class);
            int odds = json.get("odds").getAsInt();

            Type blockListType = new TypeToken<List<List<List<String>>>>() {}.getType();
            List<List<List<String>>> blockList = GSON.fromJson(json.get("blocks"), blockListType);

            Map<Material, WorldlessLocation> blocks = new HashMap<>();
            WorldlessLocation center;
            int centerXIndex = 0, centerYIndex = 0, centerZIndex = 0;

            // First, find the CENTER_ block and assign its location
            for(int x = 0; x < blockList.size(); x++) {
                for(int y = 0; y < blockList.get(x).size(); y++) {
                    for(int z = 0; z < blockList.get(x).get(y).size(); z++) {
                        String block = blockList.get(x).get(y).get(z);
                        if(block.equals("CENTER_")) {
                            centerXIndex = x;
                            centerYIndex = y;
                            centerZIndex = z;
                            center = new WorldlessLocation(centerXIndex, centerYIndex, centerZIndex);
                            break;
                        }
                    }
                }
            }

            //get stuff relative to that
            for(int x = 0; x < blockList.size(); x++) {
                for(int y = 0; y < blockList.get(x).size(); y++) {
                    for(int z = 0; z < blockList.get(x).get(y).size(); z++) {
                        String block = blockList.get(x).get(y).get(z);
                        if(!block.equals("CENTER_")) {
                            // Get the material from the string
                            Material material = Material.matchMaterial(block);
                            if(material != null) {
                                // Create a new WorldlessLocation relative to the center
                                WorldlessLocation location = new WorldlessLocation(centerXIndex + x, centerYIndex + y, centerZIndex + z);
                                blocks.put(material, location);
                            }
                        }
                    }
                }
            }

            // Return the scene with the odds and the map of blocks
            return new Scene(odds, blocks);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}