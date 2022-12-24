package model.interfaces;

import model.Item;
import model.Player;
import service.Combination;

import java.util.List;

public interface ICombinable {

    default boolean combination(List<Item> itensCombination, Player player){
        return new Combination(player,itensCombination).run();
    }

    int getCombine();
}
