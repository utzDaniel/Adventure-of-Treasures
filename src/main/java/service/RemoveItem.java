package service;

import model.Inventory;
import model.Item;
import model.ItemNotRemove;

public final class RemoveItem {

    private final Inventory inventory;
    private final Item item;

    public RemoveItem(Inventory inventory, Item item) {
        this.inventory = inventory;
        this.item = item;
    }

    public boolean run() {
        if (checkItemRemoved()) return false;
        removeItem();
        updateInventoryCapacity();
        return true;
    }

    private boolean checkItemRemoved(){
        return item instanceof ItemNotRemove;
    }

    private void removeItem(){
        this.inventory.getMapItem()
                        .remove(this.item.getName());

    }

    private void updateInventoryCapacity(){
        this.inventory.updadeCapacity(-this.item.getWeight());

    }
}
