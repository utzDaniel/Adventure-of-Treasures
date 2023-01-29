package service;

import model.*;
import model.builder.item.Item;
import model.builder.map.MapGame;
import model.enums.MovePlayer;

public class AddItemMapGame {

    private final Item item;
    private final Player player;
    private final Coordinate coordinate;
    private int multiply;
    private final MapGame mapGame;

    public AddItemMapGame(Item item) {
        this.item = item;
        this.player = Player.getInstance();
        this.coordinate = new Coordinate(0,0);
        this.multiply = 0;
        this.mapGame = player.getCurrentMap();
    }

    public boolean run(){
        do{
            this.multiply++;
        }while (!checkDirectionDropItem());
        setItemNewCoordinate();
        return true;
    }

    private boolean checkDirectionDropItem(){
        int x,y;
        for (MovePlayer move : MovePlayer.values()) {
            x = this.player.getPositionPlayerX() + (move.getToMoveX() * this.multiply);
            y = this.player.getPositionPlayerY() + (move.getToMoveY() * this.multiply);
            this.coordinate.setAxisX(x);
            this.coordinate.setAxisY(y);
            if(x >= 10 && y >= 10 && x <= 770 && y <= 550 && checkCoordinateDropItem()) return true;
            if(this.multiply == 20) break;
        }
        return false;//TODO criar uma exception, pode ficar em loop, criar um algoritmo melhor de dropar item
    }

    private boolean checkCoordinateDropItem(){
        return this.mapGame.checkLimits(this.coordinate);
    }

    private void setItemNewCoordinate(){
        this.item.setPositionX(this.coordinate.getAxisX()*10);// position X item * STEP = 10
        this.item.setPositionY(this.coordinate.getAxisY()*10);// position Y item * STEP = 10
    }
}
