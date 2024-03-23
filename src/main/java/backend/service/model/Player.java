package backend.service.model;

import backend.service.enums.Direction;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IMove;


public final class Player implements IMove {

    private final IMove move;
    private final Inventory inventory;

    public Player(IMove move, Inventory inventory) {
        this.move = move;
        this.inventory = inventory;
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

    @Override
    public String extrinsic() {
        return """
                %s
                %s
                """.formatted(this.move.extrinsic(), this.inventory.extrinsecos())
                .replace("\n", "");
    }


}
