package backend.repository.entity;

import backend.repository.interfaces.IEventDoorEntity;

public record EventDoorEntity(int id,
                              int idItem,
                              int idDoor,
                              boolean open) implements IEventDoorEntity {
    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItem": "%d",
                    "idDoor": "%d",
                    "open": "%b"
                }
                """.formatted(this.id, this.idItem, this.idDoor, this.open);
    }
}
