package model;

import service.DropItem;
import service.LookItem;
import service.TakeItem;
import service.Walk;
import settings.SettingsPlayer;

import javax.swing.*;
import java.util.Objects;

public final class Player {

    private static Player player;
    private int positionPlayerX;
    private int positionPlayerY;
    private String direction;
    private MapGame currentMapGame;
    private final Inventory inventory;
    private final JLabel jLabel;

    private Player() {
        SettingsPlayer settingsPlayer = new SettingsPlayer();
        this.positionPlayerX = settingsPlayer.getRectangle().x;
        this.positionPlayerY = settingsPlayer.getRectangle().y;
        this.currentMapGame = null;
        this.inventory = new Inventory();
        this.jLabel = new JLabel();
    }

    public JLabel getJLabel() {
        return this.jLabel;
    }

    public void setLocation() {
        this.jLabel.setLocation(this.positionPlayerX, this.positionPlayerY);
    }

    public void setIcon(ImageIcon imageIcon) {
        this.jLabel.setIcon(imageIcon);
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

    public static Player getInstance() {
        if (Objects.isNull(player)) {
            player = new Player();
        }
        return player;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public MapGame getCurrentMap() {
        return this.currentMapGame;
    }

    public void setCurrentMap(MapGame currentScenery) {
        this.currentMapGame = currentScenery;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void walk(String direction) {
        new Walk(direction).run();
    }

    public Item lookItem() {
        return new LookItem().run();
    }

    public boolean takeItem(Item item) {
        return new TakeItem(item).run();
    }

    public boolean dropItem(Item item) {
        return new DropItem(item).run();
    }
}
