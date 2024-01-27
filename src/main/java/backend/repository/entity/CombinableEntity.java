package backend.repository.entity;

import backend.repository.interfaces.ICombinableEntity;

public record CombinableEntity(int id,
                               int idItem,
                               int combination) implements ICombinableEntity {

    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItem": "%d",
                    "combination": "%d"
                }
                """.formatted(this.id, this.idItem, this.combination);
    }
}