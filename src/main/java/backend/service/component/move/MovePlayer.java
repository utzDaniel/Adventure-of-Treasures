package backend.service.component.move;

import backend.service.interfaces.ICoordinate;
import backend.service.model.Player;

import java.util.Arrays;
import java.util.Optional;

public final class MovePlayer {

    private final String direction;
    private final Player player;
    private final ICoordinate coordinate;
    private backend.service.enums.MovePlayer move;

    public MovePlayer(Player player, String direction) {
        this.direction = direction;
        this.player = player;
        this.coordinate = player.getLocation();
    }

    public boolean run() {
        getMovePlayer().ifPresent(m -> this.move = m);
        updateImagePlayer();
        updateDirectionPlayer();
        updateCoordinate();
        if (validCoordinate()) updateLocationPlayer();
        return validCoordinate();
    }


    public String getName() {
        return "walk";
    }

    private Optional<backend.service.enums.MovePlayer> getMovePlayer() {
        return Arrays.stream(backend.service.enums.MovePlayer.values())
                .filter(movePlayer -> movePlayer.getDirection().equals(this.direction))
                .findFirst();
    }

    private void updateImagePlayer() {
        this.move.run();
        this.player.setIcon(this.move.getImage());
    }

    private void updateCoordinate() {
        this.coordinate.move(move.getCoordinate());
    }

    private void updateDirectionPlayer() {
        this.player.setDirection(this.direction);
    }

    private boolean validCoordinate() {
        var area = this.player.getCurrentMap().getArea();
        return area.isBlock(this.coordinate);
    }

    private void updateLocationPlayer() {
        this.player.setLocation(this.coordinate);
    }
}
