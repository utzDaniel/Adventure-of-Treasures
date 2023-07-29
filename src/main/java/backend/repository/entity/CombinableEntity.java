package backend.repository.entity;

import backend.repository.interfaces.ICombinableEntity;

public record CombinableEntity(int id,
                               int idItem,
                               int idItemCombine) implements ICombinableEntity {

    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItem": "%d",
                    "idItemCombine": "%d"
                }
                """.formatted(this.id, this.idItem, this.idItemCombine);
    }
}