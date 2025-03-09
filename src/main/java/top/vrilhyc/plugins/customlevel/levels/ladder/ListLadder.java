package top.vrilhyc.plugins.customlevel.levels.ladder;

import top.vrilhyc.plugins.customlevel.levels.storager.LevelStorager;

import java.util.List;

public class ListLadder implements LevelLadder{
    protected List<Double> list;

    public ListLadder(List<Double> list){
        this.list = list;
    }

    @Override
    public double getDemandingExperience(long level) {
        return validLevel(level)?list.get((int)level):-1;
    }

    @Override
    public boolean validLevel(long level) {
        return level<list.size()&&level>=0;
    }
}
