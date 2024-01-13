package backend.repository.entity;

import backend.repository.interfaces.IEventEntity;

public record EventEntity(int id,
                          int idItem,
                          int idMap,
                          int idDoor) implements IEventEntity {
    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItem": "%d",
                    "idMap": "%d",
                    "idDoor": "%d"
                }
                """.formatted(this.id, this.idItem, this.idMap, this.idDoor);
    }
}
