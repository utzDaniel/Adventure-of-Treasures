package model;

import java.util.Objects;

public class Coordinate {

    private final int STEP = MovePlayer.STEP;
    private int axisX;
    private int axisY;

    public Coordinate(int eixoX, int eixoY) {
        setAxisX(eixoX);
        setAxisY(eixoY);
    }

    public int getAxisX() {
        return axisX;
    }

    public int getAxisY() {
        return axisY;
    }

    public void setAxisX(int axisX) {
        this.axisX = axisX / STEP;
    }

    public void setAxisY(int axisY) {
        this.axisY = axisY / STEP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return axisX == that.axisX && axisY == that.axisY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(axisX, axisY);
    }
}
