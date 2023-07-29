package backend.repository.entity;

import backend.repository.interfaces.IItemEntity;
import backend.service.enums.TypeItem;

import java.util.List;

public record ItemEntity(
        int id,
        int idMapGame,
        String name,
        String description,
        int weight,
        int positionX,
        int positionY,

        String imagemIcon,

        List<TypeItem> type,

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
                    "type": %s,
                    "visible": %b
                }
                """.formatted(this.id, this.idMapGame, this.name, this.description, this.weight, this.positionX,
                this.positionY, this.imagemIcon, this.type, this.visible);

    }
}
