package model;

public class Area {

    private final int initialAxisX;
    private final int finalAxisX;
    private final int initialAxisY;
    private final int finalAxisY;

    public Area(int initialAxisX, int finalAxisX, int initialAxisY, int finalAxisY) {
        this.initialAxisX = initialAxisX;
        this.finalAxisX = finalAxisX;
        this.initialAxisY = initialAxisY;
        this.finalAxisY = finalAxisY;
    }

    public int getInitialAxisX() {
        return this.initialAxisX;
    }

    public int getFinalAxisX() {
        return this.finalAxisX;
    }

    public int getInitialAxisY() {
        return this.initialAxisY;
    }

    public int getFinalAxisY() {
        return this.finalAxisY;
    }
}
