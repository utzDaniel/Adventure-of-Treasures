package backend.repository.entity;

import backend.repository.interfaces.IItemEntity;

public record ItemEntity(
        int id,
        int idMapGame,
        String name,
        String description,
        int weight,
        int positionX,
        int positionY,

        String imagemIcon,

        int combine,

        boolean equipped,

        String mapGame,

        String localUse,

        boolean remove,

        boolean visible) implements IItemEntity {

    @Override
    public String toString() {
        return """
                {
                    "id": "%d",
                    "idMapGame": "%d",
                    "name": "%s",
                    "description": "%s",
                    "weight": %d,
                    "positionX": %d,
                    "positionY": %d,
                    "imagemIcon": "%s",
                    "combine": %d,
                    "equipped": %b,
                    "mapGame": "%s",
                    "localUse": "%s",
                    "remove": %b,
                    "visible": %b
                }
                """.formatted(this.id, this.idMapGame, this.name, this.description, this.weight, this.positionX, this.positionY,
                this.imagemIcon, this.combine, this.equipped, this.mapGame, this.localUse, this.remove, this.visible);

    }
}
