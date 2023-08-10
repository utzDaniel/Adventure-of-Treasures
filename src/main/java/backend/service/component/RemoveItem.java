package backend.service.component;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.model.Inventory;
import backend.service.model.Item;

public final class RemoveItem {

    private final Inventory inventory;
    private final Item item;

    public RemoveItem(Inventory inventory, Item item) {
        this.inventory = inventory;
        this.item = item;
    }

    public TypeMessage run() {
        if (this.item.isType(TypeItem.MISSION))
            return TypeMessage.INVENTORY_NOT_REMOVE;

        if (this.item.isType(TypeItem.EQUIPABLE) && inventory.isAtivo(item))
            return TypeMessage.INVENTORY_NOT_REMOVE_EQUIP;

        this.inventory.removeItem(this.item);
        this.inventory.updadeCapacity(-this.item.getWeight());
        return TypeMessage.DROP_SUCESS;
    }
}
