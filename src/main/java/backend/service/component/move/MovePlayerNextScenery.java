package backend.service.component.move;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Move;
import backend.service.infra.Cache;
import backend.service.model.Player;

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

        var idMap = map.getExit(this.move);
        if (idMap.isEmpty())
            return TypeMessage.MOVE_MAP_NOT_EXIT;

        var nextScenery = Cache.getMapGame(idMap.get());
        if (Objects.isNull(nextScenery))
            return TypeMessage.MOVE_MAP_NOT_FOUND;

        this.player.setCurrentMap(nextScenery);

        return TypeMessage.MOVE_SUCESS;
    }
}
