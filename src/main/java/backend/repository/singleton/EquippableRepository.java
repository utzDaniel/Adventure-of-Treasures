package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IEquippableEntity;
import backend.repository.mapper.EquippableEntityMapper;

import java.util.*;

public final class EquippableRepository {

    private static EquippableRepository repository;

    private final Map<Integer, IEquippableEntity> map;

    public static EquippableRepository getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (EquippableRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new EquippableEntityMapper();
                var rep = new Repository<>(Filename.EQUIPPABLE, mapper);
                var map = rep.create();
                repository = new EquippableRepository(map);
            }
        }
        return repository;
    }

    private EquippableRepository(Map<Integer, IEquippableEntity> map) {
        this.map = new HashMap<>(map);
    }

    public List<IEquippableEntity> getAll() {
        return this.map.values().stream().toList();
    }

    public Optional<IEquippableEntity> getByIdItem(int idItem) {
        return this.map.values()
                .stream()
                .filter(v -> v.idItem() == idItem)
                .findFirst();
    }

}
