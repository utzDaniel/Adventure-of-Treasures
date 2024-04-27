package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.infra.CacheService;
import backend.service.model.Door;

import java.util.Optional;

public final class InteractDoorMapFoundHandler extends Handler<Door> {

    @Override
    protected Optional<TypeMessage> hook(Door door) {
        var mapGame = CacheService.getMapGame(door.getIdMapOutside());

        return mapGame.isEmpty()
                ? Optional.of(TypeMessage.MAP_ERROR_FOUND) : Optional.empty();
    }

}
