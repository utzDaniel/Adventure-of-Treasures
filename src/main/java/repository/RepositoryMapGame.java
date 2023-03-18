package repository;

import model.builder.map.MapGame;
import model.mapper.MapGameMapper;
import util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RepositoryMapGame implements Repository<MapGame> {

    private static RepositoryMapGame repositoryMapGame;
    private final Map<String, MapGame> mapGame;

    private RepositoryMapGame() {
        this.mapGame = new HashMap<>();
        createMapGame();
    }

    public static synchronized RepositoryMapGame getInstance() {
        if (Objects.isNull(repositoryMapGame)) {
            repositoryMapGame = new RepositoryMapGame();
        }
        return repositoryMapGame;
    }

    private void createMapGame() {
        String filename = "map/map.csv";
        var filee = new FileUtil<MapGame>(filename);
        try {
            this.mapGame.putAll(filee.readFile(new MapGameMapper()).stream().collect(Collectors.toMap(MapGame::getName, mapGame1 -> mapGame1)));
        } catch (IOException e) {
            System.exit(0);
        }
    }

    @Override
    public MapGame get(String name) {
        return this.mapGame.get(name);
    }

}
