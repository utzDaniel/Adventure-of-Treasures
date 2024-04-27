package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Direction;
import backend.service.interfaces.IMove;

import java.util.Objects;
import java.util.Optional;

public final class MoveNextSceneryExitHandler extends Handler<IMove> {

    private final Direction direction;

    public MoveNextSceneryExitHandler(Direction direction) {
        this.direction = direction;
    }

    @Override
    protected Optional<TypeMessage> hook(IMove move) {
        var idMap = move.getCurrentMap().getExit(this.direction).orElse(null);

        return Objects.isNull(idMap)
                ? Optional.of(TypeMessage.MOVE_NEXT_SCENERY_NOT_EXIT) : Optional.empty();
    }
}