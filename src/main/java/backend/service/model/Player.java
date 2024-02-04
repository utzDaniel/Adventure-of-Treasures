package backend.service.model;

import backend.service.enums.Direction;
import backend.service.interfaces.ICoordinate;

public final class Player {

    private final Move move;
    private MapGame currentMapGame;
    private final Inventory inventory;

    public Player(Move move, MapGame mapGame, Inventory inventory) {
        this.move = move;
        this.currentMapGame = mapGame;
        this.inventory = inventory;
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
