package frontend.model.vo;

import backend.controller.interfaces.ICoordinate;

import java.awt.*;
import java.util.Objects;

public final class CoordinateVO implements ICoordinate {
    private int x;
    private int y;

    public CoordinateVO() {}
    public CoordinateVO(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CoordinateVO(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void updateX(int x) {
        this.x += x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void updateY(int y) {
        this.y += y;
    }

    @Override
    public void move(ICoordinate coordinate) {
        this.x += coordinate.getX();
        this.y += coordinate.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinateVO that = (CoordinateVO) o;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                ", x =" + this.x +
                ", y =" + this.y +
                '}';
    }
}
