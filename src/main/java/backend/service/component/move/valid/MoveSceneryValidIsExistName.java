package backend.service.component.move.valid;

import backend.service.exception.MapGameException;

import java.util.Objects;

public final class MoveSceneryValidIsExistName {

    public void valid(String nameMap) {
        if (Objects.isNull(nameMap)) throw new MapGameException("Exit nameMap não encontrado");
    }
}
