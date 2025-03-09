package top.vrilhyc.plugins.customlevel.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import top.vrilhyc.plugins.customlevel.levels.Level;
import top.vrilhyc.plugins.customlevel.levels.storager.LevelStorager;

public class LevelChangeEvent extends Event {
    public static HandlerList handlerList = new HandlerList();
    protected Level level;
    protected LevelStorager storager;
    protected double experience;
    protected long leveled;

    public LevelChangeEvent(LevelStorager levelStorager,double experience,long leveled){
        this.level = levelStorager.getLevelModel();
        this.storager = levelStorager;
        this.experience = experience;
        this.leveled = leveled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public Level getLevel() {
        return level;
    }

    public double getExperience() {
        return experience;
    }

    public long getLeveled() {
        return leveled;
    }

    public LevelStorager getStorager() {
        return storager;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
