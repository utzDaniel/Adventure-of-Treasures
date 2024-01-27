package backend.repository.entity;

import backend.repository.interfaces.IExitEntity;

public record ExitEntity(int id,
                        int idMapOri,
                        String direction,
                        int idMapExt) implements IExitEntity {

    @Override
    public String toString() {
        return """
                {
                    "id": "%d",
                    "idMapOri": "%d",
                    "direction": %s,
                    "idMapExt": %d
                }
                """.formatted(this.id, this.idMapOri, this.direction, this.idMapExt);
    }
}
