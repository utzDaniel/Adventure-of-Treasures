package backend.service.enums;

import backend.service.interfaces.ICoordinate;

public enum Movement {
    WEST(ICoordinate.getInstance(0, -Movement.STEP)),
    NORTH(ICoordinate.getInstance(-Movement.STEP, 0)),
    EAST(ICoordinate.getInstance(0, Movement.STEP)),
    SOUTH(ICoordinate.getInstance(Movement.STEP, 0));

    public static final int STEP = 1;
    private final ICoordinate move;

    Movement(ICoordinate move) {
        this.move = move;
    }

    public ICoordinate getMove() {
        return this.move;
    }
}
