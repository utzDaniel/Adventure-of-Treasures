package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IEventDoorEntity;
import backend.repository.mapper.EventDoorEntityMapper;

import java.util.*;

public final class EventIDoorRepository {
    private static EventIDoorRepository repository;
    private final Map<Integer, IEventDoorEntity> map;

    public static EventIDoorRepository getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (EventIDoorRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new EventDoorEntityMapper();
                var rep = new Repository<>(Filename.EVENT_DOOR, mapper);
                var map = rep.create();
                repository = new EventIDoorRepository(map);
            }
        }
        return repository;
    }

    private EventIDoorRepository(Map<Integer, IEventDoorEntity> map) {
        this.map = new HashMap<>(map);
    }

    public List<IEventDoorEntity> getAll() {
        return this.map.values().stream().toList();
    }

    public Optional<IEventDoorEntity> getByIdItem(int idItem) {
        return this.map.values()
                .stream()
                .filter(v -> v.idItem() == idItem)
                .findFirst();
    }
}