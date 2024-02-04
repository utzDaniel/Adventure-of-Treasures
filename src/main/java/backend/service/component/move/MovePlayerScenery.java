package backend.service.component.move;

import backend.controller.enums.TypeMessage;
import backend.service.enums.MoveCommand2;
import backend.service.interfaces.ICoordinate;
import backend.service.model.Player;

public final class MovePlayerScenery {

    private final Player player;
    private final MoveCommand2 move;

    public MovePlayerScenery(Player player, MoveCommand2 move) {
        this.move = move;
        this.player = player;
    }

    public TypeMessage run() {
        var newCoordate = newCoordinate();
        if (!validCoordinate(newCoordate))
            return TypeMessage.MOVE_BLOCKED;
        return TypeMessage.MOVE;
    }

    private ICoordinate newCoordinate() {
        var coordinate = this.player.getCoordinate();
        return this.move.coordinateByScenery(coordinate);
    }

    private boolean validCoordinate(ICoordinate coordinate) {
        var area = this.player.getCurrentMap().getArea();
        return area.isAccessible(coordinate);
    }
}
