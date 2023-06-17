package backend.repository.factory;

import backend.repository.interfaces.IDoorEntity;
import backend.repository.interfaces.IRepository;
import backend.repository.mapper.DoorEntityMapper;
import backend.repository.util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class RepositoryDoor implements IRepository<IDoorEntity, Integer> {
    private final Map<Integer, IDoorEntity> door;

    RepositoryDoor() {
        this.door = new HashMap<>();
        createDoor();
    }

    private void createDoor() {
        String filename = "door/door.csv";
        var file = new FileUtil<IDoorEntity>(filename);
        try {
            this.door.putAll(
                    file.readFile(new DoorEntityMapper()).stream()
                            .collect(Collectors.toMap(IDoorEntity::id, door1 -> door1)));
        } catch (IOException e) {
            System.exit(0);
        }
    }

    @Override
    public IDoorEntity get(Integer id) {
        return this.door.get(id);
    }

    @Override
    public List<IDoorEntity> getAll() {
        return this.door.values().stream().toList();
    }

}
