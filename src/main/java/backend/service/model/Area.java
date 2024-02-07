package backend.service.model;

import backend.service.interfaces.ICoordinate;

import java.util.HashSet;
import java.util.Set;

public final class Area {
    private static final int POSITION_MINIMUM = 0;
    public static final int CODE_BLOCK = 48;
    public static final int CODE_PASS = 49;
    private final byte[][] limits;
    private final Set<ICoordinate> blocks;

    public Area(byte[][] limits) {
        this.limits = limits;
        this.blocks = new HashSet<>();
    }

    public boolean blocked(ICoordinate coordinate) {
        return this.limits[coordinate.x()][coordinate.y()] != CODE_PASS || this.blocks.contains(coordinate);
    }

    public boolean outside(ICoordinate coordinate) {
        return !(Math.min(coordinate.x(), coordinate.y()) >= POSITION_MINIMUM &&
                coordinate.x() < this.limits.length && coordinate.y() < this.limits[0].length);
    }

    public ICoordinate next(ICoordinate coordinate) {
        var x = nextX(coordinate.x());
        var y = nextY(coordinate.y());
        return ICoordinate.getInstance(x, y);
    }

    private int nextY(int value) {
        if (value <= POSITION_MINIMUM) return this.limits[0].length - 1;
        if (value >= this.limits[0].length) return POSITION_MINIMUM;
        return value;
    }

    private int nextX(int value) {
        if (value <= POSITION_MINIMUM) return this.limits.length - 1;
        if (value >= this.limits.length) return POSITION_MINIMUM;
        return value;
    }

    public void unlock(ICoordinate coordinate) {
        this.blocks.remove(coordinate);
    }

    public void block(ICoordinate coordinate) {
        this.blocks.add(coordinate);
    }

    @Override
    public String toString() {
        return """
                {
                    "limits": "%s"
                }
                """.formatted(this.limits());
    }

    private String limits() {
        StringBuilder str = new StringBuilder();
        for (byte[] limit : this.limits) {
            for (byte i : limit) {
                str.append(i);
            }
        }
        return str.toString();
    }
}
