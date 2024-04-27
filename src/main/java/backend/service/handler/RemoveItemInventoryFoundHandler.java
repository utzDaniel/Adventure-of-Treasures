package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.model.Inventory;
import backend.service.model.Item;

import java.util.Objects;
import java.util.Optional;

public final class RemoveItemInventoryFoundHandler extends Handler<Item> {

    private final Inventory inventory;

    public RemoveItemInventoryFoundHandler(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    protected Optional<TypeMessage> hook(Item item) {
        var valid = Objects.isNull(this.inventory.getItem(item.getId()));

        return valid ?
                Optional.of(TypeMessage.INVENTORY_ITEM_ERROR_FOUND) : Optional.empty();

    }
}
