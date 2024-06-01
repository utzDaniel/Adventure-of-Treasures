package backend.service.model;

import backend.service.enums.Direction;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IMemento;
import backend.service.interfaces.IMove;
import backend.service.memento.PlayerMemento;

public final class Player implements IMove, IMemento<PlayerMemento> {

    private final IMove move;
    private final Inventory inventory;

    public Player(IMove move, Inventory inventory) {
        this.move = move;
        this.inventory = inventory;
    }

    @Override
    public int id() {
        return 0;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public void updateMove(Direction direction, ICoordinate coordinate) {
        this.move.updateMove(direction, coordinate);
    }

    @Override
    public MapGame getCurrentMap() {
        return this.move.getCurrentMap();
    }

    @Override
    public void setCurrentMap(MapGame currentScenery) {
        this.move.setCurrentMap(currentScenery);
    }

    @Override
    public String getImage() {
        return this.move.getImage();
    }

    @Override
    public ICoordinate getCoordinate() {
        return this.move.getCoordinate();
    }

    @Override
    public Direction getDirection() {
        return this.move.getDirection();
    }

    public PlayerMemento save() {
        var moveMemento = ((Move) this.move).save();
        var inventoryMemento = this.inventory.save();
        return new PlayerMemento(moveMemento, inventoryMemento);
    }

    public void restore(PlayerMemento memento) {
        ((Move) this.move).restore(memento.move());
        this.inventory.restore(memento.inventory());
    }

}
