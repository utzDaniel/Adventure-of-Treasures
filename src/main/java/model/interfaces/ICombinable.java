package model.interfaces;

import model.Item;
import model.Player;
import service.Combination;

import java.util.List;

public interface ICombinable {

    default boolean combination(List<Item> itensCombination){
        return new Combination(itensCombination).run();
    }

    int getCombine();
}
