package backend.service.model;

import backend.repository.interfaces.IExitEntity;
import backend.service.enums.Move;

import java.util.Locale;

public final class ExitFactory {

    private IExitEntity exitEntity;

    public Exit create(IExitEntity exitEntity) {
        this.exitEntity = exitEntity;
        return run();
    }

    private Exit run() {
        var direction = exitEntity.direction();
        var mapGame = exitEntity.mapGame();
        var move = Enum.valueOf(Move.class, direction.toUpperCase(Locale.ROOT));
        return new Exit(move, mapGame);
    }
}
