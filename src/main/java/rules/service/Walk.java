package rules.service;

import backend.enums.MovePlayer;
import backend.model.Player;
import backend.model.interfaces.Command;
import rules.exception.MoveException;
import rules.interfaces.ICoordinate;

import java.util.Arrays;

public final class Walk implements Command {

    private final String direction;
    private final Player player;
    private ICoordinate coordinate;
    private MovePlayer move;

    public Walk(Player player, String direction) {
        this.direction = direction;
        this.player = player;
        this.coordinate = player.getLocation();
    }

    public boolean run() {
        this.move = getMovePlayer();
        updateImagePlayer();
        updateDirectionPlayer();
        updateCoordinate();
        if (validCoordinate()) updateLocationPlayer();
        return validCoordinate();
    }

    @Override
    public String getName() {
        return "walk";
    }

    private MovePlayer getMovePlayer() {
        return Arrays.stream(MovePlayer.values())
                .filter(movePlayer -> movePlayer.getDirection().equals(this.direction))
                .findFirst().orElseThrow(() -> new MoveException("Direção invalida!"));
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
        return this.player.getCurrentMap().checkLimits(this.coordinate);
    }

    private void updateLocationPlayer() {
        this.player.setLocation(this.coordinate);
    }
}
