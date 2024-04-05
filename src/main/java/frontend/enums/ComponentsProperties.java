package frontend.enums;

import java.awt.*;

public enum ComponentsProperties {
    ITEM(new Dimension(100, 100)),
    PLAYER(new Dimension(32, 47)),
    MAP(new Dimension(800, 600));

    private final Dimension dimension;

    ComponentsProperties(Dimension dimension) {
        this.dimension = dimension;
    }

    public Dimension getDimension() {
        return this.dimension;
    }
}
