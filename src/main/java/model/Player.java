package model;

import service.DropItem;
import service.LookItem;
import service.TakeItem;
import service.Walk;

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

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setIcon(ImageIcon imageIcon, JLabel playerJLabel) {
        playerJLabel.setIcon(imageIcon);
    }

    public void setLocation(JLabel playerJLabel) {
        playerJLabel.setLocation(this.positionPlayerX, this.positionPlayerY);
    }

    public void setCurrentMap(MapGame currentScenery) {
        this.currentMapGame = currentScenery;
    }

    public MapGame getCurrentMap() {
        return this.currentMapGame;
    }

    public void walk(String direction, JLabel playerJLabel) {
        new Walk(direction, playerJLabel, this).run();
    }

    public Item lookItem() {
        return new LookItem(this).run();
    }

    public boolean takeItem(Item item) {
        return new TakeItem(this, item).run();
    }

    public boolean dropItem(Item item) {
        return new DropItem(this, item).run();
    }
}
