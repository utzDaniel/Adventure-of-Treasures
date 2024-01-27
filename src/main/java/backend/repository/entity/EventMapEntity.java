package backend.repository.entity;

import backend.repository.interfaces.IEventMapEntity;

public record EventMapEntity(int id,
                             int idItem,
                             int idMap,
                             String image) implements IEventMapEntity {
    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItem": "%d",
                    "idMap": "%d",
                    "image": "%s"
                }
                """.formatted(this.id, this.idItem, this.idMap, this.image);
    }
}
