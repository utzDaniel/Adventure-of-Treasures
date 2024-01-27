package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IEventItemEntity;
import backend.repository.mapper.EventItemEntityMapper;
import backend.repository.util.Repository;

import java.util.*;

public final class EventItemRepository {
    private static EventItemRepository repository;
    private final Map<Integer, IEventItemEntity> map;

    public static EventItemRepository getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (EventItemRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new EventItemEntityMapper();
                var rep = new Repository<>(Filename.EVENT_ITEM, mapper);
                var map = rep.create();
                repository = new EventItemRepository(map);
            }
        }
        return repository;
    }

    private EventItemRepository(Map<Integer, IEventItemEntity> map) {
        this.map = new HashMap<>(map);
    }

    public List<IEventItemEntity> getAll() {
        return this.map.values().stream().toList();
    }

    public Optional<IEventItemEntity> getByIdItem(int idItem) {
        return this.map.values()
                .stream()
                .filter(v -> v.idItem() == idItem)
                .findFirst();
    }
}
