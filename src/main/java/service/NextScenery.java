package service;

import model.*;
import model.builder.map.Scenery;
import view.InterfaceGame;

import java.util.Objects;

public class NextScenery {

    private final Player player;
    private final InterfaceGame interfaceGame;

    public NextScenery(InterfaceGame interfaceGame) {
        this.player = Player.getInstance();
        this.interfaceGame = interfaceGame;
    }

    public boolean run(String direction) {
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit(direction);
        if (Objects.isNull(nextScenery)) return false;
        player.setCurrentMap(nextScenery);
        newPosition(direction);
        interfaceGame.getMapGameJLabel().setIcon(nextScenery.getImage());
        return true;

    }

    private void newPosition(String direction) {
        switch (direction) {
            case "norte" -> player.setPositionPlayerY(interfaceGame.getMapGameJLabel().getHeight() - 70);
            case "sul" -> player.setPositionPlayerY(10);
            case "oeste" -> player.setPositionPlayerX(interfaceGame.getMapGameJLabel().getWidth() - 50);
            case "leste" -> player.setPositionPlayerX(10);
        }
    }
}
