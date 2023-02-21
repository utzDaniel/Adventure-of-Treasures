package service;

import exception.MoveException;
import model.Coordinate;
import model.Player;
import model.enums.MovePlayer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public final class Walk {

    private final String direction;
    private final Player player;
    private MovePlayer move;

    public Walk(String direction) {
        this.direction = direction;
        this.player = Player.getInstance();
    }

    public ImageIcon run() {
        moveToDirection();
        move();
        this.move.run();
        if (!checkCanWalk()) comeBack();
        return this.move.getImageIcon();
    }

    private void moveToDirection() {
        this.move = Arrays.stream(MovePlayer.values())
                .filter(movePlayer -> movePlayer.getDirection().equals(this.direction))
                .findFirst().orElseThrow(() -> new MoveException("Direção invalida!"));
    }

    private void move() {
        int newPositionX = this.player.getLocation().x + this.move.getToMoveX();
        int newPositionY = this.player.getLocation().y + this.move.getToMoveY();
        this.player.setLocation(new Point(newPositionX, newPositionY));
    }

    private boolean checkCanWalk() {
        return this.player.getCurrentMap()
                .checkLimits(new Coordinate(this.player.getLocation()));
    }

    private void comeBack() {
        int newPositionX = this.player.getLocation().x - this.move.getToMoveX();
        int newPositionY = this.player.getLocation().y - this.move.getToMoveY();
        this.player.setLocation(new Point(newPositionX, newPositionY));
    }
}
