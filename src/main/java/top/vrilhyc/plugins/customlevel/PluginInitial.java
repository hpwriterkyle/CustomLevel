package top.vrilhyc.plugins.customlevel;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class PluginInitial {
    public static JavaPlugin plugin;
    public static File playerdata;
    public static File langdata;

    public PluginInitial(JavaPlugin jp){
        plugin = jp;
    }

    public FileConfiguration loadConfig(){
        plugin.getConfig().options().copyDefaults();
        if(!plugin.getDataFolder().exists()) {
            try{
                plugin.getDataFolder().mkdirs();
            }catch (Exception e){
                log("&c总配置文件加载故障");
            }
            plugin.saveDefaultConfig();
        }
        return plugin.getConfig();
    }

    public File loadPlayerData(){
        playerdata = new File(plugin.getDataFolder(),"playerdata");
        if(!playerdata.exists()){
            try{
                playerdata.mkdirs();
            }catch (Exception e){
                log("&c玩家配置文件加载故障");
            }
        }
        return playerdata;
    }


    public File loadLangData(){
        langdata = new File(plugin.getDataFolder(),"lang.yml");
        if(!langdata.exists()){
            try{
                plugin.saveResource("lang.yml",false);
            }catch (Exception e){
                log("&c玩家配置文件加载故障");
            }
        }
        return langdata;
    }

    public static String log(String s){
        s = Auto(s);
        Bukkit.getLogger().info(s);
        return s;
    }

    public static String warn(String s){
        s = Auto("&f["+"&e"+plugin.getName()+"&f]"+s);
        Bukkit.getLogger().info(s);
        return s;
    }

    public void onEnable(){
        log("&f["+"&e"+plugin.getName()+"&f]&a插件已开启!");
    }

    public void onDisable(){
        log("&f["+"&e"+plugin.getName()+"&f]&a插件已关闭!");
    }

    public static String Auto(String s){
        return ChatColor.translateAlternateColorCodes('&',s);
    }
}
