package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.repository.singleton.ItemRepository;
import backend.service.enums.TypeItem;
import backend.service.infra.Cache;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IEventInventory;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;

import java.util.Optional;

public final class EventInventoryCommand implements ICommand {

    private final Item item;
    private Item newItem;
    private final Inventory inventory;
    private boolean isAdd;


    public EventInventoryCommand(Item item, Inventory inventory) {
        this.item = item;
        this.newItem = null;
        this.inventory = inventory;
        this.isAdd = false;
    }

    @Override
    public Optional<TypeMessage> execute() {
        var spec = this.item.getSpecialization(TypeItem.EVENT_INVENTORY);
        if (spec.isEmpty()) return Optional.empty();
        var event = (IEventInventory) spec.get();

        var item = Cache.getItem(event.getIdItemNew());
        if (item.isEmpty()) return Optional.of(TypeMessage.ITEM_ERRO_FOUND);
        this.newItem = item.get();

        if (!this.inventory.hasSpace(this.newItem.getWeight())) return Optional.of(TypeMessage.INVENTORY_ERRO_FULL);
        this.isAdd = true;
        this.inventory.addItem(this.newItem);

        return getEventTypeMessage();
    }


    @Override
    public void undo() {
        if (this.isAdd) this.inventory.removeItem(this.newItem);
        this.isAdd = false;
    }

    private Optional<TypeMessage> getEventTypeMessage() {
        return switch (this.newItem.getId()) {
            case 1 -> Optional.of(TypeMessage.EVENT_INVENTORY_KEY);
            default -> Optional.empty();
        };
    }


}
