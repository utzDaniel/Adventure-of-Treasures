package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.model.Inventory;
import backend.service.model.Item;

public final class AddItemInventoryCommand implements ICommand {

    private final Item item;
    private final Inventory inventory;

    public AddItemInventoryCommand(Item item, Inventory inventory) {
        this.item = item;
        this.inventory = inventory;
    }

    @Override
    public TypeMessage execute() {
        this.inventory.addItem(this.item);
        return TypeMessage.ADD_ITEM_INVENTORY;
    }

}
