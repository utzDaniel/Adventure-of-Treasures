package backend.service.component;

import backend.exception.InventoryException;
import backend.service.model.Inventory;

public final class AddItemInventory {

    private final Inventory inventory;
    private final IItemDomain item;

    public AddItemInventory(Inventory inventory, IItemDomain item) {
        this.inventory = inventory;
        this.item = item;
    }

    public void run() {
        checkInventoryCapacity();
        addItem();
        updateInventoryCapacity();
    }

    private void checkInventoryCapacity() {
        if (this.item.getWeight() + this.inventory.getCapacity() > this.inventory.getMaxCapacity())
            throw new InventoryException("Inventario Cheio!");
    }

    private void addItem() {
        this.inventory.getMapItem()
                .put(this.item.getName(), item);

    }

    private void updateInventoryCapacity() {
        this.inventory.updadeCapacity(this.item.getWeight());
    }
}
