package backend.service.model;

import backend.service.enums.Direction;
import backend.service.interfaces.ICoordinate;

import java.util.Objects;

public final class Player {

    private static Player player = null;
    private final Move move;
    private MapGame currentMapGame;
    private final Inventory inventory;

    // TODO resolver isso depois
    public Player(Move move, MapGame mapGame, Inventory inventory) throws Exception {
        if (Objects.nonNull(player)) throw new Exception("Player já criado, use o Player.getInstance()");
        this.move = move;
        this.currentMapGame = mapGame;
        this.inventory = inventory;
        player = this;
    }

    public static synchronized Player getInstance() {
        return player;
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

    public void updateMove(Direction direction, ICoordinate coordinate) {
        this.move.update(direction, coordinate);
    }

    public String getImage() {
        return this.move.getImage();
    }

    public ICoordinate getCoordinate() {
        return this.move.getCoordinate();
    }

    public Direction getDirection() {
        return this.move.getDirection();
    }
}
