package backend.service.model;

import backend.service.enums.TypeItem;
import backend.service.interfaces.ICombinable;

public record Combinable(int id, int combination, int sizeCombination) implements ICombinable {

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
}
