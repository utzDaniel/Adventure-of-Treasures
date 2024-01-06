package backend.service.model;

import backend.service.enums.TypeItem;
import backend.service.interfaces.ICombinable;

public final class Combinable implements ICombinable {

    private final int newItem;
    private final int sizeCombination;

    public Combinable(int newItem, int sizeCombination) {
        this.newItem = newItem;
        this.sizeCombination = sizeCombination;
    }

    public int getNewItem() {
        return newItem;
    }

    public int getSizeCombination() {
        return sizeCombination;
    }

    @Override
    public boolean isType(TypeItem type) {
        return TypeItem.COMBINABLE == type;
    }

    @Override
    public boolean isRemovable() {
        return true;
    }

    @Override
    public String toString() {
        return TypeItem.COMBINABLE.toString();
    }
}
