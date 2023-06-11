package backend.service.model.builder;

import backend.service.model.Exit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public final class SceneryBuilder extends MapGameBuilder {

    private final Scenery scenery;
    List<Exit> exits;

    public SceneryBuilder() {
        this.scenery = new Scenery();
        this.exits = new ArrayList<>();
        super.mapGame = this.scenery;
        super.doors = new HashMap<>();
        super.itens = new HashMap<>();
    }

    public static SceneryBuilder builder() {
        return new SceneryBuilder();
    }

    public SceneryBuilder exits(List<Exit> exits) {
        if (Objects.nonNull(exits) && exits.size() != 0)
            this.exits.addAll(exits);
        return this;
    }

    @Override
    public Scenery build() {
        this.scenery.setExits(exits);
        super.build();
        return this.scenery;
    }
}
