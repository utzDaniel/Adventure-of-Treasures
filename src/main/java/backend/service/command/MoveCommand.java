package backend.service.command;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Direction;
import backend.service.interfaces.ICommand;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IMove;

public final class MoveCommand implements ICommand {

    private final IMove person;
    private final Direction direction;

    public MoveCommand(IMove person, Direction direction) {
        this.person = person;
        this.direction = direction;
    }

    @Override
    public TypeMessage execute() {
        var coordinate = ICoordinate.getInstance(this.person.getCoordinate());
        coordinate.move(this.direction.getMove());

        return getCommand(coordinate).execute();
    }

    private ICommand getCommand(ICoordinate coordinate) {
        if (this.person.getCurrentMap().isNextScenery(coordinate)) {
            return CommandFactory.createMoveNextSceneryCommand(this.person, this.direction);
        } else {
            return CommandFactory.createMoveSceneryCommand(this.person, this.direction);
        }
    }

}