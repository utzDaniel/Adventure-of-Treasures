package service;

import exception.MoveException;
import model.Coordinate;
import model.Player;
import model.enums.MovePlayer;

import javax.swing.*;
import java.util.Arrays;

public final class Walk {

    private final String direction;
    private final Player player;
    private Coordinate coordinate;
    private MovePlayer move;

    public Walk(String direction) {
        this.direction = direction;
        this.player = Player.getInstance();
        this.coordinate = new Coordinate(player.getLocation().getPoint());
    }

    public ImageIcon run() {
        this.move = getMovePlayer();
        updateImagePlayer();
        updateCoordinate();
        if (validCoordinate()) setLocationPlayer();
        return this.move.getImage();
    }

    private MovePlayer getMovePlayer() {
        return Arrays.stream(MovePlayer.values())
                .filter(movePlayer -> movePlayer.getDirection().equals(this.direction))
                .findFirst().orElseThrow(() -> new MoveException("Direção invalida!"));
    }

    private void updateImagePlayer() {
        this.move.run();
    }

    private void updateCoordinate() {
        this.coordinate.move(move.getCoordinate());
    }

    private boolean validCoordinate() {
        return this.player.getCurrentMap().checkLimits(this.coordinate);
    }

    private void setLocationPlayer() {
        this.player.setLocation(this.coordinate);
    }
}
