package backend.service.component.move;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Move;
import backend.service.infra.Cache;
import backend.service.interfaces.ICoordinate;
import backend.service.model.Player;
import backend.service.model.builder.Scenery;

import java.util.Objects;

public final class MovePlayerNextScenery {

    private final Player player;
    private final Move move;

    public MovePlayerNextScenery(Player player, Move move) {
        this.move = move;
        this.player = player;
    }

    public TypeMessage run() {
        var map = this.player.getCurrentMap();
        String nameScenery = "";

        if (map instanceof Scenery map1)
            nameScenery = map1.getExit(this.move);

        if (nameScenery.isEmpty())
            return TypeMessage.MAP_NOT_EXIT;

        var nextScenery = Cache.getMapGame(nameScenery);
        if (Objects.isNull(nextScenery))
            return TypeMessage.MAP_NOT_FOUND;

        this.player.setCurrentMap(nextScenery);
        updateImagePlayer();
        updateDirectionPlayer();
        this.player.setLocation(newCoordinate());
        return TypeMessage.MOVE_SUCESS;
    }

    private ICoordinate newCoordinate() {
        var coordinate = this.player.getLocation();
        return this.move.coordinateByNextScenery(coordinate);
    }


    private void updateImagePlayer() {
        this.move.run();
        this.player.setIcon(this.move.getImage());
    }

    private void updateDirectionPlayer() {
        this.player.setMove(this.move);
    }
}
