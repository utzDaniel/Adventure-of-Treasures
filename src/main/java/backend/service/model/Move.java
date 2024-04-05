package backend.service.model;

import backend.service.enums.Direction;
import backend.service.enums.MovementImage;
import backend.service.infra.CacheService;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IMove;
import backend.service.interfaces.IBackup;
import backend.service.memento.MoveMemento;

public final class Move implements IMove, IBackup<MoveMemento> {
    private MovementImage movementImage;
    private MapGame currentMapGame;
    private Direction direction;
    private ICoordinate coordinate;
    private String path;

    public Move(String path, ICoordinate coordinate, MapGame mapGame) {
        this.path = path;
        this.movementImage = MovementImage.RIGHT_FOOT_TOGETHER;
        this.direction = Direction.SOUTH;
        this.coordinate = coordinate;
        this.currentMapGame = mapGame;
    }

    @Override
    public void updateMove(Direction direction, ICoordinate coordinate) {
        this.movementImage = direction.equals(this.direction) ? this.movementImage.next() : MovementImage.reset();
        this.coordinate = coordinate;
        this.direction = direction;
    }

    @Override
    public MapGame getCurrentMap() {
        return this.currentMapGame;
    }

    @Override
    public void setCurrentMap(MapGame currentScenery) {
        this.currentMapGame = currentScenery;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public String getImage() {
        return String.format("%s/%s_%s.png", this.path, this.direction.getFileName(), this.movementImage.getCode());
    }

    @Override
    public ICoordinate getCoordinate() {
        return ICoordinate.getInstance(this.coordinate);
    }

    @Override
    public MoveMemento save() {
        return new MoveMemento(this.movementImage.name(), this.currentMapGame.id(),
                this.direction.name(), this.coordinate.x(), this.coordinate.y(), this.path);
    }

    @Override
    public void restore(MoveMemento memento) {
        this.path = memento.path();
        this.movementImage = Enum.valueOf(MovementImage.class, memento.movementImage());
        this.direction = Enum.valueOf(Direction.class, memento.direction());
        this.coordinate = ICoordinate.getInstance(memento.x(), memento.y());
        this.currentMapGame = CacheService.getMapGame(memento.idMapGame()).orElse(null);
    }
}

