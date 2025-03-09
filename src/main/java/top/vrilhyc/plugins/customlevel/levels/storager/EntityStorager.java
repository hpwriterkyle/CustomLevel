package top.vrilhyc.plugins.customlevel.levels.storager;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import top.vrilhyc.plugins.customlevel.CustomLevel;
import top.vrilhyc.plugins.customlevel.PluginInitial;
import top.vrilhyc.plugins.customlevel.levels.Level;

public class EntityStorager implements LevelStorager{
    protected Entity storager;
    protected Level level;

    public EntityStorager(Level level,Entity storager){
        this.level = level;
        this.storager = storager;
    }

    public Entity getStorager() {
        return storager;
    }

    @Override
    public double getExperience() {
        return storager.getPersistentDataContainer().getOrDefault(level.getExperienceKey(), PersistentDataType.DOUBLE,0D);
    }

    @Override
    public long getLevel() {
        return storager.getPersistentDataContainer().getOrDefault(level.getLevelKey(), PersistentDataType.LONG,0L);
    }

    @Override
    public void setLevel(long level) {
        storager.getPersistentDataContainer().set(this.level.getLevelKey(),PersistentDataType.LONG,level);
//        if(CustomLevel.encover_vanilla&&storager instanceof Player){
//            ((Player)storager).setLevel((int)getLevel());
//        }
    }

    @Override
    public void setExperience(double experience) {
        storager.getPersistentDataContainer().set(this.level.getExperienceKey(),PersistentDataType.DOUBLE,experience);
//        if(CustomLevel.encover_vanilla&&storager instanceof Player){
//            ((Player)storager).setExp((float)(getExperience()/level.getLadder().getDemandingExperience(getLevel()))*((Player)storager).getExpToLevel());
//        }
    }

    @Override
    public Level getLevelModel() {
        return level;
    }
}
