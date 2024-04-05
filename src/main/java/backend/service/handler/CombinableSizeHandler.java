package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.model.Item;

import java.util.List;
import java.util.Optional;

public final class CombinableSizeHandler extends Handler<List<Item>> {

    @Override
    protected Optional<TypeMessage> hook(List<Item> items) {
        var count = items.stream()
                .filter(v -> v.isType(TypeItem.COMBINABLE))
                .count();

        return count == items.size() ?
                Optional.empty() : Optional.of(TypeMessage.COMBINE_ERROR_ALL);
    }

}
