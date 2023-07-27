package backend.service.component;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.IEquipable;
import backend.service.interfaces.IMission;
import backend.service.model.Inventory;
import backend.service.model.builder.Item;

public final class RemoveItem {

    private final Inventory inventory;
    private final Item item;

    public RemoveItem(Inventory inventory, Item item) {
        this.inventory = inventory;
        this.item = item;
    }

    public TypeMessage run() {
        if (this.item instanceof IMission)
            return TypeMessage.INVENTORY_NOT_REMOVE;

        if (this.item instanceof IEquipable item1 && item1.isEquipped())
            return TypeMessage.INVENTORY_NOT_REMOVE_EQUIP;

        this.inventory.removeItem(this.item);
        this.inventory.updadeCapacity(-this.item.getWeight());
        return TypeMessage.DROP_SUCESS;
    }
}
