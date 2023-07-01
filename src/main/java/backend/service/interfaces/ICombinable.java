package backend.service.interfaces;

import backend.service.component.combination.Combination;

import java.util.List;

public interface ICombinable extends IEffect {

    default boolean combination(List<ICombinable> itensCombination) {
        return new Combination(itensCombination).run();
    }

    int getCombine();

    String getName();

    String getEffect();
}
