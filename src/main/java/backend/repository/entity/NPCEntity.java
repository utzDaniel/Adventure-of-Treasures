package backend.repository.entity;

import backend.repository.interfaces.INPCEntity;

public record NPCEntity(int id,
                        int idMap,
                        int idDoor,
                        int idItem) implements INPCEntity {

    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idMap": "%d",
                    "idDoor": "%d",
                    "idItem": %d
                }
                """.formatted(this.id, this.idMap, this.idDoor, this.idItem);
    }
}
