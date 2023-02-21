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

    private Coordinate coordinate;

    private Player() {
        this.currentMapGame = null;
        this.inventory = new Inventory();
        this.jLabel = new JLabel();
        settingsPlayer();
        this.coordinate = new Coordinate(this.jLabel.getLocation());
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

    //TODO devolver uma nova referencia
    public Coordinate getLocation() {
        return this.coordinate;
    }

    public void setLocation(Coordinate coordinate) {
        this.coordinate.setLocation(coordinate);
        this.jLabel.setLocation(this.coordinate.getPoint());
    }

    public String getDirection() {
        return this.direction;
    }

    //TODO refatorar os teste, pois o setDirection usa so em teste agora
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
        var imageIcon = new Walk(direction).run();
        this.direction = direction;
        this.jLabel.setIcon(imageIcon);
    }

    public Item lookItem() {
        Coordinate coordinate = new LookItem(this.direction, this.getLocation()).run();
        return this.getCurrentMap().getItem(coordinate);
    }

    public boolean takeItem(Item item) {
        return new TakeItem(item).run();
    }

    public boolean dropItem(Item item) {
        return new DropItem(item).run();
    }
}
