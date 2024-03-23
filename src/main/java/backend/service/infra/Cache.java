package backend.service.infra;

import backend.repository.interfaces.IEntity;
import backend.service.interfaces.ICache;
import backend.service.interfaces.IFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class Cache<T extends IEntity> implements ICache<T> {

    private final Map<Integer, T> memory = new HashMap<>();
    private final IFactory<T> factory;

    public Cache(IFactory<T> factory) {
        this.factory = factory;
    }

    public Optional<T> get(int id) {
        return Objects.isNull(this.memory.get(id)) ? create(id) : Optional.of(memory.get(id));
    }

    private Optional<T> create(int id) {
        var model = factory.create(id);
        model.ifPresent(this::add);
        return model;
    }

    public void add(T model) {
        memory.put(model.id(), model);
    }
}
