package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IDoorEntity;
import backend.repository.mapper.DoorEntityMapper;

import java.util.*;

public final class DoorRepository {
    private static DoorRepository repository;
    private final Map<Integer, IDoorEntity> map;

    public static DoorRepository getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (DoorRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new DoorEntityMapper();
                var rep = new Repository<>(Filename.DOOR, mapper);
                var map = rep.create();
                repository = new DoorRepository(map);
            }
        }
        return repository;
    }

    private DoorRepository(Map<Integer, IDoorEntity> map) {
        this.map = new HashMap<>(map);
    }

    public List<IDoorEntity> getAll() {
        return this.map.values().stream().toList();
    }

    public List<IDoorEntity> getByIdMapInside(int idMapInside) {
        return this.map.values()
                .stream()
                .filter(v -> v.idMapInside() == idMapInside)
                .toList();
    }

    public Optional<IDoorEntity> getById(int idDoor) {
        return Optional.of(this.map.get(idDoor));
    }
}
