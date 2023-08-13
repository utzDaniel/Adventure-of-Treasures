package backend.service.component.open;

import backend.controller.enums.TypeMessage;
import backend.service.infra.Cache;
import backend.service.model.Player;
import backend.service.model.MapGame;

import java.util.Objects;

public final class Open {

    private final Player player;

    public Open(Player player) {
        this.player = player;
    }

    public TypeMessage run() {
        if (isFinish()) return TypeMessage.GAME_SUCESS_FINISH;

        var door = player.getCurrentMap().getDoor(player.getCoordinate()).orElse(null);

        if (Objects.isNull(door))
            return TypeMessage.DOOR_NOT_EXIT;
        if (!door.isOpen())
            return TypeMessage.DOOR_CLOSED;

        MapGame mapGame = Cache.getMapGame(door.getIdMapGame());
        updatePositionPlayer(mapGame);
        player.setCurrentMap(mapGame);

        return TypeMessage.DOOR_SUCESS_OPEN;
    }

    private boolean isFinish() {
        var coordinate = player.getCoordinate();
        var item = player.getInventory().getItem(15);
        return coordinate.x() == 71 && coordinate.y() == 28 && Objects.nonNull(item);
    }

    private void updatePositionPlayer(MapGame mapGame) {
        var idMapGame = player.getCurrentMap().getId();
        var door = mapGame.getDoor(idMapGame).get();
        player.setCoordinate(door.getCoordinate());
    }

}
