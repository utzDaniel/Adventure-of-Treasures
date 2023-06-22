package backend.service.component.move.valid;

import backend.controller.exception.MapGameException;
import backend.service.model.builder.MapGame;

import java.util.Objects;

public final class MoveSceneryValidIsExistMap {

    public void valid(MapGame mapGame) {
        if (Objects.isNull(mapGame)) throw new MapGameException("Exit map não encontrado");
    }
}
