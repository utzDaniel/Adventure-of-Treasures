package model;

import model.enums.MovePlayer;

public class Area {

    public static final int STEP = MovePlayer.STEP;
    private static final int POSITION_MINIMUM = 10;
    private static final int POSITION_X_MAXIMUM = 56;
    private static final int POSITION_Y_MAXIMUM = 78;

    private final int[][] limits;

    public static boolean isLimit(Coordinate coordinate) {
        return !(Math.min(x(coordinate), y(coordinate)) >= POSITION_MINIMUM &&
                x(coordinate) <= POSITION_X_MAXIMUM && y(coordinate) <= POSITION_Y_MAXIMUM);
    }

    public static int getTotalArea() {
        return POSITION_X_MAXIMUM * POSITION_Y_MAXIMUM;
    }

    public Area(int[][] limits) {
        this.limits = limits;
    }

    public boolean isBlock(Coordinate coordinate) {
        return this.limits[y(coordinate)][x(coordinate)] == 1;
    }

    public void unlock(Coordinate coordinate) {
        this.limits[y(coordinate)][x(coordinate)] = 1;
    }

    public void block(Coordinate coordinate) {
        this.limits[y(coordinate)][x(coordinate)] = 2;
    }

    private static int x(Coordinate coordinate) {
        return coordinate.getX() / Area.STEP;
    }

    private static int y(Coordinate coordinate) {
        return coordinate.getY() / Area.STEP;
    }

    @Override
    public String toString() {
        return "Area{" +
                "limits=" + limits() +
                '}';
    }

    private String limits() {
        StringBuilder str = new StringBuilder();
        for (int[] limit : limits) {
            for (int i : limit) {
                str.append(i);
            }
        }
        return str.toString();
    }
}
