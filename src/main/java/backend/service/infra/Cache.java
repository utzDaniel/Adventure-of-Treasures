package backend.service.infra;

import backend.repository.factory.RepositoryFactory;
import backend.service.model.builder.MapGame;
import backend.service.model.builder.MapGameFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Cache {

    //TODO usar o padrao Flyweight
    private static final Map<String, MapGame> mapGame = new HashMap<>();
    private static final MapGameFactory factory = new MapGameFactory();

    private Cache(){}

    public static void addMapGame(MapGame mapGame){
        Cache.mapGame.put(mapGame.getName(), mapGame);
    }

    public static MapGame getMapGame(String mapGame){
        var mapGame1 = Cache.mapGame.get(mapGame);
        if(Objects.isNull(mapGame1)) {
            mapGame1 = create(mapGame);
            addMapGame(mapGame1);
        }
        return mapGame1;
    }

    private static MapGame create(String mapGame){
        var entity = RepositoryFactory.getRepositoryMapGame().get(mapGame);
        return factory.create(entity);
    }

}
