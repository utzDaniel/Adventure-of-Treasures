package backend.service.model.builder;

import backend.service.model.Exit;

import java.util.ArrayList;
import java.util.List;

public final class Scenery extends MapGame {

    private List<Exit> exits;

    public Scenery() {
        this.exits = new ArrayList<>();
    }

    public MapGame getExit(String direction) {
        return exits.stream()
                .filter(exit -> exit.getDirection().equals(direction))
                .map(Exit::getMapGame)
                .findFirst().orElse(null);
    }

    void setExits(List<Exit> exits) {
        this.exits = exits;
    }

    @Override
    public String toString() {
        return "Scenery{" +
                "exits=" + this.exits +
                "} " + super.toString();
    }
}
