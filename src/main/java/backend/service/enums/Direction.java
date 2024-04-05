package backend.service.enums;

import backend.service.interfaces.ICoordinate;

import java.util.Locale;

public enum Direction {

    WEST(Movement.WEST, "esquerda"),
    NORTH(Movement.NORTH, "cima"),
    EAST(Movement.EAST, "direita"),
    SOUTH(Movement.SOUTH, "baixo");

    private final Movement movement;
    private final String fileName;

    Direction(Movement movement, String fileName) {
        this.movement = movement;
        this.fileName = fileName;
    }

    public ICoordinate getMove() {
        return this.movement.getMove();
    }

    public String getFileName() {
        return this.fileName;
    }

    public static Direction getInstance(String direction) {
        var name = direction.toUpperCase(Locale.ROOT);
        try {
            return Enum.valueOf(Direction.class, name);
        } catch (Exception e) {
            return null;
        }
    }
}
