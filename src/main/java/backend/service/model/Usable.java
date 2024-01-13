package backend.service.model;

import backend.service.enums.TypeItem;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IUsable;

public final class Usable implements IUsable {

    private final int idMap;
    private final ICoordinate coordinate;
    private boolean enabled;

    public Usable(int idMap, ICoordinate coordinate, boolean enabled) {
        this.idMap = idMap;
        this.coordinate = coordinate;
        this.enabled = enabled;
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
        return this.idMap;
    }

    @Override
    public ICoordinate getCoordinate() {
        return this.coordinate;
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
