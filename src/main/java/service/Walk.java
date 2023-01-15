package service;

import exception.MoveException;
import model.Coordinate;
import model.Player;
import model.enums.MovePlayer;

public final class Walk {

    private final String direction;
    private final Player player;
    private MovePlayer move;

    public Walk(String direction, Player player) {
        this.direction = direction;
        this.player = player;
    }

    public boolean run (){
        moveToDirection();
        move();
        if (checkCanWalk()) setLocation();
        else comeBack();
        setDirection();
        setIcon();
        return true;
    }

    private void moveToDirection(){
        for (MovePlayer move : MovePlayer.values()) {
            if(move.getDirection().equals(this.direction)){
                this.move = move;
                return;
            }
        }
        throw new MoveException("Direção invalida!");
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
