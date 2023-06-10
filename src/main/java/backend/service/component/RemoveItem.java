package backend.service.component;

import backend.exception.InventoryException;
import backend.service.model.Inventory;

public final class RemoveItem {

    private final Inventory inventory;
    private final IItemDomain item;

    public RemoveItem(Inventory inventory, IItemDomain item) {
        this.inventory = inventory;
        this.item = item;
    }

    public void run() {
        checkItemRemoved();
        removeItem();
        updateInventoryCapacity();
    }

    private void checkItemRemoved() {
        if (!item.isRemovable())
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
