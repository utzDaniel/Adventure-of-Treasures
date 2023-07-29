package backend.repository.entity;

import backend.repository.interfaces.IDoorEntity;

public record DoorEntity(
        int id,
        int idMapGame,
        int idMapGameExit,
        int positionX,
        int positionY,
        boolean open) implements IDoorEntity {

    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idMapGame": "%d",
                    "idMapGameExit": "%d",
                    "positionX": %d,
                    "positionY": %d,
                    "open": %b
                }
                """.formatted(this.id,this.idMapGame, this.idMapGameExit, this.positionX, this.positionY, this.open);
    }
}
