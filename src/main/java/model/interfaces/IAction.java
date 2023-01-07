package model.interfaces;

import model.Item;
import model.Player;

import java.util.List;

public interface IAction {

    boolean action(Item item, Player player);
    boolean action(List<Item> itens, Player player);
}
