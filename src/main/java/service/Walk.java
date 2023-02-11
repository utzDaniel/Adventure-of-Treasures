package service;

import exception.MoveException;
import model.Coordinate;
import model.Player;
import model.enums.MovePlayer;

import java.util.Arrays;

public final class Walk {

    private final String direction;
    private final Player player;
    private MovePlayer move;

    public Walk(String direction) {
        this.direction = direction;
        this.player = Player.getInstance();
    }

    public boolean run (){
        moveToDirection();
        move();
        this.move.run();
        if (checkCanWalk()) setLocation();
        else comeBack();
        setDirection();
        setIcon();
        return true;
    }

    private void moveToDirection(){
        this.move = Arrays.stream(MovePlayer.values())
                .filter(movePlayer -> movePlayer.getDirection().equals(this.direction))
                .findFirst().orElseThrow(() -> new MoveException("Direção invalida!"));
    }

    private void move(){
        int newPositionX = this.player.getPositionPlayerX() + this.move.getToMoveX();
        int newPositionY = this.player.getPositionPlayerY() + this.move.getToMoveY();
        this.player.setPositionPlayerX(newPositionX);
        this.player.setPositionPlayerY(newPositionY);
    }

    private boolean checkCanWalk(){
        return this.player.getCurrentMap()
                .checkLimits( new Coordinate(this.player.getPositionPlayerX(),this.player.getPositionPlayerY()));
    }

    private void comeBack(){
        int newPositionX = this.player.getPositionPlayerX() - this.move.getToMoveX();
        int newPositionY = this.player.getPositionPlayerY() - this.move.getToMoveY();
        this.player.setPositionPlayerX(newPositionX);
        this.player.setPositionPlayerY(newPositionY);
    }

    private void setDirection(){
        this.player.setDirection(this.direction);
    }

    private void setLocation(){
        this.player.setLocation();
    }

    private void setIcon(){
        this.player.setIcon(this.move.getImageIcon());
    }
}
