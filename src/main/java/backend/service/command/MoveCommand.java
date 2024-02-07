package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Direction;
import backend.service.infra.Cache;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.ICoordinate;
import backend.service.model.MapGame;
import backend.service.model.Player;

public final class MoveCommand implements ICommand {

    private final Player player;
    private final Direction direction;
    private final ICoordinate oldCoordinate;
    private final Direction oldDirection;
    private final MapGame oldScenery;

    public MoveCommand(Player player, Direction direction) {
        this.player = player;
        this.direction = direction;
        this.oldCoordinate = ICoordinate.getInstance(player.getCoordinate());
        this.oldDirection = player.getDirection();
        this.oldScenery = player.getCurrentMap();
    }

    @Override
    public TypeMessage execute() {
        var coordinate = ICoordinate.getInstance(this.player.getCoordinate());
        coordinate.move(this.direction.getMove());

        if (!this.player.getCurrentMap().isNextScenery(coordinate)) {

            var area = this.player.getCurrentMap().getArea();
            if (area.blocked(coordinate)) {
                this.player.updateMove(this.direction, this.oldCoordinate);
                return TypeMessage.MOVE_BLOCKED;
            }

            this.player.updateMove(this.direction, coordinate);
        } else {

            var idMap = this.player.getCurrentMap().getExit(this.direction);
            if (idMap.isEmpty()) {
                this.player.updateMove(this.direction, this.oldCoordinate);
                return TypeMessage.MOVE_NEXT_SCENERY_NOT_EXIT;
            }

            var nextScenery = Cache.getMapGame(idMap.get());
            if (nextScenery.isEmpty()) {
                this.player.updateMove(this.direction, this.oldCoordinate);
                return TypeMessage.MAP_NOT_FOUND;
            }

            var newCoordinate = this.player.getCurrentMap().nextScenery(coordinate);
            this.player.updateMove(this.direction, newCoordinate);
            this.player.setCurrentMap(nextScenery.get());
        }

        return TypeMessage.MOVE;
    }

    @Override
    public void undo() {
        this.player.setCurrentMap(this.oldScenery);
        this.player.updateMove(this.oldDirection, this.oldCoordinate);
    }
}
