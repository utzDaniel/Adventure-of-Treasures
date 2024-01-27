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
    public byte[][] limits() {
        return copyLimits(this.limits);
    }

    private static byte[][] copyLimits(byte[][] original) {
        if (Objects.isNull(original)) return new byte[0][0];

        int rows = original.length;
        int cols = original[0].length;

        byte[][] copy = new byte[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, cols);
        }

        return copy;
    }

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
                """.formatted(this.id, this.name, this.image, this.song, byteArrayToString());
    }

    private String byteArrayToString() {
        var sb = new StringBuilder();

        for (byte[] b1 : this.limits) {
            for (byte b : b1) {
                sb.append((char) b);
            }
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var entity = (IMapGameEntity) o;
        return Arrays.deepEquals(this.limits, entity.limits()) &&
                Objects.equals(this.id, entity.id()) &&
                Objects.equals(this.name, entity.name()) &&
                Objects.equals(this.image, entity.image()) &&
                Objects.equals(this.song, entity.song());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(this.id, this.name, this.image, this.song);
        result = 31 * result + Arrays.deepHashCode(this.limits);
        return result;
    }
}
