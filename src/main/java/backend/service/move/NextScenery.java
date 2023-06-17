package backend.service.move;

import backend.controller.exception.MapGameException;
import backend.service.model.Player;
import backend.service.model.builder.MapGame;
import backend.service.model.builder.Scenery;
import backend.service.interfaces.ICoordinate;

import java.util.Objects;

public final class NextScenery {

    private final Player player;
    private final ICoordinate coordinate;

    public NextScenery(ICoordinate coordinate) {
        this.player = Player.getInstance();
        this.coordinate = coordinate;
    }

    public void run(String direction) {
        //TODO não validou a direction e nem setDirection, e faltou o set icon player
        MapGame nextScenery = ((Scenery) this.player.getCurrentMap()).getExit(direction);
        if (Objects.isNull(nextScenery)) throw new MapGameException("Exit map não encontrado");
        this.player.setCurrentMap(nextScenery);
        newPosition(direction);

    }

    private void newPosition(String direction) {
        var upCoordinate = this.player.getLocation();
        switch (direction) {
            case "norte" -> upCoordinate.setY(this.coordinate.y() - 70);
            case "sul" -> upCoordinate.setY(10);
            case "oeste" -> upCoordinate.setX(this.coordinate.x() - 50);
            case "leste" -> upCoordinate.setX(10);
            default -> this.player.getLocation();
        }
        this.player.setLocation(upCoordinate);
    }
}
