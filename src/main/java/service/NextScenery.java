package service;

import model.*;
import view.InterfaceGame;

import java.util.Objects;

public class NextScenery {

    private final Player player;
    private final InterfaceGame interfaceGame;

    public NextScenery(Player player, InterfaceGame interfaceGame) {
        this.player = player;
        this.interfaceGame = interfaceGame;
    }

    public boolean run(String direction) {
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit(direction);
        if (Objects.isNull(nextScenery)) return false;
        player.setCurrentMap(nextScenery);
        newPosition(direction);
        interfaceGame.getMapGameJLabel().setIcon(nextScenery.getImagemIcon());
        return true;

    }

    private void newPosition(String direction) {
        switch (direction) {
            case "norte" -> player.setPositionPlayerY(interfaceGame.getMapGameJLabel().getHeight() - 70, interfaceGame.getPlayerJLabel());
            case "sul" -> player.setPositionPlayerY(10, interfaceGame.getPlayerJLabel());
            case "oeste" -> player.setPositionPlayerX(interfaceGame.getMapGameJLabel().getWidth() - 50, interfaceGame.getPlayerJLabel());
            case "leste" -> player.setPositionPlayerX(10, interfaceGame.getPlayerJLabel());
        }
    }
}
