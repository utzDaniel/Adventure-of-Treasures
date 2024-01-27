package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IEventInventoryEntity;
import backend.repository.mapper.EventInventoryEntityMapper;
import backend.repository.util.Repository;

import java.util.*;

public final class EventInventoryRepository {
    private static EventInventoryRepository repository;
    private final Map<Integer, IEventInventoryEntity> map;

    public static EventInventoryRepository getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (EventInventoryRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new EventInventoryEntityMapper();
                var rep = new Repository<>(Filename.EVENT_INVENTORY, mapper);
                var map = rep.create();
                repository = new EventInventoryRepository(map);
            }
        }
        return repository;
    }

    private EventInventoryRepository(Map<Integer, IEventInventoryEntity> map) {
        this.map = new HashMap<>(map);
    }

    public List<IEventInventoryEntity> getAll() {
        return this.map.values().stream().toList();
    }

    public Optional<IEventInventoryEntity> getByIdItem(int idItem) {
        return this.map.values()
                .stream()
                .filter(v -> v.idItem() == idItem)
                .findFirst();

    }

}
