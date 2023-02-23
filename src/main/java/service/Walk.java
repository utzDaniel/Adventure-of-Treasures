package service;

import exception.MoveException;
import model.Coordinate;
import model.Player;
import model.builder.item.Item;
import model.enums.MovePlayer;

import javax.swing.*;
import java.util.Arrays;

public final class Walk {

    private final String direction;
    private final Player player;
    private Coordinate coordinate;
    private MovePlayer move;

    public Walk(Player player, String direction) {
        this.direction = direction;
        this.player = player;
        this.coordinate = player.getLocation();
    }

    public void run() {
        this.move = getMovePlayer();
        updateImagePlayer();
        updateDirectionPlayer();
        updateCoordinate();
        if (validCoordinate()) updateLocationPlayer();
    }

    private MovePlayer getMovePlayer() {
        return Arrays.stream(MovePlayer.values())
                .filter(movePlayer -> movePlayer.getDirection().equals(this.direction))
                .findFirst().orElseThrow(() -> new MoveException("Direção invalida!"));
    }

    private void updateImagePlayer() {
        this.move.run();
        this.player.getJLabel().setIcon(this.move.getImage());
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
