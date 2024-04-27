package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.model.Door;

import java.util.Optional;

public final class InteractDoorClosedHandler extends Handler<Door> {

    @Override
    protected Optional<TypeMessage> hook(Door door) {
        return !door.isOpen()
                ? Optional.of(TypeMessage.DOOR_ERROR_CLOSED) : Optional.empty();
    }

}
