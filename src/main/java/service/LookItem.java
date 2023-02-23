package service;

import exception.MoveException;
import model.Coordinate;
import model.Player;
import model.builder.item.Item;
import model.enums.MovePlayer;

import java.util.Arrays;

public final class LookItem {

    private final Player player;
    private final String direction;
    private final Coordinate coordinate;

    public LookItem(Player player) {
        this.player = player;
        this.direction = player.getDirection();
        this.coordinate = player.getLocation();
    }

    public Item run() {
        Coordinate coordinate = lookToDirection();
        updateCoordinate(coordinate);
        return getItem();
    }

    private Coordinate lookToDirection() {
        return Arrays.stream(MovePlayer.values())
                .filter(move -> move.getDirection().equals(this.direction))
                .findFirst().map(MovePlayer::getCoordinate)
                .orElseThrow(() -> new MoveException("Direção invalida!"));
    }

    private void updateCoordinate(Coordinate coordinate) {
        this.coordinate.move(coordinate);
    }

    private Item getItem() {
        return this.player.getCurrentMap().getItem(this.coordinate);
    }
}
