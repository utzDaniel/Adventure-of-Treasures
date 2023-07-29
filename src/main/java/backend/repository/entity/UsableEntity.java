package backend.repository.entity;

import backend.repository.interfaces.IUsableEntity;

public record UsableEntity(int id,
                           int idItem,
                           int idMapGame) implements IUsableEntity {
    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItem": "%d",
                    "idMapGame": "%d"
                }
                """.formatted(this.id, this.idItem, this.idMapGame);
    }
}
