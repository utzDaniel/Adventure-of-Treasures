package backend.service.component;

import backend.controller.exception.InventoryException;
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

    public void run() {
        checkItemRemoved();
        removeItem();
        updateInventoryCapacity();
    }

    private void checkItemRemoved() {
        if (item instanceof IMission)
            throw new InventoryException("Item não pode ser removido!");
    }

    private void removeItem() {
        this.inventory.removeItem(this.item);

    }

    private void updateInventoryCapacity() {
        this.inventory.updadeCapacity(-this.item.getWeight());

    }
}
