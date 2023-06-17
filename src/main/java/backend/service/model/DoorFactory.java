package backend.service.model;

import backend.service.interfaces.ICoordinate;
import backend.repository.interfaces.IDoorEntity;

public final class DoorFactory {

    private IDoorEntity doorEntity;

    public Door create(IDoorEntity doorEntity) {
        this.doorEntity = doorEntity;
        return run();
    }

    private Door run() {
        var mapGame = doorEntity.mapGame();
        var coordinate = ICoordinate.getInstance(doorEntity.positionX(), doorEntity.positionY());
        var open = doorEntity.open();
        return new Door(mapGame, coordinate, open);
    }
}
