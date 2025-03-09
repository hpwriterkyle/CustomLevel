package top.vrilhyc.plugins.customlevel.levels;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import top.vrilhyc.plugins.customlevel.PluginInitial;
import top.vrilhyc.plugins.customlevel.levels.ladder.LevelLadder;
import top.vrilhyc.plugins.customlevel.levels.ladder.ListLadder;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Level {
    protected String name;
    protected NamespacedKey key = null;
    protected NamespacedKey experienceKey = null;
    protected LevelLadder ladder = null;
    public static Map<String,Level> levelMap = new HashMap<>();

    public Level(NamespacedKey levelkey,NamespacedKey experiencekey){
        this.name = "wiseLevel";
        this.key = levelkey;
        this.experienceKey = experiencekey;
    }

    public NamespacedKey getLevelKey() {
        return key;
    }

    public NamespacedKey getExperienceKey() {
        return experienceKey;
    }

    public LevelLadder getLadder() {
        return ladder;
    }

    public Level loadFromConfig(ConfigurationSection configurationSection){
        ladder = new ListLadder(configurationSection.getDoubleList("levels"));
        return this;
    }

    public boolean register(){
        if(levelMap.containsKey(this.name)){
            return false;
        }
        levelMap.put(this.name,this);
        return true;
    }

    public static Level getWiseLevel(){
        return levelMap.get("wiseLevel");
    }
}
