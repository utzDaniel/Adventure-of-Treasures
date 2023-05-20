package rules.service;

import backend.enums.MovePlayer;
import backend.model.Coordinate;
import backend.model.Player;
import backend.model.builder.item.Item;
import rules.exception.MoveException;
import rules.interfaces.ICoordinate;

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

    public Item run() {
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

    private Item getItem() {
        return this.player.getCurrentMap().getItem(this.coordinate);
    }
}
