package backend.repository.entity;

import backend.repository.interfaces.IEventInventoryEntity;

public record EventInventoryEntity(int id,
                                   int idItem,
                                   int idItemNew) implements IEventInventoryEntity {
    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItem": "%d",
                    "idItemNew": "%d"
                }
                """.formatted(this.id, this.idItem, this.idItemNew);
    }
}
