package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.infra.CacheService;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IMove;
import backend.service.model.Door;

public final class InteractDoorCommand implements ICommand {

    private final Door door;
    private final IMove move;

    public InteractDoorCommand(Door door, IMove move) {
        this.door = door;
        this.move = move;
    }

    @Override
    public TypeMessage execute() {
        var mapGame = CacheService.getMapGame(this.door.getIdMapOutside()).orElse(null);

        this.move.updateMove(this.move.getDirection(), this.door.getCoordinateOutside());
        this.move.setCurrentMap(mapGame);
        return TypeMessage.DOOR_OPEN;
    }

}
