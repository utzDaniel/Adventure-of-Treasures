package backend.service.model;

import backend.repository.interfaces.IEntity;

public final class Flyweight implements IEntity {

    private final IEntity entity;

    public Flyweight(IEntity entity) {
        this.entity = entity;
    }

    @Override
    public int id() {
        return entity.id();
    }


}
