package service;

import model.MovePlayer;
import model.Player;

import javax.swing.*;

public final class Walk {

    private final String direction;
    private final JLabel playerJLabel;
    private final Player player;
    private MovePlayer move;

    public Walk(String direction, JLabel playerJLabel, Player player) {
        this.direction = direction;
        this.playerJLabel = playerJLabel;
        this.player = player;
    }

    public boolean run (){
        this.move = moveToDirection();
        move();
        if (checkCanWalk()) setLocation();
        else comeBack();
        setDirection();
        setIcon();
        return true;
    }

    private MovePlayer moveToDirection(){
        for (MovePlayer move : MovePlayer.values()) {
            if(move.getDirection().equals(this.direction)){
                return move;
            }
        }
        return null;
    }

    private void move(){
        int newPositionX = this.player.getPositionPlayerX() + this.move.getToMoveX();
        int newPositionY = this.player.getPositionPlayerY() + this.move.getToMoveY();
        this.player.setPositionPlayerX(newPositionX, this.playerJLabel);
        this.player.setPositionPlayerY(newPositionY, this.playerJLabel);
    }

    private boolean checkCanWalk(){
        return this.player.getCurrentMap()
                .mapGameLimits(this.player.getPositionPlayerX(), this.player.getPositionPlayerY());
    }

    private void comeBack(){
        int newPositionX = this.player.getPositionPlayerX() - this.move.getToMoveX();
        int newPositionY = this.player.getPositionPlayerY() - this.move.getToMoveY();
        this.player.setPositionPlayerX(newPositionX, this.playerJLabel);
        this.player.setPositionPlayerY(newPositionY, this.playerJLabel);
    }

    private void setDirection(){
        this.player.setDirection(this.direction);
    }

    private void setLocation(){
        this.player.setLocation(this.playerJLabel);
    }

    private void setIcon(){
        this.player.setIcon(this.move.getImageIcon(), this.playerJLabel);
    }
}
