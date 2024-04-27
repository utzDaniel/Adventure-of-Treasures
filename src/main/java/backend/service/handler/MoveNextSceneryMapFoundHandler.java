package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Direction;
import backend.service.infra.CacheService;
import backend.service.interfaces.IMove;

import java.util.Objects;
import java.util.Optional;

public final class MoveNextSceneryMapFoundHandler extends Handler<IMove> {

    private final Direction direction;

    public MoveNextSceneryMapFoundHandler(Direction direction) {
        this.direction = direction;
    }

    @Override
    protected Optional<TypeMessage> hook(IMove move) {
        var nextScenery = Objects.requireNonNull(move.getCurrentMap().getExit(this.direction)
                .map(CacheService::getMapGame)
                .orElse(null)).orElse(null);

        return Objects.isNull(nextScenery)
                ? Optional.of(TypeMessage.MAP_ERROR_FOUND) : Optional.empty();
    }
}