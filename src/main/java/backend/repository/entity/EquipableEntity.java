package backend.repository.entity;

import backend.repository.interfaces.IEquipableEntity;

public record EquipableEntity(int id,
                              int idItem,
                              int upCapacity) implements IEquipableEntity {
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