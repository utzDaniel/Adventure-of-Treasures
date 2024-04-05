package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.infra.CacheService;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.ICoordinate;
import backend.service.model.MapGame;
import backend.service.model.Player;

import java.util.Objects;

public final class InteractDoorCommand implements ICommand {

    private final Player player;
    private final MapGame oldMapGame;
    private final ICoordinate oldCoordinate;

    public InteractDoorCommand(Player player) {
        this.player = player;
        this.oldMapGame = player.getCurrentMap();
        this.oldCoordinate = ICoordinate.getInstance(player.getCoordinate());
    }

    @Override
    public TypeMessage execute() {
        var coordinate = this.player.getCoordinate();
        coordinate.move(this.player.getDirection().getMove());
        var door = this.player.getCurrentMap().getDoor(coordinate).orElse(null);

        if (Objects.isNull(door))
            return TypeMessage.DOOR_ERROR_FOUND;

        if (!door.isOpen())
            return TypeMessage.DOOR_ERROR_CLOSED;

        var mapGame = CacheService.getMapGame(door.getIdMapGame());
        if (mapGame.isEmpty()) return TypeMessage.MAP_NOT_FOUND;

        updateMove(mapGame.get());
        this.player.setCurrentMap(mapGame.get());

        return TypeMessage.DOOR_OPEN;
    }

    @Override
    public void undo() {
        this.player.setCurrentMap(this.oldMapGame);
        this.player.updateMove(this.player.getDirection(), this.oldCoordinate);
    }

    private void updateMove(MapGame mapGame) {
        var idMapGame = this.player.getCurrentMap().id();
        var door = mapGame.getDoorByMap(idMapGame);
        if (door.isEmpty()) return;
        this.player.updateMove(this.player.getDirection(), door.get().getCoordinate());
    }

}
