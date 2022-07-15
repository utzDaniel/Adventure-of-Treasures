package service;

import exception.InventoryException;
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
        checkItemRemoved();
        removeItem();
        updateInventoryCapacity();
        return true;
    }

    private void checkItemRemoved(){
        if(item instanceof ItemNotRemove)
            throw new InventoryException("Item não pode ser removido!");
    }

    private void removeItem(){
        this.inventory.getMapItem()
                        .remove(this.item.getName());

    }

    private void updateInventoryCapacity(){
        this.inventory.updadeCapacity(-this.item.getWeight());

    }
}
