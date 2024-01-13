package backend.service.model;

import backend.service.enums.TypeItem;
import backend.service.interfaces.IEventInventory;

public final class EventInventory implements IEventInventory {

    private final int idItemNew;

    public EventInventory(int idItemNew) {
        this.idItemNew = idItemNew;
    }

    @Override
    public boolean isType(TypeItem type) {
        return TypeItem.EVENT_INVENTORY == type;
    }

    @Override
    public boolean isRemove() {
        return true;
    }

    @Override
    public String toString() {
        return TypeItem.EVENT_INVENTORY.toString();
    }

    @Override
    public int getIdItemNew() {
        return this.idItemNew;
    }
}
