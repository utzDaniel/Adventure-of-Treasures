package backend.service.component;

import backend.controller.interfaces.ICoordinate;
import backend.enums.MovePlayer;
import backend.exception.MoveException;
import backend.service.model.Player;

import java.util.Arrays;

public final class LookItem {

    private final Player player;
    private final String direction;
    private final ICoordinate coordinate;

    public LookItem(Player player) {
        this.player = player;
        this.direction = player.getDirection();
        this.coordinate = player.getLocation();
    }

    public IItemDomain run() {
        ICoordinate coordinate = lookToDirection();
        updateCoordinate(coordinate);
        return getItem();
    }

    private ICoordinate lookToDirection() {
        return Arrays.stream(MovePlayer.values())
                .filter(move -> move.getDirection().equals(this.direction))
                .findFirst().map(MovePlayer::getCoordinate)
                .orElseThrow(() -> new MoveException("Direção invalida!"));
    }

    private void updateCoordinate(ICoordinate coordinate) {
        this.coordinate.move(coordinate);
    }

    private IItemDomain getItem() {
        return this.player.getCurrentMap().getItem(this.coordinate);
    }
}
