package backend.repository.entity;

import backend.repository.interfaces.IMissionEntity;

public record MissionEntity(int id,
                            int idItem) implements IMissionEntity {
    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItem": "%d"
                }
                """.formatted(this.id, this.idItem);
    }
}