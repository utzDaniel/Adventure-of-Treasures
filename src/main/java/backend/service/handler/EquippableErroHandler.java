package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.IEquippable;

import java.util.Optional;

public final class EquippableErroHandler extends Handler<IEquippable> {

    private final boolean equip;

    public EquippableErroHandler(boolean equip) {
        this.equip = equip;
    }

    @Override
    protected Optional<TypeMessage> hook(IEquippable equippable) {
        var valid = equippable.isEquip() == this.equip;

        return valid ? Optional.empty() : Optional.of(getTypeMessage());

    }

    private TypeMessage getTypeMessage() {
        return this.equip ? TypeMessage.UNEQUIP_ERROR : TypeMessage.EQUIP_ERROR;
    }

}
