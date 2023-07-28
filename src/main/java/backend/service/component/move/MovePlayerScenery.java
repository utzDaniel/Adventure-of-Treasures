package backend.service.component.move;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Move;
import backend.service.interfaces.ICoordinate;
import backend.service.model.Player;

public final class MovePlayerScenery {

    private final Player player;
    private final Move move;

    public MovePlayerScenery(Player player, Move move) {
        this.move = move;
        this.player = player;
    }

    public TypeMessage run() {
        updateImagePlayer();
        updateDirectionPlayer();

        var newCoordate = newCoordinate();
        if (validCoordinate(newCoordate)) updateLocationPlayer(newCoordate);
        return TypeMessage.MOVE_SUCESS;
    }

    private void updateImagePlayer() {
        this.move.run();
        this.player.setIcon(this.move.getImage());
    }

    private ICoordinate newCoordinate() {
        var coordinate = this.player.getLocation();
        return this.move.coordinateByScenery(coordinate);
    }

    private void updateDirectionPlayer() {
        this.player.setMove(move);
    }

    private boolean validCoordinate(ICoordinate coordinate) {
        var area = this.player.getCurrentMap().getArea();
        return area.isBlock(coordinate);
    }

    private void updateLocationPlayer(ICoordinate coordinate) {
        this.player.setLocation(coordinate);
    }
}
