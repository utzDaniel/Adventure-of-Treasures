package model;

import service.Combination;

import java.util.List;

public interface ICombinable {

    default boolean combination(List<Item> itensCombination, Player player){
        return new Combination(player).execute(itensCombination);
    }

    int getCombine();
}
