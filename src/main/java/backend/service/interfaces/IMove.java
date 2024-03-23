package backend.service.interfaces;

import backend.service.enums.Direction;
import backend.service.model.MapGame;

public interface IMove extends IImage, IFlyweight {
    void updateMove(Direction direction, ICoordinate coordinate);

    MapGame getCurrentMap();

    void setCurrentMap(MapGame currentScenery);

    String getImage();

    ICoordinate getCoordinate();

    Direction getDirection();
}
