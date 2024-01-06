package backend.service.model;

import backend.service.enums.TypeItem;
import backend.service.interfaces.IUsable;

public final class Usable implements IUsable {

    private final int idMap;

    public Usable(int idMap) {
        this.idMap = idMap;
    }


    @Override
    public boolean isType(TypeItem type) {
        return TypeItem.USABLE == type;
    }

    @Override
    public boolean isRemove() {
        return true;
    }

    @Override
    public String toString() {
        return TypeItem.USABLE.toString();
    }
}
