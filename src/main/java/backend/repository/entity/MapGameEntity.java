package backend.repository.entity;

import backend.repository.interfaces.IMapGameEntity;

import java.util.Arrays;
import java.util.Objects;

public record MapGameEntity(int id,
                            String name,
                            String image,
                            String song,
                            byte[][] limits) implements IMapGameEntity {
    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "name": "%s",
                    "image": "%s",
                    "song": "%s",
                    "limits": %s,
                }
                """.formatted(this.id, this.name,this.image, this.song, Arrays.toString(this.limits));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapGameEntity mapGameEntity = (MapGameEntity) o;
        return Arrays.deepEquals(this.limits, mapGameEntity.limits) &&
                Objects.equals(this.id, mapGameEntity.id) &&
                Objects.equals(this.name, mapGameEntity.name) &&
                Objects.equals(this.image, mapGameEntity.image) &&
                Objects.equals(this.song, mapGameEntity.song);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(this.id, this.name,this.image, this.song);
        result = 31 * result + Arrays.deepHashCode(this.limits);
        return result;
    }
}
