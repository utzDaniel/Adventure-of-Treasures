package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IEquippable;
import backend.service.model.Inventory;

public final class EquipCommand implements ICommand {

    private final IEquippable equippable;
    private final Inventory inventory;

    public EquipCommand(IEquippable equippable, Inventory inventory) {
        this.equippable = equippable;
        this.inventory = inventory;
    }

    @Override
    public TypeMessage execute() {
        this.inventory.updateMaxCapacity(this.equippable.getUpCapacity());
        this.equippable.setEquip(true);

        return getEquipTypeMessageSuccess();
    }

    private TypeMessage getEquipTypeMessageSuccess() {
        return switch (this.equippable.id()) {
            case 10 -> TypeMessage.EQUIP_SCHOOLBAG;
            case 16 -> TypeMessage.EQUIP_TORCH;
            default -> TypeMessage.EQUIP;
        };
    }
}
