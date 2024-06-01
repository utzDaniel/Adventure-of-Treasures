package backend.service.model;

import backend.repository.interfaces.IUsableEntity;
import backend.service.enums.TypeItem;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IUsable;

public final class Usable implements IUsable {

    private final IUsableEntity entity;
    private boolean enabled;

    public Usable(IUsableEntity entity) {
        this.entity = entity;
        this.enabled = entity.enabled();
    }

    @Override
    public int id() {
        return this.entity.idItem();
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

    @Override
    public int getIdMap() {
        return this.entity.idMap();
    }

    @Override
    public ICoordinate getCoordinate() {
        return ICoordinate.getInstance(this.entity.positionX(), this.entity.positionY());
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
