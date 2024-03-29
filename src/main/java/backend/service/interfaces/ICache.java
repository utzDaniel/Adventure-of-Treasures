package backend.service.interfaces;

import backend.repository.interfaces.IEntity;

import java.util.Collection;
import java.util.Optional;

public interface ICache<T extends IEntity> {

    Optional<T> get(int id);

    Collection<T> getAll();

    void add(T model);

    void clear();

}
