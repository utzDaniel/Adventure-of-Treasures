package backend.service.component.move;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Move;
import backend.service.infra.Cache;
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
        int idScenery = -1;

        if (map instanceof Scenery map1)
            idScenery = map1.getExit(this.move);

        if (idScenery == -1)
            return TypeMessage.MOVE_MAP_NOT_EXIT;

        var nextScenery = Cache.getMapGame(idScenery);
        if (Objects.isNull(nextScenery))
            return TypeMessage.MOVE_MAP_NOT_FOUND;

        this.player.setCurrentMap(nextScenery);

        return TypeMessage.MOVE_SUCESS;
    }
}
