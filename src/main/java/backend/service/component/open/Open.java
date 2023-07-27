package backend.service.component.open;

import backend.controller.enums.TypeMessage;
import backend.service.infra.Cache;
import backend.service.model.Player;
import backend.service.model.builder.MapGame;

import java.util.Objects;

public final class Open {

    private final Player player;

    public Open(Player player) {
        this.player = player;
    }

    public TypeMessage run() {
        if (isFinish()) return TypeMessage.GAME_SUCESS_FINISH;

        var door = player.getCurrentMap().getDoor(player.getLocation()).orElse(null);

        if (Objects.isNull(door))
            return TypeMessage.DOOR_NOT_EXIT;
        if (!door.isOpen())
            return TypeMessage.DOOR_CLOSED;

        MapGame mapGame = Cache.getMapGame(door.getMapGame());
        updatePositionPlayer(mapGame);
        player.setCurrentMap(mapGame);

        return TypeMessage.DOOR_SUCESS_OPEN;
    }

    private boolean isFinish() {
        var coordinate = player.getLocation();
        var item = player.getInventory().getItem("tesouro");
        return coordinate.x() == 71 && coordinate.y() == 28 && Objects.nonNull(item);
    }

    private void updatePositionPlayer(MapGame mapGame) {
        var nameMap = player.getCurrentMap().getName();
        var door = mapGame.getDoor(nameMap).get();
        player.setLocation(door.getCoordinate());
    }

}
