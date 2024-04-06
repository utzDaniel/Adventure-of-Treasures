package backend.repository.entity;

import backend.repository.interfaces.IEventMapEntity;

public record EventMapEntity(int id,
                             int idItem,
                             int idMap,
                             int idDecoration) implements IEventMapEntity {
    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItem": "%d",
                    "idMap": "%d",
                    "idDecoration": "%d"
                }
                """.formatted(this.id, this.idItem, this.idMap, this.idDecoration);
    }
}
