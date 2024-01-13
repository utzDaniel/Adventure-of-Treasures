package backend.service.model;

import backend.service.enums.TypeItem;
import backend.service.interfaces.IEvent;

public final class Event implements IEvent {

    private final int idMap;
    private final int idDoor;
    private boolean active;

    public Event(int idMap, int idDoor) {
        this.idMap = idMap;
        this.idDoor = idDoor;
        this.active = false;
    }

    @Override
    public boolean isType(TypeItem type) {
        return TypeItem.EVENT == type;
    }

    @Override
    public boolean isRemove() {
        return true;
    }

    @Override
    public String toString() {
        return TypeItem.EVENT.toString();
    }

    @Override
    public int getIdMap() {
        return this.idMap;
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
