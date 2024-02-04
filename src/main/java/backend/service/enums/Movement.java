package backend.service.enums;

import backend.service.interfaces.ICoordinate;

public enum Movement {
    OESTE(ICoordinate.getInstance(0, -Movement.STEP)),
    NORTE(ICoordinate.getInstance(-Movement.STEP, 0)),
    LESTE(ICoordinate.getInstance(0, Movement.STEP)),
    SUL(ICoordinate.getInstance(Movement.STEP, 0));

    public static final int STEP = 1;
    private final ICoordinate move;

    Movement(ICoordinate move) {
        this.move = move;
    }

    public ICoordinate getMove() {
        return this.move;
    }
}
