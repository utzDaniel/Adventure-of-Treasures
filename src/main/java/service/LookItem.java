package service;

import model.Item;
import model.MovePlayer;
import model.Player;

public final class LookItem {

    private final Player player;
    private MovePlayer move;


    public LookItem(Player player) {
        this.player = player;
    }

    public Item run(){
        this.move = lookToDirection();
        return getItemCurrentMap();

    }

    private MovePlayer lookToDirection(){
        for (MovePlayer move : MovePlayer.values()) {
            if(move.getDirection().equals(this.player.getDirection())){
                return move;
            }
        }
        return null;
    }

    private Item getItemCurrentMap(){
        int newPositionX = this.player.getPositionPlayerX() + this.move.getToMoveX();
        int newPositionY = this.player.getPositionPlayerY() + this.move.getToMoveY();
        return this.player.getCurrentMap().getItemMapGame(newPositionX, newPositionY);
    }

}
