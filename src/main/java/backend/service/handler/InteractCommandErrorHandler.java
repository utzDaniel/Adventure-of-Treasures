package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.interfaces.IMove;

import java.util.Optional;

public final class InteractCommandErrorHandler extends Handler<IMove> {

    @Override
    protected Optional<TypeMessage> hook(IMove move) {
        var valid = move.getCurrentMap().isInteract(move.getCoordinate(), move.getDirection());

        return valid
                ? Optional.empty() : Optional.of(TypeMessage.INTERACT_ERROR);
    }
}
