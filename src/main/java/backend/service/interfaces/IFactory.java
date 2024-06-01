package backend.service.interfaces;

import backend.repository.interfaces.IEntity;

import java.util.Optional;

public interface IFactory<T extends IMemento<K>, U extends IEntity, K extends IFlyweight> {

    Optional<T> create(int id);

    T create(U entity);
}
