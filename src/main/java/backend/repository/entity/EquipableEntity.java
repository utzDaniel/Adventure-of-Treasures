package backend.repository.entity;

import backend.repository.interfaces.IEquipableEntity;

public record EquipableEntity(int id,
                              int idItem,
                              int upMaxCapacity) implements IEquipableEntity {
    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItem": "%d",
                    "upMaxCapacity": "%d"
                }
                """.formatted(this.id, this.idItem, this.upMaxCapacity);
    }
}