package backend.service.enums;

import backend.service.interfaces.ICoordinate;

import java.util.Locale;

public enum Direction {

    OESTE(Movement.OESTE, "esquerda"),
    NORTE(Movement.NORTE, "cima"),
    LESTE(Movement.LESTE, "direita"),
    SUL(Movement.SUL, "baixo");

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
