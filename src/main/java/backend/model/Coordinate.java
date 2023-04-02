package backend.model;

import rules.interfaces.ICoordinate;

import java.awt.*;
import java.util.Objects;

public class Coordinate implements ICoordinate {

    private final Point point;

    public Coordinate(int x, int y) {
        this.point = new Point(x, y);
    }

    public Coordinate(Point point) {
        this.point = new Point(point);
    }

    @Override
    public Point getPoint() {
        return this.point;
    }

    @Override
    public int getX() {
        return this.point.x;
    }

    @Override
    public void setX(int x) {
        this.point.move(x, this.point.y);
    }

    @Override
    public void updateX(int x) {
        this.point.translate(x, 0);
    }

    @Override
    public int getY() {
        return this.point.y;
    }

    @Override
    public void setY(int y) {
        this.point.move(this.point.x, y);
    }

    @Override
    public void updateY(int y) {
        this.point.translate(0, y);
    }

    @Override
    public void move(ICoordinate coordinate) {
        this.point.translate(coordinate.getX(), coordinate.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return this.point.x == that.point.x && this.point.y == that.point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.point.x, this.point.y);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                ", x =" + this.point.x +
                ", y =" + this.point.y +
                '}';
    }
}
