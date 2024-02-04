package backend.service.component.move;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Direction;
import backend.service.enums.MoveCommand2;
import backend.service.interfaces.ICoordinate;
import backend.service.model.Player;

import java.util.Locale;

public final class MovePlayer {
    private final Player player;
    private final Direction direction;

    public MovePlayer(Direction direction, Player player) {
        this.direction = direction;
        this.player = player;
    }

    public TypeMessage run() {
        var coordinate = this.player.getCoordinate();
        TypeMessage typeMessage;
        ICoordinate newCoordinate;

        MoveCommand2 move = Enum.valueOf(MoveCommand2.class, this.direction.name().toUpperCase(Locale.ROOT));

        if (move.isNextScenery(coordinate)) {
            typeMessage = new MovePlayerNextScenery(this.player, move).run();
            newCoordinate = move.coordinateByNextScenery(coordinate);
        } else {
            typeMessage = new MovePlayerScenery(this.player, move).run();
            newCoordinate = move.coordinateByScenery(coordinate);
        }

        if (TypeMessage.MOVE.equals(typeMessage)) {
            this.player.updateMove(this.direction, newCoordinate);
        }

        this.player.updateMove(this.direction, this.player.getCoordinate());
        return typeMessage;
    }

}
