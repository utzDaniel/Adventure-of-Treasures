package service;

import exception.MapGameException;
import model.Door;
import model.Player;
import model.builder.map.MapGame;
import repository.RepositoryMapGame;
import view.InterfaceGame;

public class NextDoor {

    private final Player player;
    private final InterfaceGame interfaceGame;

    public NextDoor(InterfaceGame interfaceGame) {
        this.player = Player.getInstance();
        this.interfaceGame = interfaceGame;
    }

    public void run() {
        var door = player.getCurrentMap().getDoor(player.getPositionPlayerX(), player.getPositionPlayerY());
        if (door.isEmpty()) return;
        if (!door.get().isOpen()) throw new MapGameException("Porta está fechada!");
        MapGame mapGame = player.getCurrentMap().getMapDoor(door.get());
        setPositionPlayer(mapGame.getDoor(player.getCurrentMap().getName()).get());
        player.setCurrentMap(mapGame);
        interfaceGame.getMapGameJLabel().setIcon(mapGame.getImage());
    }

    private void setPositionPlayer(Door door) {
        player.setPositionPlayerX(door.getCoordinate().getAxisX()*10);
        player.setPositionPlayerY(door.getCoordinate().getAxisY()*10);
    }
}
