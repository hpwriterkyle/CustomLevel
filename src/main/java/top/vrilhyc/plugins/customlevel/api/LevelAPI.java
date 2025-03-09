package top.vrilhyc.plugins.customlevel.api;

import top.vrilhyc.plugins.customlevel.levels.Level;
import top.vrilhyc.plugins.customlevel.levels.storager.LevelStorager;

public class LevelAPI {
    public static boolean addExperience(LevelStorager levelStorager,double experience){
        Level level = levelStorager.getLevelModel();
        level.getLadder().addExperience(levelStorager,experience);
        return true;
    }

    public static boolean addLevel(LevelStorager levelStorager,long level){
        Level levels = levelStorager.getLevelModel();
        levels.getLadder().levelUp(levelStorager,level);
        return true;
    }

    public static long getLevel(LevelStorager levelStorager){
        return levelStorager.getLevel();
    }

    public static double getExperience(LevelStorager levelStorager){
        return levelStorager.getExperience();
    }
}
