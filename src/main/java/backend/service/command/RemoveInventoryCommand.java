package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IEquipable;
import backend.service.model.Inventory;
import backend.service.model.Item;

import java.util.Optional;

public final class RemoveInventoryCommand implements ICommand {

    private final Item item;
    private final Inventory inventory;
    private boolean isRemove;


    public RemoveInventoryCommand(Item item, Inventory inventory) {
        this.item = item;
        this.inventory = inventory;
        this.isRemove = false;
    }

    @Override
    public Optional<TypeMessage> execute() {
        var spec1 = this.item.getSpecialization(TypeItem.MISSION);
        if (spec1.isPresent()) return Optional.of(TypeMessage.REMOVE_INVENTORY_ERRO);

        var spec2 = this.item.getSpecialization(TypeItem.EQUIPABLE);
        if (spec2.isPresent() && ((IEquipable) spec2.get()).isEquip())
            return Optional.of(TypeMessage.REMOVE_INVENTORY_ERRO_EQUIP);

        this.isRemove = true;
        this.inventory.removeItem(this.item);

        return Optional.of(TypeMessage.REMOVE_INVENTORY);
    }

    @Override
    public void undo() {
        if (this.isRemove) this.inventory.addItem(this.item);
        this.isRemove = false;
    }

}
