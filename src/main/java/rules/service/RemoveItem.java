package rules.service;

import rules.exception.InventoryException;
import backend.model.Inventory;
import backend.model.builder.item.Item;
import backend.model.interfaces.IMission;

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
        this.inventory.getMapItem()
                .remove(this.item.getName());

    }

    private void updateInventoryCapacity() {
        this.inventory.updadeCapacity(-this.item.getWeight());

    }
}
