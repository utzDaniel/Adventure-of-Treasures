package service;

import exception.MapGameException;
import model.*;
import view.InterfaceGame;

public class NextDoor {

    private final Player player;
    private final InterfaceGame interfaceGame;

    public NextDoor(Player player, InterfaceGame interfaceGame) {
        this.player = player;
        this.interfaceGame = interfaceGame;
    }

    public void run() {
        Door door = player.getCurrentMap().getDoorMap(player.getPositionPlayerX(), player.getPositionPlayerY());
        if (door == null) return;
        if(!door.isOpen()) throw new MapGameException("Porta está fechada!");
        MapGame mapGame = player.getCurrentMap().getMapDoor(door);
        setPositionPlayer(door);
        player.setCurrentMap(mapGame);
        interfaceGame.getMapGameJLabel().setIcon(mapGame.getImagemIcon());
    }

    private void setPositionPlayer(Door door) {
        if (door.getPositionInsideX() == player.getPositionPlayerX() && door.getPositionInsideY() == player.getPositionPlayerY()) {
            player.setPositionPlayerX(door.getPositionOutsideX(), interfaceGame.getPlayerJLabel());
            player.setPositionPlayerY(door.getPositionOutsideY(), interfaceGame.getPlayerJLabel());
        } else {
            player.setPositionPlayerX(door.getPositionInsideX(), interfaceGame.getPlayerJLabel());
            player.setPositionPlayerY(door.getPositionInsideY(), interfaceGame.getPlayerJLabel());
        }
    }
}
