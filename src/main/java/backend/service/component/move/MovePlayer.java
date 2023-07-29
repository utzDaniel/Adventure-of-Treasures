package backend.service.component.move;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Move;
import backend.service.interfaces.ICoordinate;
import backend.service.model.Player;

public final class MovePlayer {
    private final Player player;
    private final Move move;

    public MovePlayer(Move move, Player player) {
        this.move = move;
        this.player = player;
    }

    public TypeMessage run() {
        var coordinate = this.player.getLocation();
        TypeMessage typeMessage;
        ICoordinate newCoordinate;

        if (this.move.isNextScenery(coordinate)) {
            typeMessage = new MovePlayerNextScenery(this.player, this.move).run();
            newCoordinate = this.move.coordinateByNextScenery(coordinate);
        } else {
            typeMessage = new MovePlayerScenery(this.player, this.move).run();
            newCoordinate = this.move.coordinateByScenery(coordinate);
        }

        if (TypeMessage.MOVE_SUCESS.equals(typeMessage))
            updateLocationPlayer(newCoordinate);

        updateImagePlayer();
        updateMovePlayer();
        return typeMessage;
    }

    private void updateImagePlayer() {
        this.move.run();
        this.player.setIcon(this.move.getImage());
    }

    private void updateMovePlayer() {
        this.player.setMove(move);
    }

    private void updateLocationPlayer(ICoordinate coordinate) {
        this.player.setLocation(coordinate);
    }
}
