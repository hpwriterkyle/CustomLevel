package top.vrilhyc.plugins.customlevel.levels.storager;

import top.vrilhyc.plugins.customlevel.levels.Level;

public interface LevelStorager {
    double getExperience();
    long getLevel();
    void setLevel(long level);
    void setExperience(double experience);
    Level getLevelModel();
}
