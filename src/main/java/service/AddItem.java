package service;

import model.Inventory;
import model.Item;

public final class AddItem {

    private final Inventory inventory;
    private final Item item;

    public AddItem(Inventory inventory, Item item) {
        this.inventory = inventory;
        this.item = item;
    }

    public boolean run() {
        if (!checkInventoryCapacity()) return false;
        addItem();
        updateInventoryCapacity();
        return true;
    }

    private boolean checkInventoryCapacity() {
        return this.item.getWeight() + this.inventory.getCapacity() <= this.inventory.getMaxCapacity();
    }

    private void addItem(){
        this.inventory.getMapItem()
                .put(this.item.getName(), item);

    }

    private void updateInventoryCapacity() {
        this.inventory.updadeCapacity(this.item.getWeight());
    }
}
