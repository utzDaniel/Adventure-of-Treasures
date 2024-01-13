package backend.repository.entity;

import backend.repository.interfaces.IUsableEntity;

public record UsableEntity(int id,
                           int idItem,
                           int idMap,
                           int positionX,
                           int positionY,
                           boolean enabled) implements IUsableEntity {
    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItem": "%d",
                    "idMap": "%d",
                    "positionX": "%d",
                    "positionY": "%d",
                    "enabled": "%b"
                }
                """.formatted(this.id, this.idItem, this.idMap, this.positionX, this.positionY, this.enabled);
    }
}
