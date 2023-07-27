package backend.service.component.move;

import backend.controller.enums.TypeMessage;
import backend.service.exception.MoveException;
import backend.service.model.Area;
import backend.service.model.Player;

public final class MovePlayer {
    private final Player player;
    private final String direction;

    public MovePlayer(String direction, Player player) {
        this.direction = direction;
        this.player = player;
    }

    public TypeMessage run() {
        if (isWalk(this.direction))
            return new MovePlayerScenery(this.player, this.direction).run();
        else
            return new MovePlayerNextScenery(this.player, this.direction).run();
    }

    private boolean isWalk(String direction) {
        return switch (direction) {
            case "norte" -> player.getLocation().x() > Area.minX();
            case "sul" -> player.getLocation().x() < Area.maxX();
            case "oeste" -> player.getLocation().y() > Area.minY();
            case "leste" -> player.getLocation().y() < Area.maxY();
            default -> throw new MoveException("Direção invalida!");
        };
    }
}
