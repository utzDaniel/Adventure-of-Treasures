package backend.service;

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
        if (direction == Direction.EAST && limits.get(Direction.EAST) == coordinate.y()) {
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
        direction = Direction.NORTH;
        limits.clear();
        limits.put(Direction.NORTH, coordinate.x() - distance * Movement.STEP);
        limits.put(Direction.WEST, coordinate.y() - distance * Movement.STEP);
        limits.put(Direction.SOUTH, coordinate.x() + distance * Movement.STEP);
        limits.put(Direction.EAST, coordinate.y() + distance * Movement.STEP);
        coordinate.move(ICoordinate.getInstance(Movement.STEP, Movement.STEP));
    }

    private static void updateDirection() {
        var limit = limits.get(direction);
        direction = switch (direction) {
            case NORTH -> limit == coordinate.x() ? Direction.WEST : direction;
            case WEST -> limit == coordinate.y() ? Direction.SOUTH : direction;
            case SOUTH -> limit == coordinate.x() ? Direction.EAST : direction;
            case EAST -> limit == coordinate.y() ? Direction.NORTH : direction;
        };
    }

}
