package model;

import java.awt.*;
import java.util.Objects;

public class Coordinate {

    private Point point;

    public Coordinate(int x, int y) {
        this.point = new Point(x, y);
    }

    public Coordinate(Point point) {
        this.point = new Point(point);
    }

    public int getX() {
        return this.point.x;
    }

    public Point getPoint() {
        return this.point;
    }

    public void setX(int x) {
        this.point.move(x, this.point.y);
    }

    public void updateX(int x) {
        this.point.translate(x, 0);
    }

    public int getY() {
        return this.point.y;
    }

    public void setY(int y) {
        this.point.move(this.point.x,y);
    }

    public void updateY(int y) {
        this.point.translate(0,y);
    }

    public void move(Coordinate coordinate) {
        this.point.translate(coordinate.getX(), coordinate.getY());
    }

    public void setLocation(Coordinate coordinate) {
        this.point.move(coordinate.getX(), coordinate.getY());
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
