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
        var spec = this.item.getSpecialization(TypeItem.EQUIPPABLE);
        if (spec.isEmpty()) return TypeMessage.ITEM_ERROR_EQUIPPABLE;
        var equippable = (IEquippable) spec.get();

        if (equippable.isEquip()) {
            if (!this.inventory.updateMaxCapacity(-equippable.getUpCapacity()))
                return getUnequipTypeMessageErro();
            equippable.setEquip(!equippable.isEquip());
            return getUnequipTypeMessage();
        } else {
            this.inventory.updateMaxCapacity(equippable.getUpCapacity());
            equippable.setEquip(!equippable.isEquip());
            return getEquipTypeMessageSuccess();
        }
    }

    @Override
    public void undo() {
        var spec = this.item.getSpecialization(TypeItem.EQUIPPABLE);
        if (spec.isEmpty()) return;
        var equippable = (IEquippable) spec.get();

        if (equippable.isEquip()) {
            this.inventory.updateMaxCapacity(-equippable.getUpCapacity());
            equippable.setEquip(!equippable.isEquip());
        } else {
            this.inventory.updateMaxCapacity(equippable.getUpCapacity());
            equippable.setEquip(!equippable.isEquip());
        }
    }

    private TypeMessage getEquipTypeMessageSuccess() {
        return switch (this.item.getId()) {
            case 10 -> TypeMessage.EQUIP_SCHOOLBAG;
            case 16 -> TypeMessage.EQUIP_TORCH;
            default -> TypeMessage.EQUIP;
        };
    }

    private TypeMessage getUnequipTypeMessage() {
        return switch (this.item.getId()) {
            case 10 -> TypeMessage.UNEQUIP_SCHOOLBAG;
            case 16 -> TypeMessage.UNEQUIP_TORCH;
            default -> TypeMessage.UNEQUIP;
        };
    }

    private TypeMessage getUnequipTypeMessageErro() {
        return this.item.getId() == 10 ? TypeMessage.UNEQUIP_ERROR_SCHOOLBAG : TypeMessage.UNEQUIP_ERROR;
    }

}
