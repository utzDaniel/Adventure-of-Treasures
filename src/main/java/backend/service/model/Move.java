package backend.service.model;

import backend.service.enums.Direction;
import backend.service.enums.MovementImage;
import backend.service.interfaces.ICoordinate;

public final class Move {
    private MovementImage movementImage;
    private Direction direction;
    private ICoordinate coordinate;
    private final String path;

    public Move(String path, ICoordinate coordinate) {
        this.path = path;
        this.movementImage = MovementImage.RIGHT_FOOT_TOGETHER;
        this.direction = Direction.SUL;
        this.coordinate = coordinate;
    }

    public void update(Direction direction, ICoordinate coordinate) {
        this.movementImage = direction.equals(this.direction) ? this.movementImage.next() : MovementImage.reset();
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }


    public String getImage() {
        return String.format("%s/%s_%s.png", this.path, this.direction.getFileName(), this.movementImage.getCode());
    }

    public ICoordinate getCoordinate() {
        return ICoordinate.getInstance(this.coordinate);
    }

}

