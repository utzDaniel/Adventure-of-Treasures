package backend.model;

import backend.model.builder.item.Item;
import backend.model.builder.map.MapGame;
import rules.interfaces.ICoordinate;
import rules.service.DropItem;
import rules.service.LookItem;
import rules.service.TakeItem;
import rules.service.Walk;

import javax.swing.*;
import java.util.Objects;

public final class Player {

    private static Player player;
    private String direction;
    private MapGame currentMapGame;
    private final Inventory inventory;
    private ICoordinate coordinate;
    private ImageIcon icon;

    private Player() {
        this.currentMapGame = null;
        this.inventory = new Inventory();
        this.coordinate = ICoordinate.getInstance(300, 470);
    }

    public static synchronized Player getInstance() {
        if (Objects.isNull(player)) {
            player = new Player();
        }
        return player;
    }

    public ICoordinate getLocation() {
        return ICoordinate.getInstance(this.coordinate.getPoint());
    }

    public void setLocation(ICoordinate coordinate) {
        this.coordinate = ICoordinate.getInstance(coordinate.getPoint());
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

    public ImageIcon getIcon() {
        return this.icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}
