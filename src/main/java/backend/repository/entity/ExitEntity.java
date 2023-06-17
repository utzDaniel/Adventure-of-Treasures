package backend.repository.entity;

import backend.repository.interfaces.IExitEntity;

public record ExitEntity(
        int id,
        int idMapGame,
        String direction,
        String mapGame
) implements IExitEntity {

    @Override
    public String toString() {
        return """
                {
                    "id": "%d",
                    "idMapGame": "%d",
                    "direction": %s,
                    "mapGame": %s
                }
                """.formatted(this.id, this.idMapGame,this.direction, this.mapGame);
    }
}
