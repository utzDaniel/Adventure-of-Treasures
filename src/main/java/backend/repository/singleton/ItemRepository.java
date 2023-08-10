package backend.repository.singleton;

import backend.repository.enums.Filename;
import backend.repository.interfaces.IItemEntity;
import backend.repository.interfaces.IRepository;
import backend.repository.mapper.ItemEntityMapper;
import backend.repository.util.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class ItemRepository implements IRepository<IItemEntity, Integer> {
    private static ItemRepository repository;
    private final Map<Integer, IItemEntity> map;

    public static IRepository<IItemEntity, Integer> getInstance() {
        if (Objects.nonNull(repository)) return repository;
        synchronized (ItemRepository.class) {
            if (Objects.isNull(repository)) {
                var mapper = new ItemEntityMapper();
                var rep = new Repository<>(Filename.ITEM, mapper);
                var map = rep.create();
                repository = new ItemRepository(map);
            }
            return repository;
        }
    }

    private ItemRepository(Map<Integer, IItemEntity> map) {
        this.map = new HashMap<>(map);
    }

    @Override
    public IItemEntity getById(Integer id) {
        return this.map.get(id);
    }

    @Override
    public List<IItemEntity> getAll() {
        return this.map.values().stream().toList();
    }

}
