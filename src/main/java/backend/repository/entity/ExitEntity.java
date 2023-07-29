package backend.repository.entity;

import backend.repository.interfaces.IExitEntity;

public record ExitEntity(
        int id,
        int idMapGame,
        String direction,
        int idMapGameExit) implements IExitEntity {

    @Override
    public String toString() {
        return """
                {
                    "id": "%d",
                    "idMapGame": "%d",
                    "direction": %s,
                    "idMapGameExit": %d
                }
                """.formatted(this.id, this.idMapGame, this.direction, this.idMapGameExit);
    }
}
