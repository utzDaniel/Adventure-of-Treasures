package backend.repository.entity;

import backend.repository.interfaces.IEquippableEntity;

public record EquippableEntity(int id,
                               int idItem,
                               int upCapacity) implements IEquippableEntity {
    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItem": "%d",
                    "upCapacity": "%d"
                }
                """.formatted(this.id, this.idItem, this.upCapacity);
    }
}