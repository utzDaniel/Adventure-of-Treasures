package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IEquipable;
import backend.service.model.Inventory;
import backend.service.model.Item;

import java.util.Optional;

public final class EquipableCommand implements ICommand {

    private final Item item;
    private final Inventory inventory;

    public EquipableCommand(Item item, Inventory inventory) {
        this.item = item;
        this.inventory = inventory;
    }

    @Override
    public Optional<TypeMessage> execute() {

        var spec = this.item.getSpecialization(TypeItem.EQUIPABLE);
        if (spec.isEmpty()) return Optional.empty();
        var equipable = (IEquipable) spec.get();

        if (equipable.isEquip()) {
            if (!this.inventory.updadeMaxCapacity(-equipable.getUpCapacity()))
                return getUnequipTypeMessageErro();
            equipable.setEquip(!equipable.isEquip());
            return getUnequipTypeMessage();
        } else {
            this.inventory.updadeMaxCapacity(equipable.getUpCapacity());
            equipable.setEquip(!equipable.isEquip());
            return getEquipTypeMessageSucess();
        }
    }

    @Override
    public void undo() {
        var spec = this.item.getSpecialization(TypeItem.EQUIPABLE);
        if (spec.isEmpty()) return;
        var equipable = (IEquipable) spec.get();

        if (equipable.isEquip()) {
            this.inventory.updadeMaxCapacity(-equipable.getUpCapacity());
            equipable.setEquip(!equipable.isEquip());
        } else {
            this.inventory.updadeMaxCapacity(equipable.getUpCapacity());
            equipable.setEquip(!equipable.isEquip());
        }
    }

    private Optional<TypeMessage> getEquipTypeMessageSucess() {
        return switch (this.item.getId()) {
            case 10 -> Optional.of(TypeMessage.EQUIP_SCHOOLBAG);
            case 16 -> Optional.of(TypeMessage.EQUIP_TORCH);
            default -> Optional.empty();
        };
    }

    private Optional<TypeMessage> getUnequipTypeMessage() {
        return switch (this.item.getId()) {
            case 10 -> Optional.of(TypeMessage.UNEQUIP_SCHOOLBAG);
            case 16 -> Optional.of(TypeMessage.UNEQUIP_TORCH);
            default -> Optional.empty();
        };
    }

    private Optional<TypeMessage> getUnequipTypeMessageErro() {
        return switch (this.item.getId()) {
            case 10 -> Optional.of(TypeMessage.UNEQUIP_ERRO_SCHOOLBAG);
            default -> Optional.empty();
        };
    }

}
