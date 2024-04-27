package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.IEquippable;
import backend.service.model.Inventory;

import java.util.Optional;

public final class UnequipCapacityHandler extends Handler<IEquippable> {

    private final Inventory inventory;

    public UnequipCapacityHandler(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    protected Optional<TypeMessage> hook(IEquippable equippable) {
        var newCapacity = this.inventory.getMaxCapacity() - equippable.getUpCapacity();
        var valid = this.inventory.getCapacity() > newCapacity;

        return valid ? Optional.of(TypeMessage.UNEQUIP_ERROR_SCHOOLBAG) : Optional.empty();

    }

}
