package backend.repository.factory;

import backend.repository.interfaces.IRepository;
import backend.repository.interfaces.IUsableEntity;
import backend.repository.mapper.UsableEntityMapper;
import backend.repository.util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class RepositoryUsable implements IRepository<IUsableEntity, Integer> {
    private final Map<Integer, IUsableEntity> usable;

    RepositoryUsable() {
        this.usable = new HashMap<>();
        createUsable();
    }

    private void createUsable() {
        String filename = "item/usable.csv";
        var file = new FileUtil<IUsableEntity>(filename);
        try {
            this.usable.putAll(
                    file.readFile(new UsableEntityMapper()).stream()
                            .collect(Collectors.toMap(IUsableEntity::id, usable1 -> usable1)));
        } catch (IOException e) {
            System.exit(0);
        }
    }

    @Override
    public IUsableEntity get(Integer id) {
        return this.usable.get(id);
    }

    @Override
    public List<IUsableEntity> getAll() {
        return this.usable.values().stream().toList();
    }

}
