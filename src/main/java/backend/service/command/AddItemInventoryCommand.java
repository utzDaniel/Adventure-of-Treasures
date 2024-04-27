package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IHandler;
import backend.service.model.Inventory;
import backend.service.model.Item;

public final class AddItemInventoryCommand implements ICommand {

    private final Item item;
    private final Inventory inventory;
    private final IHandler<Item> handler;

    public AddItemInventoryCommand(Item item, Inventory inventory, IHandler<Item> handler) {
        this.item = item;
        this.inventory = inventory;
        this.handler = handler;
    }

    @Override
    public TypeMessage execute() {
        var msg = this.handler.handle(this.item);
        if (msg.isPresent()) return msg.get();

        this.inventory.addItem(this.item);
        return TypeMessage.ADD_ITEM_INVENTORY;
    }

}
