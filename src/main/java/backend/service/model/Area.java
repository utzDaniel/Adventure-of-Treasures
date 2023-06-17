package backend.service.model;

import backend.service.interfaces.ICoordinate;

public final class Area {

    public static final int STEP = 10;
    private static final int POSITION_MINIMUM = 10;
    private static final int POSITION_X_MAXIMUM = 56;
    private static final int POSITION_Y_MAXIMUM = 78;

    private final int[][] limits;

    public static boolean isLimit(ICoordinate coordinate) {
        return !(Math.min(x(coordinate), y(coordinate)) >= POSITION_MINIMUM &&
                x(coordinate) <= POSITION_X_MAXIMUM && y(coordinate) <= POSITION_Y_MAXIMUM);
    }

    public static int getTotalArea() {
        return POSITION_X_MAXIMUM * POSITION_Y_MAXIMUM;
    }

    public Area(int[][] limits) {
        this.limits = limits;
    }

    public boolean isBlock(ICoordinate coordinate) {
        return this.limits[y(coordinate)][x(coordinate)] == 1;
    }

    public void unlock(ICoordinate coordinate) {
        this.limits[y(coordinate)][x(coordinate)] = 1;
    }

    public void block(ICoordinate coordinate) {
        this.limits[y(coordinate)][x(coordinate)] = 2;
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
        for (int[] limit : this.limits) {
            for (int i : limit) {
                str.append(i);
            }
        }
        return str.toString();
    }
}
