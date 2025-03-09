package top.vrilhyc.plugins.customlevel.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import top.vrilhyc.plugins.customlevel.CustomLevel;
import top.vrilhyc.plugins.customlevel.PluginInitial;
import top.vrilhyc.plugins.customlevel.api.LevelAPI;
import top.vrilhyc.plugins.customlevel.levels.Level;
import top.vrilhyc.plugins.customlevel.levels.storager.EntityStorager;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class MainCommand implements CommandExecutor, TabCompleter {
    public static Map<UUID,Status> statusMap = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        SubCommandParser subCommandParser = new SubCommandParser(getClass(),this,sender,command,label,args);
        try {
            subCommandParser.parse();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    @SubCommand(paramsLength = 1, commandName = "addExperience",permissions = {},opped = true)
    public void addExperience(CommandSender sender,Command command,String label,String[] args){
        Player player = null;
        if(sender instanceof Player){
            player = (Player) sender;
        }
        if(args.length>2){
            player = Bukkit.getPlayer(args[2]);
        }
        if(player==null){
            sender.sendMessage(CustomLevel.Auto("&a其玩家不在线罢"));
            return;
        }
        Level level = Level.getWiseLevel();
        LevelAPI.addExperience(new EntityStorager(level,player),Double.parseDouble(args[1]));
        sender.sendMessage(CustomLevel.Auto("&a其已增加"+args[1]+"经验罢"));
    }

    @SubCommand(paramsLength = 1, commandName = "addLevel",permissions = {},opped = true)
    public void addLevel(CommandSender sender,Command command,String label,String[] args){
        Player player = null;
        if(sender instanceof Player){
            player = (Player) sender;
        }
        if(args.length>2){
            player = Bukkit.getPlayer(args[2]);
        }
        if(player==null){
            sender.sendMessage(CustomLevel.Auto("&a其玩家不在线罢"));
            return;
        }
        Level level = Level.getWiseLevel();
        LevelAPI.addLevel(new EntityStorager(level,player),Long.parseLong(args[1]));
        sender.sendMessage(CustomLevel.Auto("&a其已增加"+args[1]+"等级罢"));
    }

    @SubCommand(paramsLength = 0, commandName = "getLevel",permissions = {},opped = true)
    public void getLevel(CommandSender sender,Command command,String label,String[] args){
        Player player = null;
        if(sender instanceof Player){
            player = (Player) sender;
        }
        if(args.length>1){
            player = Bukkit.getPlayer(args[1]);
        }
        if(player==null){
            sender.sendMessage(CustomLevel.Auto("&a其玩家不在线罢"));
            return;
        }
        Level level = Level.getWiseLevel();
        LevelAPI.getLevel(new EntityStorager(level,player));
        sender.sendMessage(CustomLevel.Auto("&a其为"+LevelAPI.getLevel(new EntityStorager(level,player))+"等级罢"));
    }

    @SubCommand(paramsLength = 0, commandName = "reload",permissions = {},opped = true)
    public void reloads(CommandSender sender,Command command,String label,String[] args){
        PluginInitial.plugin.reloadConfig();
        CustomLevel.loadData();
        sender.sendMessage(CustomLevel.Auto("&a其插件已重载!"));
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings==null||strings.length<=1){
            return Arrays.stream(getClass().getMethods()).filter(a->a.isAnnotationPresent(SubCommand.class)).filter(a->commandSender.isOp()||!a.getAnnotation(SubCommand.class).opped()).map(a->a.getAnnotation(SubCommand.class).commandName()).filter(a->strings==null||(strings.length==1&&a.startsWith(strings[0]))).collect(Collectors.toList());
        }
        return null;
    }


    public enum StatusType{
        TAKING,
        REMOVING;

        public void commit(Player player){
            Status status = statusMap.getOrDefault(player.getUniqueId(),new Status());
            status.getStatus().add(this);
            statusMap.put(player.getUniqueId(),status);
        }

        public void remove(Player player){
            Status status = statusMap.getOrDefault(player.getUniqueId(),new Status());
            status.getStatus().remove(this);
            statusMap.put(player.getUniqueId(),status);
        }

        public boolean equip(Player player){
            Status status = statusMap.get(player.getUniqueId());
            if(status==null){
                return false;
            }
            return status.getStatus().contains(this);
        }
    }
}

