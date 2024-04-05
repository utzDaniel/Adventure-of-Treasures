package backend.service.component.inventory.quit;

import backend.controller.enums.TypeMessage;
import backend.service.model.Inventory;

public final class InventoryQuit {

    private final Inventory inventory;

    public InventoryQuit(Inventory inventory) {
        this.inventory = inventory;
    }

    public TypeMessage run() {
        var isOpen = this.inventory.openInventory();
        if (!isOpen) return TypeMessage.INVENTORY_ERROR_CLOSED;
        this.inventory.setOpenInventory();
        return TypeMessage.INVENTORY_CLOSED;
    }
}
