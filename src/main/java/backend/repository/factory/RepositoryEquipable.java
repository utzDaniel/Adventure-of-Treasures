package backend.repository.factory;

import backend.repository.interfaces.IEquipableEntity;
import backend.repository.interfaces.IRepository;
import backend.repository.mapper.EquipableEntityMapper;
import backend.repository.util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class RepositoryEquipable implements IRepository<IEquipableEntity, Integer> {
    private final Map<Integer, IEquipableEntity> equipable;

    RepositoryEquipable() {
        this.equipable = new HashMap<>();
        createEquipable();
    }

    private void createEquipable() {
        String filename = "item/equipable.csv";
        var file = new FileUtil<IEquipableEntity>(filename);
        try {
            this.equipable.putAll(
                    file.readFile(new EquipableEntityMapper()).stream()
                            .collect(Collectors.toMap(IEquipableEntity::id, usable1 -> usable1)));
        } catch (IOException e) {
            System.exit(0);
        }
    }

    @Override
    public IEquipableEntity get(Integer id) {
        return this.equipable.get(id);
    }

    @Override
    public List<IEquipableEntity> getAll() {
        return this.equipable.values().stream().toList();
    }

}
