package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.interfaces.IEquippable;
import backend.service.model.Item;

import java.util.Optional;

public final class RemoveItemInventoryEquipHandler extends Handler<Item> {


    @Override
    protected Optional<TypeMessage> hook(Item item) {

        var spec = item.getSpecialization(TypeItem.EQUIPPABLE);

        return spec.isPresent() && ((IEquippable) spec.get()).isEquip() ?
                Optional.of(TypeMessage.REMOVE_ITEM_ERROR_EQUIP) : Optional.empty();
    }
}
