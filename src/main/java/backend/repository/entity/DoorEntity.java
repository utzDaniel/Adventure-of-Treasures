package backend.repository.entity;

import backend.repository.interfaces.IDoorEntity;

public record DoorEntity(int id,
                        int idMapOri,
                        int idMapDor,
                        int positionX,
                        int positionY,
                        boolean open) implements IDoorEntity {

    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idMapOri": "%d",
                    "idMapDor": "%d",
                    "positionX": %d,
                    "positionY": %d,
                    "open": %b
                }
                """.formatted(this.id,this.idMapOri, this.idMapDor, this.positionX, this.positionY, this.open);
    }
}
