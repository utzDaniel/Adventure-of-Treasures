package backend.enums;

import backend.exception.MoveException;

import java.util.Arrays;

public enum Direction {
    NORTE("norte", 38), SUL("sul", 40),
    OESTE("oeste", 37), LESTE("leste", 39);

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
                .orElseThrow(() -> new MoveException("Direção invalida!"));
    }

    public boolean isKeyCode(int keyCode) {
        return this.keyCode == keyCode;
    }
}
