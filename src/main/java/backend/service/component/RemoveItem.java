package backend.service.component;

import backend.service.exception.InventoryException;
import backend.service.interfaces.IEquipable;
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
        checkItemEquipable();
        removeItem();
        updateInventoryCapacity();
    }

    private void checkItemRemoved() {
        if (this.item instanceof IMission)
            throw new InventoryException("Item não pode ser removido!");
    }


    private void checkItemEquipable() {
        if (this.item instanceof IEquipable item1 && item1.isEquipped())
            throw new InventoryException("Não pode remover item equipado!");
    }

    private void removeItem() {
        this.inventory.removeItem(this.item);

    }

    private void updateInventoryCapacity() {
        this.inventory.updadeCapacity(-this.item.getWeight());

    }
}
