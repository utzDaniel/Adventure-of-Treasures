package service;

import model.*;
import model.enums.MovePlayer;

public class AddItemMapGame {

    private final Item item;
    private final Player player;
    private final Coordinate coordinate;
    private int multiply;
    private final MapGame mapGame;

    public AddItemMapGame(Item item, Player player) {
        this.item = item;
        this.player = player;
        this.coordinate = new Coordinate(0,0);
        this.multiply = 0;
        this.mapGame = player.getCurrentMap();
    }

    public void run(){
        do{
            this.multiply++;
        }while (!checkDirectionDropItem());
        setItemNewCoordinate();
        addItem();
    }

    private boolean checkDirectionDropItem(){
        for (MovePlayer move : MovePlayer.values()) {
            this.coordinate.setAxisX(this.player.getPositionPlayerX() + (move.getToMoveX() * this.multiply));
            this.coordinate.setAxisY(this.player.getPositionPlayerY() + (move.getToMoveY() * this.multiply));
            if(checkCoordinateDropItem()) return true;
        }
        return false;//TODO criar uma exception, pode ficar em loop, criar um algoritmo melhor de dropar item
    }

    private boolean checkCoordinateDropItem(){
        return this.mapGame.checkLimits(this.coordinate);
    }

    private void setItemNewCoordinate(){
        this.item.setPositionItemX(this.coordinate.getAxisX()*10);// position X item * STEP = 10
        this.item.setPositionItemY(this.coordinate.getAxisY()*10);// position Y item * STEP = 10
    }

    private void addItem(){
        this.mapGame.setItem(item);
    }

}
