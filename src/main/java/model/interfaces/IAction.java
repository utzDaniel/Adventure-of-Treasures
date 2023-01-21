package model.interfaces;

import model.builder.item.Item;

import java.util.List;

public interface IAction {

    boolean action(Item item);
    boolean action(List<Item> itens);
}
