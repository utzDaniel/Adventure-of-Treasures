package backend.repository.entity;

import backend.repository.interfaces.ICombinableEntity;

public record CombinableEntity(int id,
                               int idItemNew,
                               int idItemOri) implements ICombinableEntity {

    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "idItemNew": "%d",
                    "idItemOri": "%d"
                }
                """.formatted(this.id, this.idItemNew, this.idItemOri);
    }
}