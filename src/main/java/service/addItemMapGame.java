package service;

import model.Item;
import model.MapGame;
import model.Player;

import static model.MovePlayer.STEP;

public class addItemMapGame {

    private final Item item;
    private int positionX;
    private int positionY;
    private MapGame mapGame;

    public addItemMapGame(Item item, Player player) {
        this.item = item;
        this.positionX = player.getPositionPlayerX();
        this.positionY = player.getPositionPlayerY();
        this.mapGame = player.getCurrentMap();
    }

    public void run(){

    }


    public void addItem() {
        if (mapGame.mapGameLimits(positionX + STEP, positionY)) {
            item.setPositionItemX(positionX + STEP);
            item.setPositionItemY(positionY);
        } else if (mapGame.mapGameLimits(positionX - STEP, positionY)) {
            item.setPositionItemX(positionX - STEP);
            item.setPositionItemY(positionY);
        } else if (mapGame.mapGameLimits(positionX, positionY + STEP)) {
            item.setPositionItemX(positionX);
            item.setPositionItemY(positionY + STEP);
        } else {
            item.setPositionItemX(positionX);
            item.setPositionItemY(positionY - STEP);
        }
        mapGame.setItem(item);
    }


}
