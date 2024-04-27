package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IEquippable;
import backend.service.model.Inventory;

public final class UnequipCommand implements ICommand {

    private final IEquippable equippable;
    private final Inventory inventory;

    public UnequipCommand(IEquippable equippable, Inventory inventory) {
        this.equippable = equippable;
        this.inventory = inventory;
    }

    @Override
    public TypeMessage execute() {
        this.inventory.updateMaxCapacity(-equippable.getUpCapacity());
        this.equippable.setEquip(false);

        return getUnequippeTypeMessage();
    }

    private TypeMessage getUnequippeTypeMessage() {
        return switch (this.equippable.id()) {
            case 10 -> TypeMessage.UNEQUIP_SCHOOLBAG;
            case 16 -> TypeMessage.UNEQUIP_TORCH;
            default -> TypeMessage.UNEQUIP;
        };
    }

}
