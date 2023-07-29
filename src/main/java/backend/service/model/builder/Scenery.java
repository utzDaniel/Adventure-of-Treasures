package backend.service.model.builder;

import backend.service.enums.Move;
import backend.service.model.Exit;

import java.util.ArrayList;
import java.util.List;

public final class Scenery extends MapGame {

    private List<Exit> exits;

    public Scenery() {
        this.exits = new ArrayList<>();
    }

    public int getExit(Move move) {
        return exits.stream()
                .filter(exit -> exit.move().name().equals(move.name()))
                .map(Exit::idMapGame)
                .findFirst().orElse(-1);
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
