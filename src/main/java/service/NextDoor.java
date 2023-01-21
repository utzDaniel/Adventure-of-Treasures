package service;

import exception.ItemUsableException;
import exception.MapGameException;
import model.*;
import view.InterfaceGame;

import java.util.Objects;

public class NextDoor {

    private final Player player;
    private final InterfaceGame interfaceGame;

    public NextDoor(InterfaceGame interfaceGame) {
        this.player = Player.getInstance();
        this.interfaceGame = interfaceGame;
    }

    public void run() {
        var door = player.getCurrentMap().getDoorMap(player.getPositionPlayerX(), player.getPositionPlayerY());
        if (door.isEmpty()) return;
        if(!door.get().isOpen()) throw new MapGameException("Porta está fechada!");
        MapGame mapGame = player.getCurrentMap().getMapDoor(door.get());
        setPositionPlayer(door.get());
        player.setCurrentMap(mapGame);
        interfaceGame.getMapGameJLabel().setIcon(mapGame.getImagemIcon());
    }

    private void setPositionPlayer(Door door) {
        if (door.getPositionInsideX() == player.getPositionPlayerX() && door.getPositionInsideY() == player.getPositionPlayerY()) {
            player.setPositionPlayerX(door.getPositionOutsideX());
            player.setPositionPlayerY(door.getPositionOutsideY());
        } else {
            player.setPositionPlayerX(door.getPositionInsideX());
            player.setPositionPlayerY(door.getPositionInsideY());
        }
    }
}
