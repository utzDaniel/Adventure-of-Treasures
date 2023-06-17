package backend.service.model;

import backend.repository.interfaces.IExitEntity;

public final class ExitFactory {

    private IExitEntity exitEntity;

    public Exit create(IExitEntity exitEntity) {
        this.exitEntity = exitEntity;
        return run();
    }

    private Exit run() {
        var direction = exitEntity.direction();
        var mapGame = exitEntity.mapGame();
        return new Exit(direction, mapGame);
    }
}
