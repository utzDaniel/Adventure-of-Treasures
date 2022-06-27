package service;

import model.Coordinate;
import model.Item;
import model.MovePlayer;
import model.Player;

public final class LookItem {

    private final Player player;
    private MovePlayer move;
    private Coordinate coordinate;


    public LookItem(Player player) {
        this.player = player;
        this.coordinate = new Coordinate(0,0);
    }

    public Item run(){
        lookToDirection();
        setCoordinate();
        return getItemMapGame();

    }

    private void lookToDirection(){
        for (MovePlayer move : MovePlayer.values()) {
            if(move.getDirection().equals(this.player.getDirection())){
                this.move = move;
            }
        }
    }

    private void setCoordinate(){
        int newPositionX = this.player.getPositionPlayerX() + this.move.getToMoveX();
        int newPositionY = this.player.getPositionPlayerY() + this.move.getToMoveY();
        this.coordinate.setEixoX(newPositionX);
        this.coordinate.setEixoY(newPositionY);
    }

    private Item getItemMapGame() {
        return this.player.getCurrentMap().getItem(this.coordinate);
    }
}
