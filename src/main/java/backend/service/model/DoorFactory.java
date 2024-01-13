package backend.service.model;

import backend.repository.interfaces.IDoorEntity;
import backend.service.interfaces.ICoordinate;

public final class DoorFactory {

    private DoorFactory(){}
    public static Door create(IDoorEntity doorEntity) {
        var id = doorEntity.id();
        var idMap = doorEntity.idMapDor();
        var coordinate = ICoordinate.getInstance(doorEntity.positionX(), doorEntity.positionY());
        var open = doorEntity.open();
        return new Door(id, idMap, coordinate, open);
    }

}
