package backend.service.component;

import backend.service.model.Player;
import backend.service.model.builder.Scenery;
import backend.controller.interfaces.ICoordinate;

import java.util.Objects;

public class NextScenery {

    private final Player player;
    private final ICoordinate coordinate;

    public NextScenery(ICoordinate coordinate) {
        this.player = Player.getInstance();
        this.coordinate = coordinate;
    }

    public boolean run(String direction) {
        //TODO não validou a direction e nem setDirection, e faltou o set icon player
        Scenery nextScenery = ((Scenery) this.player.getCurrentMap()).getExit(direction);
        if (Objects.isNull(nextScenery)) return false;
        this.player.setCurrentMap(nextScenery);
        newPosition(direction);
        return true;

    }

    private void newPosition(String direction) {
        ICoordinate coordinate = this.player.getLocation();
        switch (direction) {
            case "norte" -> coordinate.setY(this.coordinate.getY() - 70);
            case "sul" -> coordinate.setY(10);
            case "oeste" -> coordinate.setX(this.coordinate.getX() - 50);
            case "leste" -> coordinate.setX(10);
        }
        this.player.setLocation(coordinate);
    }
}
