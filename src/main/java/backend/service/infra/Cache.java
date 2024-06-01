package backend.service.infra;

import backend.repository.interfaces.IEntity;
import backend.service.interfaces.ICache;
import backend.service.interfaces.IFactory;
import backend.service.interfaces.IFlyweight;
import backend.service.interfaces.IMemento;

import java.util.*;

public final class Cache<T extends IMemento<K>, U extends IEntity, K extends IFlyweight> implements ICache<T, K> {

    private final Map<Integer, T> memory = new HashMap<>();
    private final IFactory<T, U, K> factory;

    public Cache(IFactory<T, U, K> factory) {
        this.factory = factory;
    }

    public Optional<T> get(int id) {
        return Objects.isNull(this.memory.get(id)) ? create(id) : Optional.of(this.memory.get(id));
    }

    public Collection<T> getAll() {
        return this.memory.values();
    }

    private Optional<T> create(int id) {
        var model = factory.create(id);
        model.ifPresent(this::add);
        return model;
    }

    public void add(T model) {
        this.memory.put(model.id(), model);
    }

    @Override
    public void clear() {
        this.memory.clear();
    }

}
