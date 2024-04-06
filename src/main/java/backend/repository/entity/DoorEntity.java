package backend.repository.entity;

import backend.repository.interfaces.IDoorEntity;

public record DoorEntity(int id,
                         int idMapInside,
                         int insideX,
                         int insideY,
                         int idMapOutside,
                         int outsideX,
                         int outsideY,
                         boolean open) implements IDoorEntity {

    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idMapInside": "%d",
                    "insideX": %d,
                    "insideY": %d,
                    "idMapOutside": "%d",
                    "outsideX": %d,
                    "outsideY": %d,
                    "open": %b
                }
                """.formatted(this.id, this.idMapInside, this.insideX, this.insideY,
                this.idMapOutside, this.outsideX, this.outsideY, this.open);
    }
}
