package model;

import service.DropItem;
import service.LookItem;
import service.TakeItem;
import service.Walk;
import settings.SettingsPlayer;

import javax.swing.*;

public final class Player {
    private int positionPlayerX;
    private int positionPlayerY;
    private String direction;
    private MapGame currentMapGame;
    private final Inventory inventory;

    private final JLabel jLabel;

    public Player() {
        SettingsPlayer settingsPlayer = new SettingsPlayer();
        this.positionPlayerX = settingsPlayer.positionInitialX();
        this.positionPlayerY = settingsPlayer.positionInitialY();
        this.currentMapGame = null;
        this.inventory = new Inventory();
        this.jLabel = new JLabel();
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public JLabel getJLabel() {
        return this.jLabel;
    }

    public int getPositionPlayerX() {
        return this.positionPlayerX;
    }

    public int getPositionPlayerY() {
        return this.positionPlayerY;
    }

    public void setPositionPlayerX(int positionPlayerX) {
        this.positionPlayerX = positionPlayerX;
        setLocation();
    }

    public void setPositionPlayerY(int positionPlayerY) {
        this.positionPlayerY = positionPlayerY;
        setLocation();
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setIcon(ImageIcon imageIcon) {
        this.jLabel.setIcon(imageIcon);
    }

    public void setLocation() {
        this.jLabel.setLocation(this.positionPlayerX, this.positionPlayerY);
    }

    public void setCurrentMap(MapGame currentScenery) {
        this.currentMapGame = currentScenery;
    }

    public MapGame getCurrentMap() {
        return this.currentMapGame;
    }

    public void walk(String direction) {
        new Walk(direction, this).run();
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
