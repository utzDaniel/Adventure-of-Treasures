package model;

import model.enums.MovePlayer;

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
        return this.axisX;
    }

    public int getAxisY() {
        return this.axisY;
    }

    public void setAxisX(int axisX) {
        this.axisX = axisX / this.STEP;
    }

    public void setAxisY(int axisY) {
        this.axisY = axisY / this.STEP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return this.axisX == that.axisX && this.axisY == that.axisY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.axisX, this.axisY);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "STEP=" + STEP +
                ", axisX=" + axisX +
                ", axisY=" + axisY +
                '}';
    }
}
