package service;

import exception.MoveException;
import model.Coordinate;
import model.builder.item.Item;
import model.enums.MovePlayer;
import model.Player;

import java.util.Arrays;

public final class LookItem {

    private final Player player;
    private MovePlayer move;
    private Coordinate coordinate;


    public LookItem() {
        this.player = Player.getInstance();
        this.coordinate = new Coordinate(0,0);
    }

    public Item run(){
        lookToDirection();
        setCoordinate();
        return getItemMapGame();

    }

    private void lookToDirection(){
        this.move =  Arrays.stream(MovePlayer.values())
                .filter(move -> move.getDirection().equals(this.player.getDirection()))
                .findFirst().orElseThrow(() -> new MoveException("Direção invalida!"));
    }

    private void setCoordinate(){
        int newPositionX = this.player.getLocation().x+ this.move.getToMoveX();
        int newPositionY = this.player.getLocation().y + this.move.getToMoveY();
        this.coordinate.setAxisX(newPositionX);
        this.coordinate.setAxisY(newPositionY);
    }

    private Item getItemMapGame() {
        return this.player.getCurrentMap().getItem(this.coordinate);
    }
}
