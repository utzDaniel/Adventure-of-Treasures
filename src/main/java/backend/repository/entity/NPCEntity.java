package backend.repository.entity;

import backend.repository.interfaces.INPCEntity;

public record NPCEntity(int id,
                        int idMap,
                        int positionX,
                        int positionY,
                        int idDoor,
                        int idItem) implements INPCEntity {

    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idMap": "%d",
                    "positionX": "%d",
                    "positionY": "%d",
                    "idDoor": "%d",
                    "idItem": %d
                }
                """.formatted(this.id, this.idMap, this.positionX, this.positionY, this.idDoor, this.idItem);
    }
}
