package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.infra.CacheService;
import backend.service.model.NPC;

import java.util.Optional;

public final class InteractNPCMapFoundHandler extends Handler<NPC> {

    @Override
    protected Optional<TypeMessage> hook(NPC npc) {
        var mapGame = CacheService.getDoor(npc.getIdDoor())
                .map(d -> CacheService.getMapGame(d.getIdMapOutside()));

        return mapGame.isEmpty()
                ? Optional.of(TypeMessage.MAP_ERROR_FOUND) : Optional.empty();
    }
}
