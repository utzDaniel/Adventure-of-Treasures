package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IEquippable;
import backend.service.model.Inventory;
import backend.service.model.Item;

public final class EquippableCommand implements ICommand {

    private final Item item;
    private final Inventory inventory;

    public EquippableCommand(Item item, Inventory inventory) {
        this.item = item;
        this.inventory = inventory;
    }

    @Override
    public TypeMessage execute() {
        var equippable = (IEquippable) this.item.getSpecialization(TypeItem.EQUIPPABLE).orElse(null);

        assert equippable != null;

        var typeMessage = getCommand(equippable).execute();
        if (typeMessage.isSuccess()) this.item.warn();

        return typeMessage;
    }

    private ICommand getCommand(IEquippable equippable) {
        if (equippable.isEquip()) {
            return CommandFactory.createUnequipCommand(this.inventory, equippable);
        } else {
            return CommandFactory.createEquipCommand(this.inventory, equippable);
        }
    }

}
