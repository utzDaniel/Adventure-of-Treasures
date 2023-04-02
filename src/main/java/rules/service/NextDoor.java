package rules.service;

import rules.exception.MapGameException;
import backend.model.Door;
import backend.model.Player;
import backend.model.builder.map.MapGame;
import frontend.view.InterfaceGame;

import java.util.Optional;

public class NextDoor {

    private final Player player;
    private final InterfaceGame interfaceGame;

    public NextDoor(InterfaceGame interfaceGame) {
        this.player = Player.getInstance();
        this.interfaceGame = interfaceGame;
    }

    public void run() {
        var door = getDoor();
        if (door.isEmpty()) return;
        if (!door.get().isOpen()) throw new MapGameException("Porta está fechada!");
        MapGame mapGame = player.getCurrentMap().getMapDoor(door.get());
        updatePositionPlayer(mapGame);
        player.setCurrentMap(mapGame);
        interfaceGame.getMapGameJLabel().setIcon(mapGame.getImage());
    }

    private void updatePositionPlayer(MapGame mapGame) {
        setPositionPlayer(mapGame.getDoor(player.getCurrentMap().getName()).get());
    }

    private Optional<Door> getDoor() {
        return player.getCurrentMap().getDoor(player.getLocation());
    }

    private void setPositionPlayer(Door door) {
        player.setLocation(door.getCoordinate());
    }
}
