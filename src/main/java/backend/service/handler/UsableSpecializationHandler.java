package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.model.Item;

import java.util.Optional;

public final class UsableSpecializationHandler extends Handler<Item> {

    @Override
    protected Optional<TypeMessage> hook(Item item) {
        var spec = item.getSpecialization(TypeItem.USABLE);

        return spec.isEmpty() ?
                Optional.of(TypeMessage.ITEM_ERROR_USABLE) : Optional.empty();
    }
}
