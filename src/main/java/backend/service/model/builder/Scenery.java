package backend.service.model.builder;

import backend.controller.exception.MapGameException;
import backend.service.infra.Cache;
import backend.service.model.Exit;

import java.util.ArrayList;
import java.util.List;

public final class Scenery extends MapGame {

    private List<Exit> exits;

    public Scenery() {
        this.exits = new ArrayList<>();
    }

    public MapGame getExit(String direction) {
        var mapGame = exits.stream()
                .filter(exit -> exit.direction().equals(direction))
                .map(Exit::mapGame)
                .findFirst().orElseThrow(() -> new MapGameException("Exit map não encontrado"));
        return Cache.getMapGame(mapGame);
    }

    void setExits(List<Exit> exits) {
        this.exits = exits;
    }

    @Override
    public String toString() {
        return """
                MapGame: %s
                {
                    "exits": "%s"
                }
                """.formatted(super.toString(), this.exits);
    }
}
