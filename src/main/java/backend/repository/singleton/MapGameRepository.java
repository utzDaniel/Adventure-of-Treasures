package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IMapGameEntity;
import backend.repository.interfaces.IRepository;
import backend.repository.mapper.MapGameEntityMapper;

import java.util.*;

public final class MapGameRepository implements IRepository<IMapGameEntity, Integer> {
    private static MapGameRepository repository;
    private final Map<Integer, IMapGameEntity> map;

    public static IRepository<IMapGameEntity, Integer> getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (MapGameRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new MapGameEntityMapper();
                var rep = new Repository<>(Filename.MAP_GAME, mapper);
                var map = rep.create();
                repository = new MapGameRepository(map);
            }
        }
        return repository;
    }

    private MapGameRepository(Map<Integer, IMapGameEntity> map) {
        this.map = new HashMap<>(map);
    }

    @Override
    public Optional<IMapGameEntity> getById(Integer id) {
        return Optional.ofNullable(this.map.get(id));
    }

    @Override
    public List<IMapGameEntity> getAll() {
        return this.map.values().stream().toList();
    }

}
