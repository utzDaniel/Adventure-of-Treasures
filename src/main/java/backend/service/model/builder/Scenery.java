package backend.service.model.builder;

import backend.repository.factory.RepositoryFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Scenery extends MapGame {

    private Map<String, String> exits;

    public Scenery() {
        this.exits = new HashMap<>();
    }

    public Scenery getExit(String direction) {
        return Objects.isNull(this.exits.get(direction))
                ? null : (Scenery) RepositoryFactory.getRepositoryMapGame().get(this.exits.get(direction));
    }

    void setExits(Map<String, String> exits) {
        this.exits = exits;
    }

    @Override
    public String toString() {
        return "Scenery{" +
                "exits=" + this.exits +
                "} " + super.toString();
    }
}