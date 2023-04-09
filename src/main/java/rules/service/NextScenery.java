package rules.service;

import backend.model.Player;
import backend.model.builder.map.Scenery;
import frontend.view.InterfaceGame;
import rules.interfaces.ICoordinate;

import java.util.Objects;

public class NextScenery {

    private final Player player;
    private final InterfaceGame interfaceGame;

    public NextScenery(InterfaceGame interfaceGame) {
        this.player = Player.getInstance();
        this.interfaceGame = interfaceGame;
    }

    public boolean run(String direction) {
        //TODO não validou a direction e nem setDirection, e faltou o set icon player
        Scenery nextScenery = ((Scenery) this.player.getCurrentMap()).getExit(direction);
        if (Objects.isNull(nextScenery)) return false;
        this.player.setCurrentMap(nextScenery);
        newPosition(direction);
        this.interfaceGame.getMapGameJLabel().setIcon(nextScenery.getIcon());
        return true;

    }

    private void newPosition(String direction) {
        ICoordinate coordinate = this.player.getLocation();
        switch (direction) {
            case "norte" -> coordinate.setY(this.interfaceGame.getMapGameJLabel().getHeight() - 70);
            case "sul" -> coordinate.setY(10);
            case "oeste" -> coordinate.setX(this.interfaceGame.getMapGameJLabel().getWidth() - 50);
            case "leste" -> coordinate.setX(10);
        }
        this.player.setLocation(coordinate);
    }
}
