package backend.repository.entity;

import backend.repository.interfaces.IDoorEntity;

public record DoorEntity(
        int mapGameKey,
        String mapGame,
        int positionX,
        int positionY,
        boolean open
) implements IDoorEntity {

    @Override
    public String toString() {
        return """
                {
                    "mapGameKey": "%d",
                    "mapGame": "%s",
                    "positionX": %d,
                    "positionY": %d,
                    "open": %b
                }
                """.formatted(this.mapGameKey, this.mapGame, this.positionX, this.positionY, this.open);
    }
}
