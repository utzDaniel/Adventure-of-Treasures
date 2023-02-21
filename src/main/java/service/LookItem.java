package service;

import exception.MoveException;
import model.Coordinate;
import model.enums.MovePlayer;

import java.util.Arrays;

public final class LookItem {

    private final String direction;
    private final Coordinate coordinate;

    public LookItem(String direction, Coordinate coordinate) {
        this.direction = direction;
        this.coordinate = new Coordinate(coordinate.getPoint());
    }

    public Coordinate run() {
        Coordinate coordinate = lookToDirection();
        updateCoordinate(coordinate);
        return this.coordinate;
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
}
