package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.model.Item;

import java.util.List;
import java.util.Optional;

public final class ItemsSizeHandler extends Handler<Item> {

    private final List<Item> items;

    public ItemsSizeHandler(List<Item> items) {
        this.items = items;
    }

    @Override
    protected Optional<TypeMessage> hook(Item item) {
        var valid = this.items.size() == 1 && this.items.contains(item);

        return valid ?
                Optional.empty() : Optional.of(TypeMessage.ITEMS_ERROR_SIZE);
    }
}
