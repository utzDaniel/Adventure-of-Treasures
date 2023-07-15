package backend.service.component.move;

import backend.service.component.move.valid.MoveSceneryValidIsExistMap;
import backend.service.component.move.valid.MoveSceneryValidIsExistName;
import backend.service.enums.MovePlayer;
import backend.service.infra.Cache;
import backend.service.model.Area;
import backend.service.model.Player;
import backend.service.model.builder.Scenery;

import java.util.Arrays;
import java.util.Optional;

public final class MoveScenery {

    private final Player player;
    private final String direction;
    private backend.service.enums.MovePlayer move;

    public MoveScenery(Player player, String direction) {
        this.direction = direction;
        this.player = player;
    }

    public void run() {
        var nameScenery = ((Scenery) this.player.getCurrentMap()).getExit(this.direction);
        new MoveSceneryValidIsExistName().valid(nameScenery);
        var nextScenery = Cache.getMapGame(nameScenery);
        new MoveSceneryValidIsExistMap().valid(nextScenery);
        this.player.setCurrentMap(nextScenery);
        getMovePlayer().ifPresent(m -> this.move = m);
        updateImagePlayer();
        updateDirectionPlayer();
        newPosition();
    }

    private void newPosition() {
        var upCoordinate = this.player.getLocation();
        switch (this.direction) {
            case "norte" -> upCoordinate.setY(Area.limitY());
            case "sul" -> upCoordinate.setY(Area.minY());
            case "oeste" -> upCoordinate.setX(Area.limitX());
            case "leste" -> upCoordinate.setX(Area.minX());
            default -> this.player.getLocation();
        }
        this.player.setLocation(upCoordinate);
    }

    private Optional<MovePlayer> getMovePlayer() {
        return Arrays.stream(backend.service.enums.MovePlayer.values())
                .filter(movePlayer -> movePlayer.getDirection().equals(this.direction))
                .findFirst();
    }

    private void updateImagePlayer() {
        this.move.run();
        this.player.setIcon(this.move.getImage());
    }

    private void updateDirectionPlayer() {
        this.player.setDirection(this.direction);
    }
}
