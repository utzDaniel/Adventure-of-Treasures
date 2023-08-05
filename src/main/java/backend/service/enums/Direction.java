package backend.service.enums;

import java.util.Locale;

public enum Direction {
    OESTE, NORTE, LESTE, SUL;

    public static Direction getInstance(String direction) {
        var name = direction.toUpperCase(Locale.ROOT);
        try {
            return Enum.valueOf(Direction.class, name);
        } catch (Exception e) {
            return null;
        }
    }
}
