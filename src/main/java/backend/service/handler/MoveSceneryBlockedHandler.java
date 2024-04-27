package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Direction;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IMove;

import java.util.Optional;

public final class MoveSceneryBlockedHandler extends Handler<IMove> {

    private final Direction direction;

    public MoveSceneryBlockedHandler(Direction direction) {
        this.direction = direction;
    }

    @Override
    protected Optional<TypeMessage> hook(IMove move) {
        var coordinate = ICoordinate.getInstance(move.getCoordinate());
        coordinate.move(this.direction.getMove());

        var area = move.getCurrentMap().getArea();
        var blocked = area.blocked(coordinate);

        return blocked
                ? Optional.of(TypeMessage.MOVE_BLOCKED) : Optional.empty();
    }
}