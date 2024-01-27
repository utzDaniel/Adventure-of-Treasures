package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IEventMapEntity;
import backend.repository.mapper.EventMapEntityMapper;
import backend.repository.util.Repository;

import java.util.*;

public final class EventMapRepository {
    private static EventMapRepository repository;
    private final Map<Integer, IEventMapEntity> map;

    public static EventMapRepository getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (EventMapRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new EventMapEntityMapper();
                var rep = new Repository<>(Filename.EVENT_MAP, mapper);
                var map = rep.create();
                repository = new EventMapRepository(map);
            }
        }
        return repository;
    }

    private EventMapRepository(Map<Integer, IEventMapEntity> map) {
        this.map = new HashMap<>(map);
    }

    public List<IEventMapEntity> getAll() {
        return this.map.values().stream().toList();
    }

    public Optional<IEventMapEntity> getByIdItem(int idItem) {
        return this.map.values()
                .stream()
                .filter(v -> v.idItem() == idItem)
                .findFirst();

    }

}
