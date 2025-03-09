package top.vrilhyc.plugins.customlevel.levels.ladder;

import org.bukkit.Bukkit;
import top.vrilhyc.plugins.customlevel.events.LevelChangeEvent;
import top.vrilhyc.plugins.customlevel.levels.storager.LevelStorager;

public interface LevelLadder {
    default boolean levelUp(LevelStorager levelStorager,long level){
        LevelChangeEvent levelChangeEvent = new LevelChangeEvent(levelStorager,0D,level);
        Bukkit.getPluginManager().callEvent(levelChangeEvent);
        levelStorager.setLevel(levelStorager.getLevel()+levelChangeEvent.getLeveled());
        return true;
    }
    default boolean addExperience(LevelStorager levelStorager,double experience){
        double experiences = experience;
        int level = (int)levelStorager.getLevel();
        for(;experiences>=0;level++){
            if(!validLevel(level)){
                levelStorager.setExperience(levelStorager.getExperience()+experience);
                levelStorager.setLevel(level);
                return true;
            }
            experiences = experiences-getDemandingExperience(level);
        }
        LevelChangeEvent levelChangeEvent = new LevelChangeEvent(levelStorager,getDemandingExperience(level)+experiences,level);
        Bukkit.getPluginManager().callEvent(levelChangeEvent);
        levelStorager.setExperience(levelChangeEvent.getExperience());
        levelStorager.setLevel(levelChangeEvent.getLeveled());
        return true;
    }
    double getDemandingExperience(long level);
    boolean validLevel(long level);
}
