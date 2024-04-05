package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.model.Item;

import java.util.List;
import java.util.Optional;

public final class CombinableEmptyHandler extends Handler<List<Item>> {

    @Override
    protected Optional<TypeMessage> hook(List<Item> items) {
        var valid = items.stream().noneMatch(v -> v.isType(TypeItem.COMBINABLE));

        return valid ? Optional.of(TypeMessage.ITEM_ERROR_COMBINABLE) : Optional.empty();
    }

}
