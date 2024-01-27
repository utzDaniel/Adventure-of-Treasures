package backend.service.model;

import backend.service.enums.TypeItem;
import backend.service.interfaces.ICombinable;

public final class Combinable implements ICombinable {

    private final int combination;
    private final int sizeCombination;

    public Combinable(int combination, int sizeCombination) {
        this.combination = combination;
        this.sizeCombination = sizeCombination;
    }

    @Override
    public boolean isType(TypeItem type) {
        return TypeItem.COMBINABLE == type;
    }

    @Override
    public boolean isRemove() {
        return true;
    }

    @Override
    public String toString() {
        return TypeItem.COMBINABLE.toString();
    }

    @Override
    public int getCombination() {
        return this.combination;
    }

    @Override
    public int getSizeCombination() {
        return this.sizeCombination;
    }
}
