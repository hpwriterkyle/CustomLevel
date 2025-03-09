package top.vrilhyc.plugins.customlevel;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import top.vrilhyc.plugins.customlevel.api.LevelAPI;
import top.vrilhyc.plugins.customlevel.levels.Level;
import top.vrilhyc.plugins.customlevel.levels.storager.EntityStorager;

public class MainListener implements Listener {
    @EventHandler
    public void onExperience(PlayerExpChangeEvent e){
        if(!CustomLevel.encover_vanilla){
            return;
        }
        e.setAmount(0);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        LevelAPI.addLevel(new EntityStorager(Level.getWiseLevel(),e.getEntity()),-1);
    }
}
