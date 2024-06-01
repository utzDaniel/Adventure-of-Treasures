package backend.service.interfaces;

import backend.repository.interfaces.IEntity;

import java.util.Optional;

public interface IFactory<T extends IEntity, U extends IEntity> {

    Optional<T> create(int id);

    T create(U entity);
}
