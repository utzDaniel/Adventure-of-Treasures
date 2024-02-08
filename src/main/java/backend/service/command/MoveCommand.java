package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Direction;
import backend.service.infra.Cache;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IMove;
import backend.service.model.MapGame;

public final class MoveCommand implements ICommand {

    private final IMove person;
    private final Direction direction;
    private final ICoordinate oldCoordinate;
    private final Direction oldDirection;
    private final MapGame oldScenery;

    public MoveCommand(IMove person, Direction direction) {
        this.person = person;
        this.direction = direction;
        this.oldCoordinate = ICoordinate.getInstance(person.getCoordinate());
        this.oldDirection = person.getDirection();
        this.oldScenery = person.getCurrentMap();
    }

    @Override
    public TypeMessage execute() {
        var coordinate = ICoordinate.getInstance(this.person.getCoordinate());
        coordinate.move(this.direction.getMove());

        if (!this.person.getCurrentMap().isNextScenery(coordinate)) {
            var area = this.person.getCurrentMap().getArea();
            if (area.blocked(coordinate)) {
                this.person.updateMove(this.direction, this.oldCoordinate);
                return TypeMessage.MOVE_BLOCKED;
            }

            this.person.updateMove(this.direction, coordinate);
        } else {

            var idMap = this.person.getCurrentMap().getExit(this.direction);
            if (idMap.isEmpty()) {
                this.person.updateMove(this.direction, this.oldCoordinate);
                return TypeMessage.MOVE_NEXT_SCENERY_NOT_EXIT;
            }

            var nextScenery = Cache.getMapGame(idMap.get());
            if (nextScenery.isEmpty()) {
                this.person.updateMove(this.direction, this.oldCoordinate);
                return TypeMessage.MAP_NOT_FOUND;
            }

            var newCoordinate = this.person.getCurrentMap().nextScenery(coordinate);
            this.person.updateMove(this.direction, newCoordinate);
            this.person.setCurrentMap(nextScenery.get());
        }

        return TypeMessage.MOVE;
    }

    @Override
    public void undo() {
        this.person.setCurrentMap(this.oldScenery);
        this.person.updateMove(this.oldDirection, this.oldCoordinate);
    }
}
