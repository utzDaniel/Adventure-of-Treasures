package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.ICombinableEntity;
import backend.repository.mapper.CombinableEntityMapper;
import backend.repository.util.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class CombinableRepository {

    private static CombinableRepository repository;
    private final Map<Integer, ICombinableEntity> map;

    public static CombinableRepository getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (CombinableRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new CombinableEntityMapper();
                var rep = new Repository<>(Filename.COMBINABLE, mapper);
                var map = rep.create();
                repository = new CombinableRepository(map);
            }
            return repository;
        }
    }

    private CombinableRepository(Map<Integer, ICombinableEntity> map) {
        this.map = new HashMap<>(map);
    }

    public ICombinableEntity get(int id) {
        return this.map.get(id);
    }

    public List<ICombinableEntity> getByIdItemNew(int idItemNew) {
        return this.map.values()
                .stream()
                .filter(v -> v.idItemNew() == idItemNew)
                .toList();
    }

    public List<ICombinableEntity> getByIdItemOri(int idItemOri) {
        return this.map.values()
                .stream()
                .filter(v -> v.idItemOri() == idItemOri)
                .toList();
    }

    public List<ICombinableEntity> getAll() {
        return this.map.values().stream().toList();
    }

}
