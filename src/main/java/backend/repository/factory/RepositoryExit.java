package backend.repository.factory;

import backend.repository.interfaces.IExitEntity;
import backend.repository.interfaces.IRepository;
import backend.repository.mapper.ExitEntityMapper;
import backend.repository.util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class RepositoryExit implements IRepository<IExitEntity, Integer> {
    private final Map<Integer, IExitEntity> exit;

    RepositoryExit() {
        this.exit = new HashMap<>();
        createDoor();
    }

    private void createDoor() {
        String filename = "exit/exit.csv";
        var file = new FileUtil<IExitEntity>(filename);
        try {
            this.exit.putAll(
                    file.readFile(new ExitEntityMapper()).stream()
                            .collect(Collectors.toMap(IExitEntity::mapGameKey, exit1 -> exit1)));
        } catch (IOException e) {
            System.exit(0);
        }
    }

    @Override
    public IExitEntity get(Integer mapGameKey) {
        return this.exit.get(mapGameKey);
    }

    @Override
    public List<IExitEntity> getAll() {
        return this.exit.values().stream().toList();
    }

}
