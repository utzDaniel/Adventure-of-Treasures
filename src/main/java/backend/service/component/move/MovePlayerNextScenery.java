package backend.service.component.move;

import backend.controller.enums.TypeMessage;
import backend.service.enums.Move;
import backend.service.infra.Cache;
import backend.service.model.Area;
import backend.service.model.Player;
import backend.service.model.builder.Scenery;

import java.util.Locale;
import java.util.Objects;

public final class MovePlayerNextScenery {

    private final Player player;
    private final String direction;
    private Move move;

    public MovePlayerNextScenery(Player player, String direction) {
        this.direction = direction;
        this.player = player;
    }

    public TypeMessage run() {
        var map = this.player.getCurrentMap();
        String nameScenery = "";

        if (map instanceof Scenery map1)
            nameScenery = map1.getExit(this.direction);

        if (nameScenery.isEmpty())
            return TypeMessage.MAP_NOT_EXIT;

        var nextScenery = Cache.getMapGame(nameScenery);
        if (Objects.isNull(nextScenery))
            return TypeMessage.MAP_NOT_FOUND;

        this.player.setCurrentMap(nextScenery);
        this.move = getMovePlayer();
        updateImagePlayer();
        updateDirectionPlayer();
        newPosition();
        return TypeMessage.MOVE_SUCESS;
    }

    private void newPosition() {
        var upCoordinate = this.player.getLocation();
        switch (this.direction) {
            case "norte" -> upCoordinate.setX(Area.maxX());
            case "sul" -> upCoordinate.setX(Area.minX());
            case "oeste" -> upCoordinate.setY(Area.maxY());
            case "leste" -> upCoordinate.setY(Area.minY());
            default -> this.player.getLocation();
        }
        this.player.setLocation(upCoordinate);
    }

    private Move getMovePlayer() {
        return Enum.valueOf(Move.class, direction.toUpperCase(Locale.ROOT));
    }

    private void updateImagePlayer() {
        this.move.run();
        this.player.setIcon(this.move.getImage());
    }

    private void updateDirectionPlayer() {
        this.player.setDirection(this.direction);
    }
}
