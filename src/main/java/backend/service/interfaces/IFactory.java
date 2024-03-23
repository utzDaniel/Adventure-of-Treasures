package backend.service.interfaces;

import backend.repository.interfaces.IEntity;

import java.util.Optional;

public interface IFactory<T> {

    Optional<T> create(int id);

    T create(IEntity entity);
}
