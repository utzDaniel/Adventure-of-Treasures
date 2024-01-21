package backend.service.model;

import backend.service.interfaces.ICoordinate;

public final class Area {
    private static final int STEP = 1;
    private static final int POSITION_MINIMUM = 0;
    private static final int POSITION_X_MAXIMUM = 56;
    private static final int POSITION_Y_MAXIMUM = 78;
    public static final int CODE_PASS = 49;
    public static final int CODE_ITEM = 50;

    private final byte[][] limits;

    public static boolean isLimit(ICoordinate coordinate) {
        return !(Math.min(coordinate.x(), coordinate.y()) >= POSITION_MINIMUM &&
                coordinate.x() < POSITION_X_MAXIMUM && coordinate.y() < POSITION_Y_MAXIMUM);
    }

    public static int getTotalArea() {
        return POSITION_X_MAXIMUM * POSITION_Y_MAXIMUM;
    }

    public static int maxY() {
        return Area.POSITION_Y_MAXIMUM - STEP;
    }

    public static int minY() {
        return Area.POSITION_MINIMUM;
    }

    public static int maxX() {
        return Area.POSITION_X_MAXIMUM - STEP;
    }

    public static int minX() {
        return Area.POSITION_MINIMUM;
    }

    public Area(byte[][] limits) {
        this.limits = limits;
    }

    public boolean isBlock(ICoordinate coordinate) {
        return this.limits[coordinate.x()][coordinate.y()] == CODE_PASS;
    }

    public void unlock(ICoordinate coordinate) {
        this.limits[coordinate.x()][coordinate.y()] = CODE_PASS;
    }

    public void block(ICoordinate coordinate) {
        this.limits[coordinate.x()][coordinate.y()] = CODE_ITEM;
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
