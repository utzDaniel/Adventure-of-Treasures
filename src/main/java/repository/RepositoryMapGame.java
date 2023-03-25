package repository;

import model.builder.map.MapGame;
import model.mapper.MapGameMapper;
import util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RepositoryMapGame implements Repository<MapGame> {
    private final Map<String, MapGame> mapGame;

    RepositoryMapGame() {
        this.mapGame = new HashMap<>();
        createMapGame();
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
