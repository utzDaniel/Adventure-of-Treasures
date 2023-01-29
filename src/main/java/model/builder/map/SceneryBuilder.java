package model.builder.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SceneryBuilder extends MapGameBuilder {

    private final Scenery scenery;
    private final Map<String, String> exits;

    public SceneryBuilder() {
        this.scenery = new Scenery();
        this.exits = new HashMap<>();
        super.mapGame = this.scenery;
        super.doors = new HashMap<>();
        super.itens = new HashMap<>();
    }

    public static SceneryBuilder builder() {
        return new SceneryBuilder();
    }

    public SceneryBuilder exits(String direction, String scenery) {
        if(Objects.nonNull(scenery))
            this.exits.put(direction,scenery);
        return this;
    }

    @Override
    public Scenery build() {
        this.scenery.setExits(exits);
        super.build();
        return this.scenery;
    }
}
