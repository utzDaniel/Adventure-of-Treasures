package backend.service.interfaces;

import java.util.Collection;
import java.util.Optional;

public interface ICache<T extends IMemento<K>, K extends IFlyweight> {

    Optional<T> get(int id);

    Collection<T> getAll();

    void add(T model);

    void clear();

}
