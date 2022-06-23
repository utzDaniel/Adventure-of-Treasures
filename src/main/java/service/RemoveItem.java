package service;

import model.Inventory;
import model.Item;
import model.ItemNotRemove;

public class RemoveItem {

    private final Inventory inventory;
    private final Item item;

    public RemoveItem(Inventory inventory, Item item) {
        this.inventory = inventory;
        this.item = item;
    }

    public boolean run() {
        if (item instanceof ItemNotRemove) return false;
        this.inventory.removerItem(this.item);
        this.inventory.setCapacity(-this.item.getWeight());
        return true;
    }
}
