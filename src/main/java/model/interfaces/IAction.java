package model.interfaces;

import java.util.List;

public interface IAction {

    boolean action();
    boolean action(List<ICombinable> itens);
    String getEffect();
}
