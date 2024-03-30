package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.model.Inventory;
import backend.service.model.Item;

import java.util.Objects;

public final class RemoveItemInventoryCommand implements ICommand {

    private final Item item;
    private final Inventory inventory;

    public RemoveItemInventoryCommand(Item item, Inventory inventory) {
        this.item = item;
        this.inventory = inventory;
    }

    @Override
    public TypeMessage execute() {
        var isRemove = this.item.isRemove();
        if (!isRemove.isSuccess()) return isRemove;
        if (Objects.isNull(this.inventory.getItem(this.item.getId())))
            return TypeMessage.INVENTORY_ITEM_ERRO;
        this.inventory.removeItem(this.item);
        return TypeMessage.REMOVE_ITEM_INVENTORY;
    }

    @Override
    public void undo() {
        new AddItemInventoryCommand(this.item, this.inventory).execute();
    }

}
