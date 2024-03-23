package backend.service.model;

import backend.repository.interfaces.IDoorEntity;
import backend.repository.interfaces.IEntity;
import backend.repository.singleton.DoorRepository;
import backend.service.interfaces.IFactory;

import java.util.Optional;

public final class DoorFactory implements IFactory<Door> {

    @Override
    public Optional<Door> create(int id) {
        return getEntity(id).map(this::create);
    }

    @Override
    public Door create(IEntity entity) {
        if (entity instanceof IDoorEntity doorEntity) {
            return new Door(doorEntity);
        }
        return null;
    }

    private Optional<IDoorEntity> getEntity(int id) {
        return DoorRepository.getInstance().getById(id);
    }

}
