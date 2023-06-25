package backend.service.component.take.valid;


import backend.service.exception.InventoryException;
import backend.service.model.Inventory;
import backend.service.model.builder.Item;

public final class TakeItemValidIsFull {

    public void valid(Item item, Inventory inventory) {
        var newCapacity = item.getWeight() + inventory.getCapacity();
        if (newCapacity > inventory.getMaxCapacity())
            throw new InventoryException("Inventario Cheio!");
    }
}
