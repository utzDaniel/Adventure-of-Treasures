package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IUsableEntity;
import backend.repository.mapper.UsableEntityMapper;
import backend.repository.util.Repository;

import java.util.*;

public final class UsableRepository {
    private static UsableRepository repository;
    private final Map<Integer, IUsableEntity> map;

    public static UsableRepository getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (UsableRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new UsableEntityMapper();
                var rep = new Repository<>(Filename.USABLE, mapper);
                var map = rep.create();
                repository = new UsableRepository(map);
            }
        }
        return repository;
    }

    private UsableRepository(Map<Integer, IUsableEntity> map) {
        this.map = new HashMap<>(map);
    }

    public Optional<IUsableEntity> getByIdItem(int idItem) {
        return this.map.values()
                .stream()
                .filter(v -> v.idItem() == idItem)
                .findFirst();

    }

    public List<IUsableEntity> getAll() {
        return this.map.values().stream().toList();
    }

}
