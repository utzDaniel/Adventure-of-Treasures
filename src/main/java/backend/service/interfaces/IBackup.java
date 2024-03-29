package backend.service.interfaces;

import backend.service.memento.IMemento;

public interface IBackup<T extends IMemento> {

    T save();

    void restore(T memento);

}
