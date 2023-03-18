package model.interfaces;

import service.Combination;

import java.util.List;

public interface ICombinable {

    default boolean combination(List<ICombinable> itensCombination){
        return new Combination(itensCombination).run();
    }

    int getCombine();
    String getName();
    String getEffect();
}
