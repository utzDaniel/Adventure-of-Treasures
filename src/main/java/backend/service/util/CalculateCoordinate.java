package backend.service.util;

import backend.service.enums.Direction;
import backend.service.enums.Movement;
import backend.service.interfaces.ICoordinate;

import java.util.EnumMap;

public final class CalculateCoordinate {
    private static ICoordinate coordinate = ICoordinate.getInstance(-1, -1);
    private static final EnumMap<Direction, Integer> limits = new EnumMap<>(Direction.class);
    private static int distance;
    private static Direction direction;

    private CalculateCoordinate() {
    }

    public static void next(ICoordinate coordinate) {
        if (CalculateCoordinate.coordinate.equals(coordinate)) updateDistance();
        else reset(coordinate);
        updateDirection();
        CalculateCoordinate.coordinate.move(direction.getMove());
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
        limits.put(Direction.NORTE, coordinate.x() - distance * Movement.STEP);
        limits.put(Direction.OESTE, coordinate.y() - distance * Movement.STEP);
        limits.put(Direction.SUL, coordinate.x() + distance * Movement.STEP);
        limits.put(Direction.LESTE, coordinate.y() + distance * Movement.STEP);
        coordinate.move(ICoordinate.getInstance(Movement.STEP, Movement.STEP));
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

}
