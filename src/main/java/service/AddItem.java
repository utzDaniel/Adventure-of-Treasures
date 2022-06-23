package service;

import model.Inventory;
import model.Item;

public class AddItem {

    private final Inventory inventory;
    private final Item item;

    public AddItem(Inventory inventory, Item item) {
        this.inventory = inventory;
        this.item = item;
    }

    public boolean run() {
        if (!checkCapacity()) return false;
        this.inventory.addItem(this.item);
        setCapacity(this.item.getWeight());
        return true;
    }

    private boolean checkCapacity() {
        return this.item.getWeight() + this.inventory.getCapacity() <= this.inventory.getMaxCapacity();
    }

    private void setCapacity(int weight) {
        this.inventory.setCapacity(weight);
    }
}
