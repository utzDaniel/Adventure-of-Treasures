package backend.service.component.move;

import backend.service.component.move.valid.MoveSceneryValidIsExistMap;
import backend.service.infra.Cache;
import backend.service.model.Player;
import backend.service.model.builder.Scenery;
import backend.service.interfaces.ICoordinate;
import backend.service.component.move.valid.MoveSceneryValidIsExistName;

public final class MoveScenery {

    private final Player player;
    private final ICoordinate coordinate;

    public MoveScenery(ICoordinate coordinate) {
        this.player = Player.getInstance();
        this.coordinate = coordinate;
    }

    public void run(String direction) {
        //TODO não validou a direction e nem setDirection, e faltou o set icon player
        var nameScenery = ((Scenery) this.player.getCurrentMap()).getExit(direction);
        new MoveSceneryValidIsExistName().valid(nameScenery);
        var nextScenery = Cache.getMapGame(nameScenery);
        new MoveSceneryValidIsExistMap().valid(nextScenery);
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
