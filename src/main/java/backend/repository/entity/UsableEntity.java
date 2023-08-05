package backend.repository.entity;

import backend.repository.interfaces.IUsableEntity;

public record UsableEntity(int id,
                           int idItem,
                           int idMap) implements IUsableEntity {
    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItem": "%d",
                    "idMap": "%d"
                }
                """.formatted(this.id, this.idItem, this.idMap);
    }
}
