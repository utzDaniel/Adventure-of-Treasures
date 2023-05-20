package rules.interfaces;

import backend.model.Coordinate;

public interface ICoordinate {

    static ICoordinate getInstance(int x, int y) {
        return new Coordinate(x, y);
    }

    static ICoordinate getInstance(ICoordinate coordinate) {
        return getInstance(coordinate.getX(), coordinate.getY());
    }

    int getX();

    void setX(int x);

    void updateX(int x);

    int getY();

    void setY(int y);

    void updateY(int y);

    void move(ICoordinate coordinate);

}
