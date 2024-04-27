package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.IMove;

import java.util.Optional;

public final class InteractCommandErrorHandler extends Handler<IMove> {

    @Override
    protected Optional<TypeMessage> hook(IMove move) {
        var coordinate = move.getCoordinate();
        coordinate.move(move.getDirection().getMove());

        var valid = move.getCurrentMap().isInteract(coordinate);

        return valid
                ? Optional.empty() : Optional.of(TypeMessage.INTERACT_ERROR);
    }
}
