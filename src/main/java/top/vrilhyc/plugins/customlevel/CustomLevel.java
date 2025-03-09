package top.vrilhyc.plugins.customlevel;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import top.vrilhyc.plugins.customlevel.commands.MainCommand;
import top.vrilhyc.plugins.customlevel.levels.Level;

import java.util.List;

public final class CustomLevel extends JavaPlugin {
    public static PluginInitial pi;
    public static boolean encover_vanilla;
    public static NamespacedKey levelKey = null;
    public static NamespacedKey experienceKey = null;
    @Override
    public void onEnable() {
        // Plugin startup logic
        pi = new PluginInitial(this);
        pi.onEnable();
        pi.loadConfig();
        levelKey = new NamespacedKey(PluginInitial.plugin,"wiseLevel");
        experienceKey = new NamespacedKey(PluginInitial.plugin,"wiseExperience");
        loadData();
        getCommand("customlevel").setExecutor(new MainCommand());
    }

    public static void loadData(){
        Level.levelMap.remove("wiseLevel");
        encover_vanilla = PluginInitial.plugin.getConfig().getBoolean("default.encover-vanilla");
        new Level(levelKey,experienceKey).loadFromConfig(PluginInitial.plugin.getConfig()).register();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        pi.onDisable();
    }

    public static String Auto(String s){
        return ChatColor.translateAlternateColorCodes('&',s);
    }
}
