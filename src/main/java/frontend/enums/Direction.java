package frontend.enums;

import java.util.Arrays;

public enum Direction {

    NORTH("NORTH", 38), SOUTH("SOUTH", 40),
    WEST("WEST", 37), EAST("EAST", 39);

    private final String label;

    private final int keyCode;

    public String getLabel() {
        return label;
    }

    Direction(String label, int keyCode) {
        this.label = label;
        this.keyCode = keyCode;
    }

    public static boolean containsKeyCode(int keyCode) {
        return Arrays.stream(Direction.values())
                .anyMatch(direction -> direction.isKeyCode(keyCode));
    }

    public static String getLabel(int keyCode) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.isKeyCode(keyCode))
                .findFirst().map(Direction::getLabel)
                .orElse(null);
    }

    public boolean isKeyCode(int keyCode) {
        return this.keyCode == keyCode;
    }
}
