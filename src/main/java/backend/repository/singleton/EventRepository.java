package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IEventEntity;
import backend.repository.mapper.EventEntityMapper;
import backend.repository.util.Repository;

import java.util.*;

public final class EventRepository {
    private static EventRepository repository;
    private final Map<Integer, IEventEntity> map;

    public static EventRepository getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (EventRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new EventEntityMapper();
                var rep = new Repository<>(Filename.EVENT, mapper);
                var map = rep.create();
                repository = new EventRepository(map);
            }
            return repository;
        }
    }

    private EventRepository(Map<Integer, IEventEntity> map) {
        this.map = new HashMap<>(map);
    }

    public List<IEventEntity> getAll() {
        return this.map.values().stream().toList();
    }

    public List<IEventEntity> getByIdMap(int idMap) {
        return this.map.values()
                .stream()
                .filter(v -> v.idMap() == idMap)
                .toList();
    }

    public List<IEventEntity> getByIdDoor(int idDoor) {
        return this.map.values()
                .stream()
                .filter(v -> v.idDoor() == idDoor)
                .toList();
    }

    public Optional<IEventEntity> getByIdItem(int idItem) {
        return this.map.values()
                .stream()
                .filter(v -> v.idItem() == idItem)
                .findFirst();

    }

    public List<IEventEntity> getById(int id) {
        return this.map.values()
                .stream()
                .filter(v -> v.id() == id)
                .toList();
    }

}
