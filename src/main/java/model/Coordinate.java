package model;

import java.util.Objects;

public class Coordinate {

    private final int STEP = MovePlayer.STEP;
    private int eixoX;
    private int eixoY;

    public Coordinate(int eixoX, int eixoY) {
        setEixoX(eixoX);
        setEixoY(eixoY);
    }

    public int getEixoX() {
        return eixoX;
    }

    public int getEixoY() {
        return eixoY;
    }

    public void setEixoX(int eixoX) {
        this.eixoX = eixoX / STEP;
    }

    public void setEixoY(int eixoY) {
        this.eixoY = eixoY / STEP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return eixoX == that.eixoX && eixoY == that.eixoY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eixoX, eixoY);
    }
}
