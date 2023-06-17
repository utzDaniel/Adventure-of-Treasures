package backend.service.model;

import backend.service.interfaces.ICoordinate;
import backend.service.model.builder.MapGame;

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

    public ImageIcon getIcon() {
        return this.icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
}
