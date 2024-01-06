package backend.service.model;

import backend.service.enums.TypeItem;
import backend.service.interfaces.IMission;

public final class Mission implements IMission {


    @Override
    public boolean isType(TypeItem type) {
        return TypeItem.MISSION == type;
    }

    @Override
    public boolean isRemovable() {
        return false;
    }

    @Override
    public String toString() {
        return TypeItem.MISSION.toString();
    }
}
