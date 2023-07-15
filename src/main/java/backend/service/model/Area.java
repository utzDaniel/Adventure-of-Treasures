package backend.service.model;

import backend.service.interfaces.ICoordinate;

public final class Area {

    public static final int STEP = 10;
    private static final int POSITION_MINIMUM = 10;
    private static final int POSITION_X_MAXIMUM = 56;
    private static final int POSITION_Y_MAXIMUM = 78;
    public static final int CODE_PASS = 49;
    public static final int CODE_ITEM = 50;
    public static final int POSITION_X_MINIMUM = 0;
    public static final int POSITION_Y_MINIMUM = 0;

    private final byte[][] limits;

    public static boolean isLimit(ICoordinate coordinate) {
        return !(Math.min(x(coordinate), y(coordinate)) >= POSITION_MINIMUM &&
                x(coordinate) <= POSITION_X_MAXIMUM && y(coordinate) <= POSITION_Y_MAXIMUM);
    }

    public static int getTotalArea() {
        return POSITION_X_MAXIMUM * POSITION_Y_MAXIMUM;
    }

    public static int limitY() {
        return (Area.POSITION_X_MAXIMUM - 1) * Area.STEP;
    }

    public static int minY() {
        return POSITION_Y_MINIMUM;
    }

    public static int limitX() {
        return (Area.POSITION_Y_MAXIMUM - 1) * Area.STEP;
    }

    public static int minX() {
        return POSITION_X_MINIMUM;
    }

    public Area(byte[][] limits) {
        this.limits = limits;
    }

    public boolean isBlock(ICoordinate coordinate) {
        return this.limits[y(coordinate)][x(coordinate)] == CODE_PASS;
    }

    public void unlock(ICoordinate coordinate) {
        this.limits[y(coordinate)][x(coordinate)] = CODE_PASS;
    }

    public void block(ICoordinate coordinate) {
        this.limits[y(coordinate)][x(coordinate)] = CODE_ITEM;
    }

    private static int x(ICoordinate coordinate) {
        return coordinate.x() / Area.STEP;
    }

    private static int y(ICoordinate coordinate) {
        return coordinate.y() / Area.STEP;
    }

    @Override
    public String toString() {
        return """
                {
                    "limits": "%s"
                }
                """.formatted(this.limits());
    }

    private String limits() {
        StringBuilder str = new StringBuilder();
        for (byte[] limit : this.limits) {
            for (byte i : limit) {
                str.append(i);
            }
        }
        return str.toString();
    }
}
