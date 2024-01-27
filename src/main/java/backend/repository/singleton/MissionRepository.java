package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IMissionEntity;
import backend.repository.mapper.MissionEntityMapper;
import backend.repository.util.Repository;

import java.util.*;

public final class MissionRepository {
    private static MissionRepository repository;
    private final Map<Integer, IMissionEntity> map;

    public static MissionRepository getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (MissionRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new MissionEntityMapper();
                var rep = new Repository<>(Filename.MISSION, mapper);
                var map = rep.create();
                repository = new MissionRepository(map);
            }
        }
        return repository;
    }

    private MissionRepository(Map<Integer, IMissionEntity> map) {
        this.map = new HashMap<>(map);
    }

    public List<IMissionEntity> getAll() {
        return this.map.values().stream().toList();
    }

    public Optional<IMissionEntity> getByIdItem(int idItem) {
        return this.map.values()
                .stream()
                .filter(v -> v.idItem() == idItem)
                .findFirst();

    }

}
