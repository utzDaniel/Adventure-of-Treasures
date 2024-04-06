package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IUsable;
import backend.service.model.Item;

import java.util.Optional;

public final class UsableCoordinateHandler extends Handler<Item> {

    private final ICoordinate coordinate;

    public UsableCoordinateHandler(ICoordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    protected Optional<TypeMessage> hook(Item item) {
        var usable = item.getSpecialization(TypeItem.USABLE)
                .map(v -> (IUsable) v)
                .filter(v -> v.getCoordinate().equals(this.coordinate));

        return usable.isEmpty() ?
                Optional.of(TypeMessage.USABLE_ERROR_COORDINATE) : Optional.empty();
    }
}
