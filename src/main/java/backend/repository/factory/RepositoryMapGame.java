package backend.repository.factory;

import backend.repository.interfaces.IMapGameEntity;
import backend.repository.mapper.MapGameEntityMapper;
import backend.repository.interfaces.IRepository;
import backend.repository.util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class RepositoryMapGame implements IRepository<IMapGameEntity, String> {
    private final Map<String, IMapGameEntity> mapGame;

    RepositoryMapGame() {
        this.mapGame = new HashMap<>();
        createMapGame();
    }

    private void createMapGame() {
        String filename = "map/map.csv";
        var file = new FileUtil<IMapGameEntity>(filename);
        try {
            this.mapGame.putAll(
                    file.readFile(new MapGameEntityMapper()).stream()
                            .collect(Collectors.toMap(IMapGameEntity::name, mapGame1 -> mapGame1)));
        } catch (IOException e) {
            System.exit(0);
        }
    }

    @Override
    public IMapGameEntity get(String name) {
        return this.mapGame.get(name);
    }

    @Override
    public List<IMapGameEntity> getAll() {
        return this.mapGame.values().stream().toList();
    }

}
