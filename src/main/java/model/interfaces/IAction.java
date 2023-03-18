package model.interfaces;

import model.builder.item.Item;

import java.util.List;

public interface IAction {

    boolean action();
    boolean action(List<ICombinable> itens);
    String getEffect();
}
