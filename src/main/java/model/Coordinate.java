package model;

import model.enums.MovePlayer;

import java.awt.*;
import java.util.Objects;

public class Coordinate {

    private final int STEP = MovePlayer.STEP;
    private int x;
    private int y;
    private boolean blocked;

    public Coordinate(int x, int y) {
        setX(x);
        setY(y);
        this.blocked = false;
    }

    public Coordinate(Point point) {
        setX(point.x);
        setY(point.y);
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x / this.STEP;
    }

    public int getY() {
        return this.y;
    }


    public void setY(int y) {
        this.y = y / this.STEP;
    }

    public boolean isBlocked() {
        return this.blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "STEP=" + STEP +
                ", axisX=" + x +
                ", axisY=" + y +
                '}';
    }
}
