package backend.service.component.move;

import backend.controller.enums.TypeMessage;
import backend.service.enums.MoveCommand2;
import backend.service.infra.Cache;
import backend.service.model.Player;

public final class MovePlayerNextScenery {

    private final Player player;
    private final MoveCommand2 move;

    public MovePlayerNextScenery(Player player, MoveCommand2 move) {
        this.move = move;
        this.player = player;
    }

    public TypeMessage run() {
        var map = this.player.getCurrentMap();

        var idMap = map.getExit(this.move);
        if (idMap.isEmpty())
            return TypeMessage.MOVE_MAP_NOT_EXIT;

        var nextScenery = Cache.getMapGame(idMap.get());
        if (nextScenery.isEmpty()) return TypeMessage.MOVE_MAP_NOT_FOUND;

        this.player.setCurrentMap(nextScenery.get());

        return TypeMessage.MOVE;
    }
}
