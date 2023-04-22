package backend.repository;

import backend.model.builder.map.MapGame;
import backend.mapper.MapGameMapper;
import backend.util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class RepositoryMapGame implements Repository<MapGame> {
    private final Map<String, MapGame> mapGame;

    RepositoryMapGame() {
        this.mapGame = new HashMap<>();
        createMapGame();
    }

    private void createMapGame() {
        String filename = "map/map.csv";
        var file = new FileUtil<MapGame>(filename);
        try {
            this.mapGame.putAll(file.readFile(new MapGameMapper()).stream().collect(Collectors.toMap(MapGame::getName, mapGame1 -> mapGame1)));
        } catch (IOException e) {
            System.exit(0);
        }
    }

    @Override
    public MapGame get(String name) {
        return this.mapGame.get(name);
    }

    @Override
    public List<MapGame> getAll() {
        return  this.mapGame.values().stream().toList();
    }

}
