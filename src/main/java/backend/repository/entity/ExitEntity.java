package backend.repository.entity;

import backend.repository.interfaces.IExitEntity;

public record ExitEntity(
        int mapGameKey,
        String direction,
        String mapGame
) implements IExitEntity {

    @Override
    public String toString() {
        return """
                {
                    "mapGameKey": "%d",
                    "direction": %s,
                    "mapGame": %s
                }
                """.formatted(this.mapGameKey, this.direction, this.mapGame);
    }
}
