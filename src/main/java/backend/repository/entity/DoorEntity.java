package backend.repository.entity;

import backend.repository.interfaces.IDoorEntity;

public record DoorEntity(
        int id,
        int idMapGame,
        String mapGame,
        int positionX,
        int positionY,
        boolean open
) implements IDoorEntity {

    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idMapGame": "%d",
                    "mapGame": "%s",
                    "positionX": %d,
                    "positionY": %d,
                    "open": %b
                }
                """.formatted(this.id,this.idMapGame, this.mapGame, this.positionX, this.positionY, this.open);
    }
}
