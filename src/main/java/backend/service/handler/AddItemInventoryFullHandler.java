package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.model.Inventory;
import backend.service.model.Item;

import java.util.Optional;

public final class AddItemInventoryFullHandler extends Handler<Item> {

    private final Inventory inventory;

    public AddItemInventoryFullHandler(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    protected Optional<TypeMessage> hook(Item item) {
        var valid = this.inventory.hasSpace(item.getWeight());
        return valid ? Optional.empty() : Optional.of(TypeMessage.INVENTORY_ERROR_FULL);
    }
}
