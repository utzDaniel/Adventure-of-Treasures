package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.interfaces.ICombinable;
import backend.service.model.Item;

import java.util.List;
import java.util.Optional;

public final class CombinableIncompleteHandler extends Handler<List<Item>> {

    @Override
    protected Optional<TypeMessage> hook(List<Item> items) {
        var combinable = items.stream()
                .filter(v -> v.isType(TypeItem.COMBINABLE))
                .map(v -> (ICombinable) v.getSpecialization(TypeItem.COMBINABLE).orElse(null))
                .toList();

        return combinable.get(0).sizeCombination() <= combinable.size() ?
                Optional.empty() : Optional.of(TypeMessage.COMBINE_ERROR_INCOMPLETE);
    }

}
