package frontend.interfaces;

import backend.model.interfaces.ICombinable;

import java.util.List;

public interface IAction {

    boolean action();
    boolean action(List<ICombinable> itens);
    String getEffect();
}
