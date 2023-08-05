package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IMapGameEntity;
import backend.repository.interfaces.IRepository;
import backend.repository.mapper.MapGameEntityMapper;
import backend.repository.util.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class MapGameRepository implements IRepository<IMapGameEntity, Integer> {
    private static MapGameRepository repository;
    private final Map<Integer, IMapGameEntity> map;

    public static synchronized IRepository<IMapGameEntity, Integer> getInstance() {
        if (Objects.isNull(repository)) {
            var mapper = new MapGameEntityMapper();
            var rep = new Repository<>(Filename.MAPGAME, mapper);
            var map = rep.create();
            repository = new MapGameRepository(map);
        }
        return repository;
    }

    private MapGameRepository(Map<Integer, IMapGameEntity> map) {
        this.map = new HashMap<>(map);
    }

    @Override
    public IMapGameEntity getById(Integer id) {
        return this.map.get(id);
    }

    @Override
    public List<IMapGameEntity> getAll() {
        return this.map.values().stream().toList();
    }

}
