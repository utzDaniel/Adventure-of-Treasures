package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IEquipableEntity;
import backend.repository.mapper.EquipableEntityMapper;
import backend.repository.util.Repository;

import java.util.*;

public final class EquipableRepository {
    private static EquipableRepository repository;
    private final Map<Integer, IEquipableEntity> map;

    public static EquipableRepository getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (EquipableRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new EquipableEntityMapper();
                var rep = new Repository<>(Filename.EQUIPABLE, mapper);
                var map = rep.create();
                repository = new EquipableRepository(map);
            }
            return repository;
        }
    }

    private EquipableRepository(Map<Integer, IEquipableEntity> map) {
        this.map = new HashMap<>(map);
    }

    public Optional<IEquipableEntity> getByIdItem(int idItem) {
        return this.map.values()
                .stream()
                .filter(v -> v.idItem() == idItem)
                .findFirst();

    }

}
