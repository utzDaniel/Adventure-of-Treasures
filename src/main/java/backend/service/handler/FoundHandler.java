package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeObject;

import java.util.Objects;
import java.util.Optional;

public final class FoundHandler<T> extends Handler<T> {

    private final TypeObject object;

    public FoundHandler(TypeObject object) {
        this.object = object;
    }

    @Override
    protected Optional<TypeMessage> hook(T object) {
        if (Objects.nonNull(object)) return Optional.empty();

        return switch (this.object) {
            case DOOR -> Optional.of(TypeMessage.DOOR_ERROR_FOUND);
            case ITEM -> Optional.of(TypeMessage.ITEM_ERROR_FOUND);
            case NPC -> Optional.of(TypeMessage.NPC_ERROR_FOUND);
        };
    }

}
