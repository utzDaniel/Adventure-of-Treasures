package backend.service.model;

import backend.repository.interfaces.IMissionEntity;
import backend.service.enums.TypeItem;
import backend.service.interfaces.IMission;

public final class Mission implements IMission {

    private final IMissionEntity entity;

    public Mission(IMissionEntity entity) {
        this.entity = entity;
    }

    @Override
    public int id() {
        return this.entity.idItem();
    }

    @Override
    public boolean isType(TypeItem type) {
        return TypeItem.MISSION == type;
    }

    @Override
    public boolean isRemove() {
        return false;
    }

    @Override
    public String toString() {
        return TypeItem.MISSION.toString();
    }

}
