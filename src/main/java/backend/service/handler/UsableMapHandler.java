package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.interfaces.IUsable;
import backend.service.model.Item;

import java.util.Optional;

public final class UsableMapHandler extends Handler<Item> {

    private final int idMap;

    public UsableMapHandler(int idMap) {
        this.idMap = idMap;
    }

    @Override
    protected Optional<TypeMessage> hook(Item item) {
        var usable = item.getSpecialization(TypeItem.USABLE)
                .map(v -> (IUsable) v)
                .filter(v -> v.getIdMap() == this.idMap);

        return usable.isEmpty() ?
                Optional.of(TypeMessage.USABLE_ERROR_MAP) : Optional.empty();
    }
}
