package backend.service.model;

import backend.repository.interfaces.IDoorEntity;
import backend.repository.interfaces.IEntity;
import backend.service.interfaces.ICoordinate;
import backend.service.memento.DoorMemento;
import backend.service.interfaces.IBackup;

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

    public int getId() {
        return this.entity.id();
    }

    public int getIdMapGame() {
        return this.entity.idMapDor();
    }

    public ICoordinate getCoordinate() {
        return ICoordinate.getInstance(this.entity.positionX(), this.entity.positionY());
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return this.open;
    }

    public boolean isDoor(ICoordinate coordinate) {
        return ICoordinate.getInstance(this.entity.positionX(), this.entity.positionY()).equals(coordinate);
    }

    public boolean isMap(int idMapGame) {
        return this.entity.idMapDor() == idMapGame;
    }

    @Override
    public String toString() {
        return """
                {
                    "id": "%d",
                    "idMapGame": "%d",
                    "coordinate": %s,
                    "open": %b
                }
                """.formatted(this.entity.id(), this.entity.idMapDor(),
                ICoordinate.getInstance(this.entity.positionX(), this.entity.positionY()).toString(), this.open);
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
