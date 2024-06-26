package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Direction;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IHandler;
import backend.service.interfaces.IMove;

public final class MoveSceneryCommand implements ICommand {

    private final IMove person;
    private final Direction direction;
    private final IHandler<IMove> handler;

    public MoveSceneryCommand(IMove person, Direction direction, IHandler<IMove> handler) {
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

        var coordinate = ICoordinate.getInstance(this.person.getCoordinate());
        coordinate.move(this.direction.getMove());
        this.person.updateMove(this.direction, coordinate);

        return TypeMessage.MOVE;
    }

}
