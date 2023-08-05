package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IItemMapEntity;
import backend.repository.mapper.ItemMapEntityMapper;
import backend.repository.util.Repository;

import java.util.*;

public final class ItemMapRepository {
    private static ItemMapRepository repository;
    private final Map<Integer, IItemMapEntity> map;

    public static synchronized ItemMapRepository getInstance() {
        if (Objects.isNull(repository)) {
            var mapper = new ItemMapEntityMapper();
            var rep = new Repository<>(Filename.ITEM_MAP, mapper);
            var map = rep.create();
            repository = new ItemMapRepository(map);
        }
        return repository;
    }

    private ItemMapRepository(Map<Integer, IItemMapEntity> map) {
        this.map = new HashMap<>(map);
    }

    public Optional<IItemMapEntity> getById(int id) {
        return Optional.ofNullable(this.map.get(id));
    }

    public List<IItemMapEntity> getByIdItem(int idItem) {
        return this.map.values()
                .stream()
                .filter(v -> v.idItem() == idItem)
                .toList();
    }

    public List<IItemMapEntity> getByIdMap(int idMap) {
        return this.map.values()
                .stream()
                .filter(v -> v.idMap() == idMap)
                .toList();
    }

    public List<IItemMapEntity> getAll() {
        return this.map.values().stream().toList();
    }

}
