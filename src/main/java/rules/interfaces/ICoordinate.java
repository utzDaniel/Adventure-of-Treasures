package rules.interfaces;

import backend.model.Coordinate;

import java.awt.*;

public interface ICoordinate {

    static ICoordinate getInstance(int x, int y) {
        return new Coordinate(x, y);
    }

    static ICoordinate getInstance(Point point) {
        return new Coordinate(point);
    }

    Point getPoint();
    int getX();
    void setX(int x);
    void updateX(int x);
    int getY();
    void setY(int y);
    void updateY(int y);
    void move(ICoordinate coordinate);

}
