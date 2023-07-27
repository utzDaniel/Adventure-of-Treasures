package backend.service.component.move;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Move;
import backend.service.interfaces.ICoordinate;
import backend.service.model.Player;

import java.util.Locale;

public final class MovePlayerScenery {

    private final String direction;
    private final Player player;
    private final ICoordinate coordinate;
    private Move move;

    public MovePlayerScenery(Player player, String direction) {
        this.direction = direction;
        this.player = player;
        this.coordinate = player.getLocation();
    }

    public TypeMessage run() {
        this.move = getMovePlayer();
        updateImagePlayer();
        updateDirectionPlayer();
        updateCoordinate();
        if (validCoordinate()) updateLocationPlayer();
        return TypeMessage.MOVE_SUCESS;
    }

    private Move getMovePlayer() {
        return Enum.valueOf(Move.class, this.direction.toUpperCase(Locale.ROOT));
    }

    private void updateImagePlayer() {
        this.move.run();
        this.player.setIcon(this.move.getImage());
    }

    private void updateCoordinate() {
        this.coordinate.move(move.getCoordinate());
    }

    private void updateDirectionPlayer() {
        this.player.setDirection(this.direction);
    }

    private boolean validCoordinate() {
        var area = this.player.getCurrentMap().getArea();
        return area.isBlock(this.coordinate);
    }

    private void updateLocationPlayer() {
        this.player.setLocation(this.coordinate);
    }
}
