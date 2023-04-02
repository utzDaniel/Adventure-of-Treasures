package rules.service;

import rules.exception.InventoryException;
import backend.model.Inventory;
import backend.model.builder.item.Item;

public final class AddItemInventory {

    private final Inventory inventory;
    private final Item item;

    public AddItemInventory(Inventory inventory, Item item) {
        this.inventory = inventory;
        this.item = item;
    }

    public void run() {
        checkInventoryCapacity();
        addItem();
        updateInventoryCapacity();
    }

    private void checkInventoryCapacity() {
        if(this.item.getWeight() + this.inventory.getCapacity() > this.inventory.getMaxCapacity())
            throw new InventoryException("Inventario Cheio!");
    }

    private void addItem(){
        this.inventory.getMapItem()
                .put(this.item.getName(), item);

    }

    private void updateInventoryCapacity() {
        this.inventory.updadeCapacity(this.item.getWeight());
    }
}
