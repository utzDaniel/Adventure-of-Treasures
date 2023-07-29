package backend.repository.factory;

import backend.repository.interfaces.ICombinableEntity;
import backend.repository.interfaces.IRepository;
import backend.repository.mapper.CombinableEntityMapper;
import backend.repository.util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class RepositoryCombinable implements IRepository<ICombinableEntity, Integer> {
    private final Map<Integer, ICombinableEntity> combinable;

    RepositoryCombinable() {
        this.combinable = new HashMap<>();
        createCombinable();
    }

    private void createCombinable() {
        String filename = "item/combinable.csv";
        var file = new FileUtil<ICombinableEntity>(filename);
        try {
            this.combinable.putAll(
                    file.readFile(new CombinableEntityMapper()).stream()
                            .collect(Collectors.toMap(ICombinableEntity::id, combinable1 -> combinable1)));
        } catch (IOException e) {
            System.exit(0);
        }
    }

    @Override
    public ICombinableEntity get(Integer id) {
        return this.combinable.get(id);
    }

    @Override
    public List<ICombinableEntity> getAll() {
        return this.combinable.values().stream().toList();
    }

}
