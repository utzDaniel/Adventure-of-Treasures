package backend.service.model;

import backend.service.enums.TypeItem;
import backend.service.interfaces.IEventMap;

public final class EventMap implements IEventMap {

    private final int idMap;
    private final String mapImage;
    private final int idDoor;
    private boolean active;

    public EventMap(int idMap, String mapImage, int idDoor) {
        this.idMap = idMap;
        this.mapImage = mapImage;
        this.idDoor = idDoor;
        this.active = false;
    }

    @Override
    public boolean isType(TypeItem type) {
        return TypeItem.EVENT_MAP == type;
    }

    @Override
    public boolean isRemove() {
        return true;
    }

    @Override
    public String toString() {
        return TypeItem.EVENT_MAP.toString();
    }

    @Override
    public int getIdMap() {
        return this.idMap;
    }

    @Override
    public String getMapImage() {
        return this.mapImage;
    }

    @Override
    public int getIdDoor() {
        return this.idDoor;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
