package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.model.MapGame;

import java.util.Optional;

public final class AddItemMapGameFullHandler extends Handler<Integer> {

    private final MapGame mapGame;

    public AddItemMapGameFullHandler(MapGame mapGame) {
        this.mapGame = mapGame;
    }

    @Override
    protected Optional<TypeMessage> hook(Integer areaTraveled) {
        var valid = areaTraveled >= this.mapGame.areaSize();
        return valid ? Optional.of(TypeMessage.MAP_ADD_ERROR_FULL) : Optional.empty();
    }
}
