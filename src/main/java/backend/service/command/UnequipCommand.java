package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.handler.Handler;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IEquippable;
import backend.service.model.Inventory;

public final class UnequipCommand implements ICommand {

    private final IEquippable equippable;
    private final Inventory inventory;
    private final Handler<IEquippable> handler;

    public UnequipCommand(IEquippable equippable, Inventory inventory, Handler<IEquippable> handler) {
        this.equippable = equippable;
        this.inventory = inventory;
        this.handler = handler;
    }

    @Override
    public TypeMessage execute() {
        var msg = this.handler.handle(this.equippable);
        if (msg.isPresent()) return msg.get();

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
