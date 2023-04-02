package backend.model;

import rules.interfaces.ICoordinate;
import rules.service.DropItem;
import rules.service.LookItem;
import rules.service.TakeItem;
import rules.service.Walk;
import backend.model.builder.item.Item;
import backend.model.builder.map.MapGame;
import frontend.settings.SettingsPlayer;

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

    //TODO remover o JLabel, é resposabilidade da view
    private void settingsPlayer() {
        SettingsPlayer settingsPlayer = new SettingsPlayer();
        this.jLabel.setIcon(settingsPlayer.getIcon());
        this.jLabel.setName(settingsPlayer.getName());
        this.jLabel.setBounds(settingsPlayer.getRectangle());
    }

    public JLabel getJLabel() {
        return this.jLabel;
    }

    public ICoordinate getLocation() {
        return ICoordinate.getInstance(this.jLabel.getLocation());
    }

    public void setLocation(ICoordinate coordinate) {
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
