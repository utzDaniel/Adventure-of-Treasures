package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.interfaces.ICombinable;
import backend.service.model.Item;

import java.util.List;
import java.util.Optional;

public class CombinableErrorHandler extends Handler<List<Item>> {

    @Override
    protected Optional<TypeMessage> hook(List<Item> items) {
        var combinable = items.stream()
                .filter(v -> v.isType(TypeItem.COMBINABLE))
                .map(v -> (ICombinable) v.getSpecialization(TypeItem.COMBINABLE).orElse(null))
                .toList();

        var isCombine = combinable.stream()
                .allMatch(v -> v.combination() == combinable.get(0).combination());

        return !isCombine ?
                Optional.of(TypeMessage.COMBINE_ERROR_COMBINABLE) : Optional.empty();
    }
}
