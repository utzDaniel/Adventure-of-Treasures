package backend.repository.entity;

import backend.repository.interfaces.IMapGameEntity;

import java.util.Arrays;
import java.util.Objects;

public record MapGameEntity(String name,
                            String imagemIcon,
                            int exitsKey,
                            int doorsKey,
                            int itensKey,
                            String song,
                            int[][] limits) implements IMapGameEntity {
    @Override
    public String toString() {
        return """
                {
                    "name": "%s",
                    "icon": "%s",
                    "exitsKey": %d,
                    "doorsKey": %d,
                    "itensKey": %s,
                    "song": "%s",
                    "limits": %s,
                }
                """.formatted(this.name, this.imagemIcon, this.exitsKey, this.doorsKey, this.itensKey, this.song,
                Arrays.toString(this.limits));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapGameEntity mapGameEntity = (MapGameEntity) o;
        return Arrays.deepEquals(this.limits, mapGameEntity.limits) &&
                Objects.equals(this.name, mapGameEntity.name) &&
                Objects.equals(this.imagemIcon, mapGameEntity.imagemIcon) &&
                Objects.equals(this.exitsKey, mapGameEntity.exitsKey) &&
                Objects.equals(this.doorsKey, mapGameEntity.doorsKey) &&
                Objects.equals(this.itensKey, mapGameEntity.itensKey) &&
                Objects.equals(this.song, mapGameEntity.song);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(this.name, this.imagemIcon, this.exitsKey, this.doorsKey, this.itensKey, this.song);
        result = 31 * result + Arrays.deepHashCode(this.limits);
        return result;
    }
}
