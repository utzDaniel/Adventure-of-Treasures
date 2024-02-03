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
        if (CalculateCoordinate.direction == Direction.LESTE &&
                limits.get(Direction.LESTE) == CalculateCoordinate.coordinate.y()) {
            CalculateCoordinate.distance++;
            startingPoint();
        }
    }

    private static void reset(ICoordinate coordinate) {
        CalculateCoordinate.distance = 1;
        CalculateCoordinate.coordinate = coordinate;
        startingPoint();
    }

    private static void startingPoint() {
        CalculateCoordinate.direction = Direction.NORTE;
        CalculateCoordinate.limits.clear();
        CalculateCoordinate.limits.put(Direction.NORTE, CalculateCoordinate.coordinate.x() - CalculateCoordinate.distance * Move.STEP);
        CalculateCoordinate.limits.put(Direction.OESTE, CalculateCoordinate.coordinate.y() - CalculateCoordinate.distance * Move.STEP);
        CalculateCoordinate.limits.put(Direction.SUL, CalculateCoordinate.coordinate.x() + CalculateCoordinate.distance * Move.STEP);
        CalculateCoordinate.limits.put(Direction.LESTE, CalculateCoordinate.coordinate.y() + CalculateCoordinate.distance * Move.STEP);
        CalculateCoordinate.coordinate.updateY(Move.STEP);
        CalculateCoordinate.coordinate.updateX(Move.STEP);
    }

    private static void updateDirection() {
        var limit = CalculateCoordinate.limits.get(CalculateCoordinate.direction);
        CalculateCoordinate.direction = switch (CalculateCoordinate.direction) {
            case NORTE -> limit == CalculateCoordinate.coordinate.x() ? Direction.OESTE : CalculateCoordinate.direction;
            case OESTE -> limit == CalculateCoordinate.coordinate.y() ? Direction.SUL : CalculateCoordinate.direction;
            case SUL -> limit == CalculateCoordinate.coordinate.x() ? Direction.LESTE : CalculateCoordinate.direction;
            case LESTE -> limit == CalculateCoordinate.coordinate.y() ? Direction.NORTE : CalculateCoordinate.direction;
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
