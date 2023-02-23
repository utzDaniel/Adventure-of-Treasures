package model;

import model.builder.item.Item;
import model.builder.map.MapGame;
import service.DropItem;
import service.LookItem;
import service.TakeItem;
import service.Walk;
import settings.SettingsPlayer;

import javax.swing.*;
import java.util.Objects;

public final class Player {

    private static Player player;
    private String direction;
    private MapGame currentMapGame;
    private final Inventory inventory;
    private final JLabel jLabel;

    private Player() {
        this.currentMapGame = null;
        this.inventory = new Inventory();
        this.jLabel = new JLabel();
        settingsPlayer();
    }

    public static synchronized Player getInstance() {
        if (Objects.isNull(player)) {
            player = new Player();
        }
        return player;
    }

    private void settingsPlayer() {
        SettingsPlayer settingsPlayer = new SettingsPlayer();
        this.jLabel.setIcon(settingsPlayer.getIcon());
        this.jLabel.setName(settingsPlayer.getName());
        this.jLabel.setBounds(settingsPlayer.getRectangle());
    }

    public JLabel getJLabel() {
        return this.jLabel;
    }

    public Coordinate getLocation() {
        return new Coordinate(this.jLabel.getLocation());
    }

    public void setLocation(Coordinate coordinate) {
        this.jLabel.setLocation(coordinate.getPoint());
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
        new Walk(this, direction).run();
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
