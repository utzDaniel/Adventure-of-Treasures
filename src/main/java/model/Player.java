package model;

import service.DropItem;
import service.TakeItem;

import javax.swing.*;

public final class Player {
    private int positionPlayerX;
    private int positionPlayerY;
    private String direction;
    private MapGame currentMapGame;
    private final Inventory inventory;

    public Player() {
        SettingsPlayer settingsPlayer = new SettingsPlayer();
        this.positionPlayerX = settingsPlayer.positionInitialX();
        this.positionPlayerY = settingsPlayer.positionInitialY();
        this.currentMapGame = null;
        this.inventory = new Inventory();
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public int getPositionPlayerX() {
        return this.positionPlayerX;
    }

    public void setPositionPlayerX(int positionPlayerX, JLabel playerJLabel) {
        this.positionPlayerX = positionPlayerX;
        setLocation(playerJLabel);
    }

    public int getPositionPlayerY() {
        return this.positionPlayerY;
    }

    public void setPositionPlayerY(int positionPlayerY, JLabel playerJLabel) {
        this.positionPlayerY = positionPlayerY;
        setLocation(playerJLabel);
    }

    private String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean walk(String direction, JLabel playerJLabel) {
        for (MovePlayer move : MovePlayer.values()) {
            if(move.getDirection().equals(direction)){
                this.positionPlayerX += move.getToMoveX();
                this.positionPlayerY += move.getToMoveY();
                if (!this.currentMapGame.mapGameLimits(this.positionPlayerX, this.positionPlayerY)) {
                    this.positionPlayerX -= move.getToMoveX();
                    this.positionPlayerY -= move.getToMoveY();
                }else{
                    setLocation(playerJLabel);
                }
                setDirection(direction);
                setIcon(move.getImageIcon(), playerJLabel);
                return true;
            }
        }
        return false;
    }

    //colocar junto ao take ou deixar privado
    public Item getItemMapGame() {
        int positionX = this.positionPlayerX;
        int positionY = this.positionPlayerY;
        for (MovePlayer move : MovePlayer.values()) {
            if (move.getDirection().equals(getDirection())) {
                positionX += move.getToMoveX();
                positionY += move.getToMoveY();
                return this.currentMapGame.getItemMapGame(positionX, positionY);
            }
        }
        return null;
    }

    private void setIcon(ImageIcon imageIcon, JLabel playerJLabel) {
        playerJLabel.setIcon(imageIcon);
    }

    private void setLocation(JLabel playerJLabel) {
        playerJLabel.setLocation(this.positionPlayerX, this.positionPlayerY);
    }

    public void setCurrentMap(MapGame currentScenery) {
        this.currentMapGame = currentScenery;
    }

    public MapGame getCurrentMap() {
        return this.currentMapGame;
    }

    public boolean takeItem(Item item) {
        return new TakeItem(this, item).run();
    }

    public boolean dropItem(Item item) {
        return new DropItem(this,item).run();
    }
}
