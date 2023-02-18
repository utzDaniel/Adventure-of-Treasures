package service;

import model.*;
import model.builder.map.Scenery;
import view.InterfaceGame;

import java.awt.*;
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
        int x = player.getLocation().x;
        int y = player.getLocation().y;
        switch (direction) {
            case "norte" -> y = interfaceGame.getMapGameJLabel().getHeight() - 70;
            case "sul" -> y = 10;
            case "oeste" -> x = interfaceGame.getMapGameJLabel().getWidth() - 50;
            case "leste" -> x = 10;
        }
        player.setLocation(new Point(x, y));
    }
}
