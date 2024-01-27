package backend.repository.entity;

import backend.repository.interfaces.IItemEntity;

public record ItemEntity(int id,
                        String name,
                        String description,
                        int weight,
                        int positionX,
                        int positionY,
                        String image) implements IItemEntity {

    @Override
    public String toString() {
        return """
                {
                    "id": "%d",
                    "name": "%s",
                    "description": "%s",
                    "weight": %d,
                    "positionX": %d,
                    "positionY": %d,
                    "image": "%s"
                }
                """.formatted(this.id, this.name, this.description, this.weight, this.positionX,
                this.positionY, this.image);

    }
}
