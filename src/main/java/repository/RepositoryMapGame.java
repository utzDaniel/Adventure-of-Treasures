package repository;

import model.builder.item.Item;
import model.builder.map.MapGame;
import model.builder.map.Scenery;
import model.mapper.MapGameMapper;
import util.FileUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class RepositoryMapGame {

    private static RepositoryMapGame repositoryMapGame;
    private final Map<String, MapGame> mapGame;

    private Scenery initialScenery;
    private List<Item> itemInvisiblePlayer;

    private RepositoryMapGame() {
        this.mapGame = new HashMap<>();
        this.initialScenery = null;
        itemInvisiblePlayer = new ArrayList<>();
        createMapGame();
    }

    public static synchronized RepositoryMapGame getInstance(){
        if(Objects.isNull(repositoryMapGame)){
            repositoryMapGame = new RepositoryMapGame();
        }
        return repositoryMapGame;
    }

    public List<Item> getItemInvisiblePlayer() {
        return itemInvisiblePlayer;
    }

    private void createMapGame() {
        String filename = "map/map.csv";
        var filee = new FileUtil<MapGame>(filename);
        try {
            this.mapGame.putAll(filee.readFile(new MapGameMapper()).stream()
                    .collect(Collectors.toMap(MapGame::getName, mapGame1 -> mapGame1)));
        } catch (IOException e) {
            System.exit(0);
        }

        itemInvisiblePlayer = RepositoryItem.getInstance().getItemInvisiblePlayer();

        this.initialScenery = (Scenery) mapGame.get("cais");
    }

    public MapGame getMapGame(String name) {
        return this.mapGame.get(name);
    }
    public Scenery getInitialScenery() {
        return this.initialScenery;
    }
}
