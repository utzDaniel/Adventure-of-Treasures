package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.interfaces.IUsable;
import backend.service.model.Item;

import java.util.Optional;

public final class UsableEnableHandler extends Handler<Item> {

    @Override
    protected Optional<TypeMessage> hook(Item item) {
        var usable = item.getSpecialization(TypeItem.USABLE)
                .map(v -> (IUsable) v)
                .filter(IUsable::isEnabled);

        return usable.isEmpty() ?
                Optional.of(TypeMessage.USABLE_ERROR_ENABLE) : Optional.empty();
    }
}
