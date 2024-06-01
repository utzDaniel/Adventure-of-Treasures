package backend.service.model;

import backend.repository.interfaces.IDoorEntity;
import backend.repository.singleton.DoorRepository;
import backend.service.interfaces.IFactory;
import backend.service.memento.DoorMemento;

import java.util.Optional;

public final class DoorFactory implements IFactory<Door, IDoorEntity, DoorMemento> {

    @Override
    public Optional<Door> create(int id) {
        return getEntity(id).map(this::create);
    }

    @Override
    public Door create(IDoorEntity entity) {
        return new Door(entity);
    }

    private Optional<IDoorEntity> getEntity(int id) {
        return DoorRepository.getInstance().getById(id);
    }

}
