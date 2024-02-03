package backend.service.util;

import backend.service.enums.Direction;
import backend.service.enums.Move;
import backend.service.interfaces.ICoordinate;

import java.util.EnumMap;

public final class CalculateCoordinate {
    private static ICoordinate coordinate = ICoordinate.getInstance(-1, -1);
    private static final EnumMap<Direction, Integer> limits = new EnumMap<>(Direction.class);
    private static int distance;
    private static Direction direction;

    private CalculateCoordinate() {
    }

    public static ICoordinate next(ICoordinate coordinate) {
        if (CalculateCoordinate.coordinate.equals(coordinate)) updateDistance();
        else reset(coordinate);
        updateDirection();
        updateCoordinate(CalculateCoordinate.direction, CalculateCoordinate.coordinate);
        return CalculateCoordinate.coordinate;
    }

    public static ICoordinate next(ICoordinate coordinate, Direction direction) {
        updateCoordinate(direction, coordinate);
        return coordinate;
    }

    private static void updateDistance() {
        if (direction == Direction.LESTE && limits.get(Direction.LESTE) == coordinate.y()) {
            distance++;
            startingPoint();
        }
    }

    private static void reset(ICoordinate coordinate) {
        distance = 1;
        CalculateCoordinate.coordinate = coordinate;
        startingPoint();
    }

    private static void startingPoint() {
        direction = Direction.NORTE;
        limits.clear();
        limits.put(Direction.NORTE, coordinate.x() - distance * Move.STEP);
        limits.put(Direction.OESTE, coordinate.y() - distance * Move.STEP);
        limits.put(Direction.SUL, coordinate.x() + distance * Move.STEP);
        limits.put(Direction.LESTE, coordinate.y() + distance * Move.STEP);
        coordinate.updateY(Move.STEP);
        coordinate.updateX(Move.STEP);
    }

    private static void updateDirection() {
        var limit = limits.get(direction);
        direction = switch (direction) {
            case NORTE -> limit == coordinate.x() ? Direction.OESTE : direction;
            case OESTE -> limit == coordinate.y() ? Direction.SUL : direction;
            case SUL -> limit == coordinate.x() ? Direction.LESTE : direction;
            case LESTE -> limit == coordinate.y() ? Direction.NORTE : direction;
        };
    }

    private static void updateCoordinate(Direction direction, ICoordinate coordinate) {
        switch (direction) {
            case LESTE -> coordinate.updateY(Move.STEP);
            case NORTE -> coordinate.updateX(-Move.STEP);
            case OESTE -> coordinate.updateY(-Move.STEP);
            case SUL -> coordinate.updateX(Move.STEP);
        }
    }

}
