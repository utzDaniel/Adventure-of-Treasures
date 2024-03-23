package backend.service.interfaces;

import backend.repository.interfaces.IEntity;

import java.util.Optional;

public interface ICache<T extends IEntity> {

    Optional<T> get(int id);

    void add(T model);

}
