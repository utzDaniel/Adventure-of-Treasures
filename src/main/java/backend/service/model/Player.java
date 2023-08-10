package backend.service.model;

import backend.service.enums.Move;
import backend.service.interfaces.ICoordinate;

import java.util.Objects;

public final class Player {

    private static Player player = null;
    private Move move;
    private MapGame currentMapGame;
    private final Inventory inventory;
    private ICoordinate coordinate;
    private String image;

    // TODO resolver isso depois
    public Player(String image, ICoordinate coordinate, MapGame mapGame, Inventory inventory) throws Exception {
        if(Objects.nonNull(player)) throw new Exception("Player já criado, use o Player.getInstance()");
        this.image = image;
        this.coordinate = coordinate;
        this.currentMapGame = mapGame;
        this.inventory = inventory;
        player = this;
    }

    public static synchronized Player getInstance() {
        return player;
    }

    public ICoordinate getLocation() {
        return ICoordinate.getInstance(this.coordinate);
    }

    public void setLocation(ICoordinate coordinate) {
        this.coordinate = ICoordinate.getInstance(coordinate);
    }

    public Move getMove() {
        return this.move;
    }

    public void setMove(Move move) {
        this.move = move;
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

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
