package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.infra.CacheService;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.IHandler;
import backend.service.interfaces.IMove;
import backend.service.model.Door;

public final class InteractDoorCommand implements ICommand {

    private final IMove move;
    private final IHandler<Door> handler;

    public InteractDoorCommand(IMove move, IHandler<Door> handler) {
        this.move = move;
        this.handler = handler;
    }

    @Override
    public TypeMessage execute() {
        var coordinate = this.move.getCoordinate();
        coordinate.move(this.move.getDirection().getMove());
        var door = this.move.getCurrentMap().getDoor(coordinate).orElse(null);

        var msg = this.handler.handle(door);
        if (msg.isPresent()) return msg.get();

        assert door != null;
        var mapGame = CacheService.getMapGame(door.getIdMapOutside()).orElse(null);

        this.move.updateMove(this.move.getDirection(), door.getCoordinateOutside());
        this.move.setCurrentMap(mapGame);
        return TypeMessage.DOOR_OPEN;
    }

}
