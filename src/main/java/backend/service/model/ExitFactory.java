package backend.service.model;

import backend.repository.interfaces.IExitEntity;
import backend.service.enums.Direction;

public final class ExitFactory {

    private ExitFactory() {
    }

    public static Exit create(IExitEntity entity) {
        var idMapGame = entity.idMapExt();
        var direction = Direction.getInstance(entity.direction());
        return new Exit(direction, idMapGame);
    }
}
