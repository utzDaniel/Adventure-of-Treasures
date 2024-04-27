package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.infra.CacheService;
import backend.service.model.NPC;

import java.util.Optional;

public final class InteractNPCDoorFoundHandler extends Handler<NPC> {

    @Override
    protected Optional<TypeMessage> hook(NPC npc) {
        var door = CacheService.getDoor(npc.getIdDoor());

        return door.isEmpty()
                ? Optional.of(TypeMessage.DOOR_ERROR_FOUND) : Optional.empty();
    }
}
