package backend.service.model;

import backend.repository.interfaces.IExitEntity;

public class ExitFactory {

    private IExitEntity exitEntity;

    public Exit create(IExitEntity exitEntity) {
        this.exitEntity = exitEntity;
        return run();
    }

    private Exit run() {
        var mapGame = exitEntity.mapGame();
        var direction = exitEntity.direction();
        return new Exit(mapGame, direction);
    }
}
