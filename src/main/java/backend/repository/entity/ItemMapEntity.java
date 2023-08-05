package backend.repository.entity;

import backend.repository.interfaces.IItemMapEntity;

public record ItemMapEntity(int id,
                            int idItem,
                            int idMap) implements IItemMapEntity {
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
