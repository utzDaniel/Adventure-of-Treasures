package backend.repository.entity;

import backend.repository.interfaces.IEventItemEntity;

public record EventItemEntity(int id,
                              int idItem,
                              int idEnabled) implements IEventItemEntity {
    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItem": "%d",
                    "idEnabled": "%d"
                }
                """.formatted(this.id, this.idItem, this.idEnabled);
    }
}
