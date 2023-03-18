package model.builder.map;

import repository.RepositoryMapGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Scenery extends MapGame {

    private Map<String, String> exits;

    public Scenery() {
        this.exits = new HashMap<>();
    }

    public Scenery getExit(String direction) {
        return Objects.isNull(this.exits.get(direction))
                ? null : (Scenery) RepositoryMapGame.getInstance().getMapGame(this.exits.get(direction));
    }

    protected void setExits(Map<String, String> exits) {
        this.exits = exits;
    }

    @Override
    public String toString() {
        return "Scenery{" +
                "exits=" + this.exits +
                "} " + super.toString();
    }
}