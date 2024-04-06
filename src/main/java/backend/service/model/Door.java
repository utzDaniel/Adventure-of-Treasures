package backend.service.model;

import backend.repository.interfaces.IDoorEntity;
import backend.repository.interfaces.IEntity;
import backend.service.interfaces.IBackup;
import backend.service.interfaces.ICoordinate;
import backend.service.memento.DoorMemento;

public final class Door implements IEntity, IBackup<DoorMemento> {
    private final IDoorEntity entity;
    private boolean open;

    public Door(IDoorEntity entity) {
        this.entity = entity;
        this.open = entity.open();
    }

    @Override
    public int id() {
        return this.entity.id();
    }

    public ICoordinate getCoordinate() {
        return ICoordinate.getInstance(this.entity.insideX(), this.entity.insideY());
    }

    public int getIdMapOutside() {
        return this.entity.idMapOutside();
    }

    public ICoordinate getCoordinateOutside() {
        return ICoordinate.getInstance(this.entity.outsideX(), this.entity.outsideY());
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return this.open;
    }

    public boolean isDoor(ICoordinate coordinate) {
        return ICoordinate.getInstance(this.entity.insideX(), this.entity.insideY()).equals(coordinate);
    }

    public boolean isMap(int idMapGame) {
        return this.entity.idMapOutside() == idMapGame;
    }

    @Override
    public String toString() {
        return """
                {
                    "id": "%d",
                    "coordinate": %s,
                    "idMapOutside": "%d",
                    "coordinateOutside": %s,
                    "open": %b
                }
                """.formatted(this.entity.id(), ICoordinate.getInstance(this.entity.insideX(), this.entity.insideY()).toString(),
                this.entity.idMapOutside(), ICoordinate.getInstance(this.entity.outsideX(), this.entity.outsideY()).toString(),
                this.open);
    }

    @Override
    public DoorMemento save() {
        return new DoorMemento(this.entity.id(), this.open);
    }

    @Override
    public void restore(DoorMemento memento) {
        this.open = memento.open();
    }
}
