package backend.repository.entity;

import backend.repository.interfaces.IEventMapEntity;

public record EventMapEntity(int id,
                             int idItem,
                             int idMap,
                             String mapImage,
                             int idDoor) implements IEventMapEntity {
    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItem": "%d",
                    "idMap": "%d",
                    "mapImage": "%s",
                    "idDoor": "%d"
                }
                """.formatted(this.id, this.idItem, this.idMap, this.mapImage, this.idDoor);
    }
}
