package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Direction;
import backend.service.infra.CacheService;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IHandler;
import backend.service.interfaces.IMove;

import java.util.Objects;

public final class MoveNextSceneryCommand implements ICommand {

    private final IMove person;
    private final Direction direction;
    private final IHandler<IMove> handler;

    public MoveNextSceneryCommand(IMove person, Direction direction, IHandler<IMove> handler) {
        this.person = person;
        this.direction = direction;
        this.handler = handler;
    }

    @Override
    public TypeMessage execute() {
        var msg = this.handler.handle(this.person);
        if (msg.isPresent()) {
            this.person.updateMove(this.direction, this.person.getCoordinate());
            return msg.get();
        }

        var nextScenery = Objects.requireNonNull(this.person.getCurrentMap()
                .getExit(this.direction)
                .map(CacheService::getMapGame)
                .orElse(null)).orElse(null);

        var coordinate = ICoordinate.getInstance(this.person.getCoordinate());
        coordinate.move(this.direction.getMove());

        var newCoordinate = this.person.getCurrentMap().nextScenery(coordinate);
        this.person.updateMove(this.direction, newCoordinate);
        this.person.setCurrentMap(nextScenery);

        return TypeMessage.MOVE;
    }

}
