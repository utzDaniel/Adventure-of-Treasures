package backend.service.interfaces;

import backend.controller.interfaces.ICoordinateDTO;
import backend.service.model.Coordinate;

public interface ICoordinate extends ICoordinateDTO {

    static ICoordinate getInstance(int x, int y) {
        return new Coordinate(x, y);
    }

    static ICoordinate getInstance(ICoordinate coordinate) {
        return getInstance(coordinate.x(), coordinate.y());
    }

    void setX(int x);

    void updateX(int x);

    void setY(int y);

    void updateY(int y);

    void move(ICoordinate coordinate);

}
