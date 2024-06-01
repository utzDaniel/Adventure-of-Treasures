package backend.service.interfaces;

import backend.repository.interfaces.IEntity;

public interface IMemento<T extends IFlyweight> extends IEntity {

    T save();

    void restore(T flyweight);

}
