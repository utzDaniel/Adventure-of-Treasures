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
            return TypeMessage.REMOVE_INVENTORY_ERRO;

        if (!this.item.isRemove())
            return TypeMessage.REMOVE_INVENTORY_ERRO_EQUIP;

        this.inventory.removeItem(this.item);

        return TypeMessage.REMOVE_INVENTORY;
    }
}
