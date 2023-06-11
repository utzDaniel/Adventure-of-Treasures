package backend.service.model;

import backend.controller.interfaces.ICoordinate;
import backend.service.model.builder.Item;
import backend.service.model.builder.MapGame;
import backend.service.component.DropItem;
import backend.service.component.LookItem;
import backend.service.component.TakeItem;
import backend.service.component.Walk;

import javax.swing.*;
import java.util.Objects;

public final class Player {

    private static Player player = new Player();
    private String direction;
    private MapGame currentMapGame;
    private final Inventory inventory;
    private ICoordinate coordinate;
    private ImageIcon icon;

    private Player() {
        this.currentMapGame = null;
        this.icon = new ImageIcon("src/main/resources/player/baixo_C.png");
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
        return ICoordinate.getInstance(this.coordinate);
    }

    public void setLocation(ICoordinate coordinate) {
        this.coordinate = ICoordinate.getInstance(coordinate);
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

    //TODO ao pegar um item em um local que não deveria ter como o player passar, está podendeo passar. EX: pegar o item papel, na mesa
    public boolean takeItem(Item item) {
        return new TakeItem(this, item).run();
    }

    public boolean dropItem(String name) {
        Item item = this.getInventory().getItemVisible().stream()
                .filter(item1 -> item1.getName().equals(name)).findFirst().get();
        return new DropItem(this, item).run();
    }

    public ImageIcon getIcon() {
        return this.icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}
