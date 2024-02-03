package backend.service.component.inventory.open;

import backend.controller.enums.TypeMessage;
import backend.service.model.Inventory;

public final class InventoryOpen {

    private final Inventory inventory;

    public InventoryOpen(Inventory inventory) {
        this.inventory = inventory;
    }

    public TypeMessage run() {
        boolean isOpen = this.inventory.openInventory();

        if (isOpen)
            return TypeMessage.INVENTORY_NOT_OPEN;

        this.inventory.setOpenInventory();

        return TypeMessage.INVENTORY_OPEN;
    }

}
