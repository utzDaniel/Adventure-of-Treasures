package backend.repository.entity;

import backend.repository.interfaces.IDecorationEntity;

public record DecorationEntity(int id,
                               int pointX,
                               int pointY,
                               String image) implements IDecorationEntity {
    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "pointX": "%d",
                    "pointY": "%d",
                    "image": "%s"
                }
                """.formatted(this.id, this.pointX, this.pointY, this.image);
    }
}
